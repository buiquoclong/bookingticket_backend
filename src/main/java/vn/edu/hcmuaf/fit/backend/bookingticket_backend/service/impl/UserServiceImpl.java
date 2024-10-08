package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LoginDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.UserSpecification;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.EmailService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private  AuthenticationManager authenticationManager;

    @Autowired
    private  JwtTokenUtils jwtTokenUtil;

//    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
//        this.userRepository = userRepository;
//        this.emailService = emailService;
//    }




    @Override
    public String createUser(UserDTO userDTO) {
        String result = "";
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            result += "Email đã tồn tại";
        }else {
            String confirmToken = String.format("%06d", new Random().nextInt(999999));
            User user = new User();
            user.setName(userDTO.getName());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setRole(userDTO.getRole());
            user.setStatus(userDTO.getStatus());
            user.setType(userDTO.getType());
            user.setConfirmToken(confirmToken);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            User savedUser = userRepository.save(user);
            try {
                emailService.sendVerificationEmail(userDTO.getEmail(), confirmToken);
                result = String.valueOf(savedUser.getId());
            } catch (MessagingException e) {
                result = "Tài khoản đã được tạo nhưng gửi email xác nhận thất bại.";
            }
        }
        return result;
    }


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(int id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public User updateUserByID(UserDTO userDTO, int id) {
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        existingUser.setName(userDTO.getName());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setRole(userDTO.getRole());
        existingUser.setStatus(userDTO.getStatus());
        existingUser.setType(userDTO.getType());
        existingUser.setConfirmToken(userDTO.getConfirmToken());
        existingUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUserByID(int id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        userRepository.deleteById(id);
    }
    @Override
    public String login(LoginDTO loginDTO) {
        User u = userRepository.findByEmail(loginDTO.getEmail());
        if (u == null){
            return "NULL";// "Không tìm thấy người dùng"
        }

        if(!passwordEncoder.matches(loginDTO.getPass(), u.getPassword())) {
            return "PASSWORD";// "Mật khẩu không đúng";
        }
        if (u.getStatus() == 3) {
            return "LOCK";
        }
        if (u.getStatus() == 1) {
            return u.getId() + ",VERIFY"; // Trả về userId và VERIFY
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), u.getId());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(u);
    }

    @Override
    public String sendMailConfirmAccount(int userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        String confirmToken = String.format("%06d", new Random().nextInt(999999));
        existingUser.setConfirmToken(confirmToken);
        userRepository.save(existingUser);
        try {
            emailService.sendVerificationEmail(existingUser.getEmail(), confirmToken);
            return "SUCCESS";
        } catch (MessagingException e) {
            return "FAIL";
        }
    }

    public static String generatePassword(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder password;
        boolean hasLetter;
        boolean hasDigit;

        do {
            password = new StringBuilder(length);
            hasLetter = false;
            hasDigit = false;

            for (int i = 0; i < length; i++) {
                char ch = CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()));
                if (Character.isLetter(ch)) {
                    hasLetter = true;
                }
                if (Character.isDigit(ch)) {
                    hasDigit = true;
                }
                password.append(ch);
            }
        } while (!hasLetter || !hasDigit);

        return password.toString();
    }
    @Override
    public String forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Tạo mật khẩu mới
            String newPassword = generatePassword(8);
            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            // Gửi email thông báo về mật khẩu mới
            try {
                emailService.sendForgotPasswordEmail(email, newPassword);
                return "Mật khẩu mới đã được gửi đến địa chỉ email của bạn.";
            } catch (MessagingException e) {
                return "Đã xảy ra lỗi khi gửi email, vui lòng thử lại sau.";
            }
        } else {
            return "Không tìm thấy người dùng với địa chỉ email này.";
        }
    }

    @Override
    public Page<User> getAllUserPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUserPage(Pageable pageable, String name, String email, String phone, Integer role, Integer status) {
        Specification<User> spec = Specification.where(UserSpecification.hasName(name))
                .and(UserSpecification.hasEmail(email))
                .and(UserSpecification.hasPhone(phone))
                .and(UserSpecification.hasRole(role))
                .and(UserSpecification.hasStatus(status));
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public String changePassword(int userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "Mật khẩu cũ không đúng";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Mật khẩu đã được thay đổi thành công";
    }

    @Override
    public long getTotalUsers() {
        return userRepository.countUsers();
    }
    @Override
    public User processOAuthPostLogin(String email, String username) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setRole(1);
            user.setStatus(2);
            user.setType("GOOGLE");
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        }
        return user;
    }
}
