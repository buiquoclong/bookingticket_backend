package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.KindVehicleService;

import java.util.List;

@RestController
@RequestMapping("api/kindVehicle")
@CrossOrigin("http://localhost:3000")
public class KindVehicleController {
    private KindVehicleService kindVehicleService;

    public KindVehicleController(KindVehicleService kindVehicleService) {
        this.kindVehicleService = kindVehicleService;
    }

    // Get all KindVehicle
    @GetMapping
    public List<KindVehicle> getAllKindVehicles(){
        return kindVehicleService.getAllKindVehicle();
    }

    // Create a new KindVehicle
    @PostMapping
    public ResponseEntity<KindVehicle> createKindVehicle(@RequestBody KindVehicle kindVehicle){
        return new ResponseEntity<>(kindVehicleService.saveKindVehicle(kindVehicle), HttpStatus.CREATED);
    }

    // Get KindVehicle by id
    @GetMapping("{id}")
    public ResponseEntity<KindVehicle> getKindVehicleById(@PathVariable ("id") int id){
        return new ResponseEntity<>(kindVehicleService.getKindVehicleByID(id), HttpStatus.OK);
    }

    // Update KindVehicle by id
    @PutMapping("{id}")
    public ResponseEntity<KindVehicle> updateKindVehicleById(@PathVariable ("id") int id, @RequestBody KindVehicle kindVehicle){
        return new ResponseEntity<>(kindVehicleService.updateKindVehicleByID(kindVehicle, id), HttpStatus.OK);
    }

    // Delete KindVehicle by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKindVehicleById(@PathVariable ("id") int id){
        kindVehicleService.deleteKindVehicleByID(id);
        return new ResponseEntity<>("KindVehicle " + id + " is deleted successfully", HttpStatus.OK);
    }
}
