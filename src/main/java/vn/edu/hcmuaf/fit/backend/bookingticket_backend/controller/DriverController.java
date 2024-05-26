package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.DriverDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.DriverService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/driver")
@CrossOrigin("http://localhost:3000")
public class DriverController {
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // Get all Driver
    @GetMapping
    public List<Driver> getAllDrivers(){
        return driverService.getAllDriver();
    }

    // Create a new Driver
    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody DriverDTO driverDTO){
        return new ResponseEntity<>(driverService.saveDriver(driverDTO), HttpStatus.CREATED);
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
    public ResponseEntity<Driver> updateDriverById(@PathVariable ("id") int id, @RequestBody DriverDTO driverDTO){
        return new ResponseEntity<>(driverService.updateDriverByID(driverDTO, id), HttpStatus.OK);
    }

    // Delete driver by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDriverById(@PathVariable ("id") int id){
        driverService.deleteDriverByID(id);
        return new ResponseEntity<>("Driver " + id + " is deleted successfully", HttpStatus.OK);
    }
}
