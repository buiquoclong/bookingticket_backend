package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LoginDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.UserDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Login
    @PostMapping("login")
    public String loginToken(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }
    @GetMapping("/token")
    public ResponseEntity<String> getToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String token = (String) session.getAttribute("token");
            if (token != null) {
                session.removeAttribute("token"); // Xóa token khỏi session sau khi lấy
                return ResponseEntity.ok(token);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token not found");
    }

    // Get all User
    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    // Create a new User
    @PostMapping
    public String creatUser(@RequestBody UserDTO userDTO, HttpServletRequest request){

        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return null;
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            String createUser = userService.createUser(userDTO);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo tài khoản tên: "+ userDTO.getName(), 1);
            logService.createLog(logData);

            return createUser;
        } catch (Exception e) {
            return "" + e;
        }
    }
    @PostMapping("register")
    public String register(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    // Get User by id
    @GetMapping("{id}")
    public ResponseEntity<User> getuserById(@PathVariable ("id") int id){
        return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.OK);
    }

    // phân trang
//    @GetMapping("page")
//    public ResponseEntity<Map<String, Object>> getAllUserByPage1(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<User> userPage = userService.getAllUserPage(pageable);
//        Map<String, Object> response = new HashMap<>();
//        response.put("users", userPage.getContent());
//        response.put("currentPage", userPage.getNumber());
//        response.put("totalItems", userPage.getTotalElements());
//        response.put("totalPages", userPage.getTotalPages());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllUserByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer role,
            @RequestParam(required = false) Integer status) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<User> userPage = userService.getAllUserPage(pageable, name, email, phone, role, status);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update User by id
    @PutMapping("{id}")
    public ResponseEntity<User> updateUserById(@PathVariable ("id") int id, @RequestBody UserDTO userDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        // Save old values for comparison
        int oldRole = existingUser.getRole();
        int oldStatus = existingUser.getStatus();

        // Update the user
        User updatedUser;
        try {
            updatedUser = userService.updateUserByID(userDTO, id);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Check what has changed
        boolean roleChanged = oldRole != updatedUser.getRole();
        boolean statusChanged = oldStatus != updatedUser.getStatus();
        boolean otherInfoChanged = !existingUser.equals(updatedUser);

        // Generate log message based on changes
        StringBuilder logMessage = new StringBuilder("Cập nhật thông tin tài khoản Id: " + id);
        if (roleChanged) {
            logMessage.append(", thay đổi quyền");
        }
        if (statusChanged) {
            logMessage.append(", thay đổi trạng thái");
            if (updatedUser.getStatus() == 3) {
                logMessage.append(" (khóa tài khoản)");
            }
        }
        if (otherInfoChanged && !roleChanged && !statusChanged) {
            logMessage.append(", thay đổi thông tin khác");
        }

        // Create log entry
        LogDTO logData = logService.convertToLogDTO(userId, logMessage.toString(), 2);
        logService.createLog(logData);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<User> ClientUpdateUserById(@PathVariable ("id") int id, @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUserByID(userDTO, id), HttpStatus.OK);
    }

    // Delete User by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable ("id") int id, HttpServletRequest request){
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

    // gửi lại mã xác thực
    @PostMapping("change-confirm")
    public ResponseEntity<String> changeConfirm(@RequestBody Map<String, String> requestBody) {
        Integer userId = Integer.valueOf(requestBody.get("userId"));
        String response = userService.sendMailConfirmAccount(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // đổi mật khẩu
    @PutMapping("{userId}/change-password")
    public String changePassword(@PathVariable("userId") int userId,@RequestBody Map<String, String> requestBody){
        String oldPassword = requestBody.get("oldPassword");
        String newPassword = requestBody.get("newPassword");
        // Thực hiện việc đổi mật khẩu
        String result;
        try {
            result = userService.changePassword(userId, oldPassword, newPassword);
            if ("Mật khẩu đã được thay đổi thành công".equals(result)) {
                LogDTO logData = logService.convertToLogDTO(userId, "Đổi mật khẩu", 2);
                logService.createLog(logData);
            }
        } catch (ResourceNotFoundException e) {
            result = "Người dùng không tồn tại";
        }

        return result;
    }
    @GetMapping("/totalUser")
    public long getTotalUsers() {
        return userService.getTotalUsers();
    }
}
