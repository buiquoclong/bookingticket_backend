package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

import java.util.List;

public interface UserService {
    User saveUser(UserDTO userDTO);
    List<User> getAllUser();
    User getUserByID(int id);
    User updateUserByID(User user, int id);
    void deleteUserByID(int id);
    String login(String email, String pass);
}
