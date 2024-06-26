package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.VehicleService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/vehicle")
@CrossOrigin("http://localhost:3000")
public class VehicleController {
    private VehicleService vehicleService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Get all Vehicle
    @GetMapping
    public List<Vehicle> getAllVehicles(){return vehicleService.getAllVehicle();}

    // lấy danh sách xe theo loại xe
    @GetMapping("/kind/{kindVehicleId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByKindVehicleId(@PathVariable int kindVehicleId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByKindVehicleId(kindVehicleId);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
    // Create a new Vehicle
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDTO vehicleDTO, HttpServletRequest request){

//        String token = jwtTokenUtils.extractJwtFromRequest(request);
//        if (token == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
//        LogDTO logData =  logService.convertToLogDTO(userId, "Tạo phương tiện tên: "+ vehicleDTO.getName(), 1);
//        logService.createLog(logData);

        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            Vehicle createVehicle = vehicleService.createVehicle(vehicleDTO);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo phương tiện tên: "+ vehicleDTO.getName(), 1);
            logService.createLog(logData);
            return new ResponseEntity<>(createVehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Vehicle By id
    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable ("id") int id){
        return new ResponseEntity<>(vehicleService.getVehicleByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllVehicleByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String kindVehiclename,
            @RequestParam(required = false) String vehicleNumber) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Vehicle> vehiclePage = vehicleService.getAllVehiclePage(name, kindVehiclename, vehicleNumber, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("vehicles", vehiclePage.getContent());
        response.put("currentPage", vehiclePage.getNumber());
        response.put("totalItems", vehiclePage.getTotalElements());
        response.put("totalPages", vehiclePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Vehicle by id
    @PutMapping("{id}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable ("id") int id, @RequestBody VehicleDTO vehicleDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            Vehicle updateVehicle = vehicleService.updateVehicleByID(vehicleDTO, id);

            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật phương tiện Id: "+ id, 2);
            logService.createLog(logData);
            return new ResponseEntity<>(updateVehicle, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Vehicle by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            vehicleService.deleteVehicleByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData =  logService.convertToLogDTO(userId, "Xóa phương tiện Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("Vehicle " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Vehicle " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
