package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.DriverService;

import java.util.List;

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
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver){
        return new ResponseEntity<>(driverService.saveDriver(driver), HttpStatus.CREATED);
    }

    // Get Driver by id
    @GetMapping("{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable ("id") int id){
        return new ResponseEntity<>(driverService.getDriverByID(id), HttpStatus.OK);
    }

    // Update Driver by id
    @PutMapping("{id}")
    public ResponseEntity<Driver> updateDriverById(@PathVariable ("id") int id, @RequestBody Driver driver){
        return new ResponseEntity<>(driverService.updateDriverByID(driver, id), HttpStatus.OK);
    }

    // Delete driver by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDriverById(@PathVariable ("id") int id){
        driverService.deleteDriverByID(id);
        return new ResponseEntity<>("Driver " + id + " is deleted successfully", HttpStatus.OK);
    }
}
