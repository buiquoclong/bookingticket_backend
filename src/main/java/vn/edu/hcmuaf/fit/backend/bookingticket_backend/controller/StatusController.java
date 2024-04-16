package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Status;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.StatusService;

import java.util.List;

@RestController
@RequestMapping("api/status")
public class StatusController {
    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    // Get all Status
    @GetMapping
    public List<Status> getAllStatuses(){return statusService.getAllStatus();}

    // Create a new Status
    @PostMapping
    public ResponseEntity<Status> craeteStatus(@RequestBody Status status){
        return  new ResponseEntity<>(statusService.saveStatus(status), HttpStatus.CREATED);
    }

    // Get Status by id
    @GetMapping("{id}")
    public ResponseEntity<Status> createStatus(@PathVariable ("id") int id){
        return new ResponseEntity<>(statusService.getStatusByID(id), HttpStatus.OK);
    }

    // Update Status by id
    @PutMapping("{id}")
    public ResponseEntity<Status> updateStatusById(@PathVariable ("id") int id, @RequestBody Status status){
        return new ResponseEntity<>(statusService.updateStatusByID(status, id), HttpStatus.OK);
    }

    // Delete Status by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStatusById(@PathVariable ("id") int id){
        statusService.deleteStatusByID(id);
        return new ResponseEntity<>("Status " + id + " is deleted successfully", HttpStatus.OK);
    }
}
