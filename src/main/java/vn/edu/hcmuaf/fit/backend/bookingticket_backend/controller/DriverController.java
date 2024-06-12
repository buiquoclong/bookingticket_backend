package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.DriverDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.DriverService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

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
        LogDTO logData =  logService.convertToLogDTO(userId, "Tạo tài xế tên: "+ driverDTO.getName(), 1);
        logService.createLog(logData);
        return new ResponseEntity<>(driverService.createDriver(driverDTO), HttpStatus.CREATED);
    }

    // Get Driver by id
    @GetMapping("{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable ("id") int id){
        return new ResponseEntity<>(driverService.getDriverByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllSeatByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Driver> driverPage = driverService.getAllDriverPage(pageable);
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
        LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật tài xế Id: "+ id, 2);
        logService.createLog(logData);
        return new ResponseEntity<>(driverService.updateDriverByID(driverDTO, id), HttpStatus.OK);
    }

    // Delete driver by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDriverById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Xóa tài xế Id: "+ id, 2);
        logService.createLog(logData);
        driverService.deleteDriverByID(id);
        return new ResponseEntity<>("Driver " + id + " is deleted successfully", HttpStatus.OK);
    }
}
