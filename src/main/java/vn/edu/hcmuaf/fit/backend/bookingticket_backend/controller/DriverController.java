package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.DriverDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.DriverService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/driver")
@CrossOrigin("http://localhost:3000")
public class DriverController {
    private DriverService driverService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    // Ghi Log
//    private void logAction(int userId, String action, String details, Integer level) {
//        LogDTO logDTO = new LogDTO();
//        logDTO.setUserId(userId);
//        logDTO.setMessage(action + ": " + details);
//        logDTO.setLevel(level);
//        logService.createLog(logDTO);
//    }

    // Get all Driver
    @GetMapping
    public List<Driver> getAllDrivers(){
        return driverService.getAllDriver();
    }

    // Create a new Driver
    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody DriverDTO driverDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            Driver creatDriver = driverService.createDriver(driverDTO);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo tài xế tên: "+ driverDTO.getName(), 1);
            logService.createLog(logData);
            return new ResponseEntity<>(creatDriver, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Driver by id
    @GetMapping("{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable ("id") int id){
        return new ResponseEntity<>(driverService.getDriverByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllDriverByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Driver> driverPage = driverService.getAllDriverPage(name, email, phone, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("drivers", driverPage.getContent());
        response.put("currentPage", driverPage.getNumber());
        response.put("totalItems", driverPage.getTotalElements());
        response.put("totalPages", driverPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Driver by id
    @PutMapping("{id}")
    public ResponseEntity<Driver> updateDriverById(@PathVariable ("id") int id, @RequestBody DriverDTO driverDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            Driver updateDriver = driverService.updateDriverByID(driverDTO, id);

            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật tài xế Id: "+ id, 2);
            logService.createLog(logData);
            return new ResponseEntity<>(updateDriver, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete driver by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDriverById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            driverService.deleteDriverByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData =  logService.convertToLogDTO(userId, "Xóa tài xế Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("Driver " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Driver " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/available")
    public ResponseEntity<List<Driver>> getAvailableDrivers(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dayStart) {
        List<Driver> availableDrivers = driverService.findAvailableDrivers(dayStart);
        return ResponseEntity.ok(availableDrivers);
    }
}
