package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

import java.util.List;

public interface UserService {
    String saveUser(UserDTO userDTO);
    List<User> getAllUser();
    User getUserByID(int id);
    User updateUserByID(UserDTO userDTO, int id);
    void deleteUserByID(int id);
    String login(String email, String pass);
    String forgotPassword(String email);
    Page<User> getAllUserPage(Pageable pageable);
}
