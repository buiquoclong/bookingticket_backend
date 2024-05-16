package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Login
    @PostMapping("login")
    public String login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO.getEmail(),userDTO.getPassword());
    }

    // Get all User
    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    // Create a new User
    @PostMapping
    public String creatUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    // Get User by id
    @GetMapping("{id}")
    public ResponseEntity<User> getuserById(@PathVariable ("id") int id){
        return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllUserByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<User> userPage = userService.getAllUserPage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update User by id
    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable ("id") int id, @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUserByID(userDTO, id), HttpStatus.OK);
    }

    // Delete User by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable ("id") int id){
        userService.deleteUserByID(id);
        return new ResponseEntity<>("User " + id + " is deleted successfully", HttpStatus.OK);
    }

    // quên mật khẩu
    @PostMapping("forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String response = userService.forgotPassword(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // đổi mật khẩu
    @PutMapping("{userId}/change-password")
    public String changePassword(@PathVariable("userId") int userId,@RequestBody Map<String, String> requestBody){
        String oldPassword = requestBody.get("oldPassword");
        String newPassword = requestBody.get("newPassword");
        return userService.changePassword(userId, oldPassword, newPassword);
    }
}
