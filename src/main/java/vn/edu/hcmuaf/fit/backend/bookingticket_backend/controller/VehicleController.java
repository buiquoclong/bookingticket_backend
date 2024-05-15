package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.VehicleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // lấy danh sách xe theo loại xe
    @GetMapping("/kind/{kindVehicleId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByKindVehicleId(@PathVariable int kindVehicleId) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByKindVehicleId(kindVehicleId);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
    // Create a new Vehicle
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<>(vehicleService.saveVehicle(vehicleDTO), HttpStatus.CREATED);
    }

    // Get Vehicle By id
    @GetMapping("{id}")
    public ResponseEntity<Vehicle> createVehicle(@PathVariable ("id") int id){
        return new ResponseEntity<>(vehicleService.getVehicleByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllSeatByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Vehicle> vehiclePage = vehicleService.getAllVehiclePage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("vehicles", vehiclePage.getContent());
        response.put("currentPage", vehiclePage.getNumber());
        response.put("totalItems", vehiclePage.getTotalElements());
        response.put("totalPages", vehiclePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
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
