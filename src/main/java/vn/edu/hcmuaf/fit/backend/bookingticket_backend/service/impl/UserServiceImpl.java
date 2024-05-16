package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.EmailService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    public UserServiceImpl(UserRepository userRepository){
//        this.userRepository =userRepository;
//    }

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }




    @Override
    public String saveUser(UserDTO userDTO) {
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
    public String login(String email, String pass) {
        User u = userRepository.findByEmail(email);
        if(u != null) {
            if(passwordEncoder.matches(pass, u.getPassword())) {
                return String.valueOf(u.getId()); // Trả về ID nếu email và pass đều đúng
            } else {
                return "Mật khẩu không đúng"; // Thông báo nếu pass không đúng
            }
        } else {
            return "Không tìm thấy người dùng"; // Thông báo nếu không tìm thấy email
        }
    }
    public static String generatePassword(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
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
}
