package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;

import java.util.List;

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
    public ResponseEntity<User>  creatUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    // Get User by id
    @GetMapping("{id}")
    public ResponseEntity<User> getuserById(@PathVariable ("id") int id){
        return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.OK);
    }

    // Update User by id
    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable ("id") int id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUserByID(user, id), HttpStatus.OK);
    }

    // Delete User by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable ("id") int id){
        userService.deleteUserByID(id);
        return new ResponseEntity<>("User " + id + " is deleted successfully", HttpStatus.OK);
    }
}