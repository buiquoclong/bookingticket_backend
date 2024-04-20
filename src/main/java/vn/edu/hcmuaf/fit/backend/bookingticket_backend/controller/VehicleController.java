package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
@CrossOrigin("http://localhost:3000")
public class VehicleController {
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Get all Vehicle
    @GetMapping
    public List<Vehicle> getAllVehicles(){return vehicleService.getAllVehicle();}

    // Create a new Vehicle
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicle), HttpStatus.CREATED);
    }

    // Get Vehicle By id
    @GetMapping("{id}")
    public ResponseEntity<Vehicle> createVehicle(@PathVariable ("id") int id){
        return new ResponseEntity<>(vehicleService.getVehicleByID(id), HttpStatus.OK);
    }

    // Update Vehicle by id
    @PutMapping("{id}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable ("id") int id, @RequestBody Vehicle vehicle){
        return new ResponseEntity<>(vehicleService.updateVehicleByID(vehicle, id), HttpStatus.OK);
    }

    // Delete Vehicle by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable ("id") int id){
        vehicleService.deleteVehicleByID(id);
        return new ResponseEntity<>("Vehicle " + id + " is deleted successfully", HttpStatus.OK);
    }
}
