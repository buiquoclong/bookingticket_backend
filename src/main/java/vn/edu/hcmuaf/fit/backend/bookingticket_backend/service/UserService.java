package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LoginDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

import java.util.List;

public interface UserService {
    String createUser(UserDTO userDTO);
    List<User> getAllUser();
    User getUserByID(int id);
    User updateUserByID(UserDTO userDTO, int id);
    void deleteUserByID(int id);
    String login(LoginDTO loginDTO);
    String sendMailConfirmAccount(int userId);
    String forgotPassword(String email);
    Page<User> getAllUserPage(Pageable pageable);
    Page<User> getAllUserPage(Pageable pageable, String name, String email, String phone, Integer role, Integer status);

    String  changePassword(int userId, String oldPassword, String newPassword);
    long getTotalUsers();
    User processOAuthPostLogin(String email, String username);
    String confirmAccount(int userId, String token);
    String createUserByAdmin(UserDTO userDTO);
}
