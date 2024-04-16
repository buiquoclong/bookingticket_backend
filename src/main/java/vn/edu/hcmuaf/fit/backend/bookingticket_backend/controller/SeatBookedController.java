package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatBooked;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatBookedService;

import java.util.List;

@RestController
@RequestMapping("api/seatbooked")
public class SeatBookedController {
    private SeatBookedService seatBookedService;

    public SeatBookedController(SeatBookedService seatBookedService) {
        this.seatBookedService = seatBookedService;
    }

    // Get all SeatBooked
    @GetMapping
    public List<SeatBooked> getAllSeatBookeds(){return seatBookedService.getAllSeatBooked();}

    // Create a new SeatBooked
    @PostMapping
    public ResponseEntity<SeatBooked> createSeatBooked(@RequestBody SeatBooked seatBooked){
        return new ResponseEntity<>(seatBookedService.saveSeatBooked(seatBooked), HttpStatus.CREATED);
    }

    // Get SeatBooked By id
    @GetMapping("{id}")
    public ResponseEntity<SeatBooked> getSeatBookedById(@PathVariable ("id") int id){
        return new ResponseEntity<>(seatBookedService.getSeatBookedByID(id), HttpStatus.OK);
    }

    // Update SeatBooked by id
    @PutMapping("{id}")
    public ResponseEntity<SeatBooked> updateSeatBookedById(@PathVariable ("id") int id, @RequestBody SeatBooked seatBooked){
        return new ResponseEntity<>(seatBookedService.updateSeatBookedByID(seatBooked, id), HttpStatus.OK);
    }

    // Delete SeatBooked by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeatBookedById(@PathVariable ("id") int id){
        seatBookedService.deleteSeatBookedByID(id);
        return new ResponseEntity<>("SeatBooked " + id + " is deleted successfully", HttpStatus.OK);
    }

}
