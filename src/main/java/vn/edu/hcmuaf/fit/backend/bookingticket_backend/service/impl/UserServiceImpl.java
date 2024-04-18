package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository =userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
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
    public User updateUserByID(User user, int id) {
        return null;
    }

    @Override
    public void deleteUserByID(int id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        userRepository.deleteById(id);
    }

//    @Override
//    public String login(String email, String pass) {
//        User u = userRepository.findByEmailAndPassword(email, pass);
//        if(u != null) {
//            return String.valueOf(u.getId());
//        }
//        return "Không tìm thấy người dùng";
//    }
@Override
public String login(String email, String pass) {
    User u = userRepository.findByEmail(email);
    if(u != null) {
        if(u.getPassword().equals(pass)) {
            return String.valueOf(u.getId()); // Trả về ID nếu email và pass đều đúng
        } else {
            return "Mật khẩu không đúng"; // Thông báo nếu pass không đúng
        }
    } else {
        return "Không tìm thấy người dùng"; // Thông báo nếu không tìm thấy email
    }
}
}