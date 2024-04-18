package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("api/seat")
public class SeatController {
    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    // Get all Seat
    @GetMapping
    public List<Seat> getAllSeats(){return seatService.getAllSeat();}

    // Create a new Seat
    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat){
        return new ResponseEntity<>(seatService.saveSeat(seat), HttpStatus.CREATED);
    }

    // Get Seat By id
    @GetMapping("{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable ("id") int id){
        return new ResponseEntity<>(seatService.getSeatByID(id), HttpStatus.OK);
    }

    // Update City by id
    @PutMapping("{id}")
    public ResponseEntity<Seat> updateSeatById(@PathVariable ("id") int id, @RequestBody Seat seat){
        return new ResponseEntity<>(seatService.updateSeatByID(seat, id), HttpStatus.OK);
    }

    // Delete city by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeatById(@PathVariable ("id") int id){
        seatService.deleteSeatByID(id);
        return new ResponseEntity<>("Seat " + id + " is deleted successfully", HttpStatus.OK);
    }
}