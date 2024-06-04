package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.SeatReservationDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.WaitingSeatDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatReservation;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.WaitingSeat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatReservationService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.WaitingSeatService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/waiting_seat")
@CrossOrigin("http://localhost:3000")
public class WaitingSeatController {
    private WaitingSeatService waitingSeatService;

    public WaitingSeatController(WaitingSeatService waitingSeatService) {
        this.waitingSeatService = waitingSeatService;
    }

    // Get all WaitingSeat
    @GetMapping
    public List<WaitingSeat> getAllWaitingSeats(){return waitingSeatService.getAllWaitingSeat();}

    //get by trip
    @GetMapping("/trip/{tripId}")
    public List<WaitingSeat> getWaitingSeatsByTripId(@PathVariable("tripId") int tripId) {
        return waitingSeatService.getWaitingSeatsByTripId(tripId);
    }

    // Create a new SeatReservation
    @PostMapping
    public ResponseEntity<WaitingSeat> createWaitingSeat(@RequestBody WaitingSeatDTO waitingSeatDTO){
        return new ResponseEntity<>(waitingSeatService.saveWaitingSeat(waitingSeatDTO), HttpStatus.CREATED);
    }

    // Get SeatReservation By id
    @GetMapping("{id}")
    public ResponseEntity<WaitingSeat> getWaitingSeatByID(@PathVariable ("id") int id){
        return new ResponseEntity<>(waitingSeatService.getWaitingSeatByID(id), HttpStatus.OK);
    }


    // Update SeatReservation by id
    @PutMapping("{id}")
    public ResponseEntity<WaitingSeat> updateWaitingSeatById(@PathVariable ("id") int id, @RequestBody WaitingSeatDTO waitingSeatDTO){
        return new ResponseEntity<>(waitingSeatService.updateWaitingSeatByID(waitingSeatDTO, id), HttpStatus.OK);
    }

    // Delete SeatReservation by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWaitingSeatById(@PathVariable ("id") int id){
        waitingSeatService.deleteWaitingSeatByID(id);
        return new ResponseEntity<>("WaitingSeat " + id + " is deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteWaitingSeat(@RequestBody WaitingSeatDTO waitingSeatDTO){
        waitingSeatService.deleteWaitingSeatByTripAndSeatId(waitingSeatDTO);
        return new ResponseEntity<>("WaitingSeat with tripId " + waitingSeatDTO.getTripId() + " and seatId " + waitingSeatDTO.getSeatId() + " is deleted successfully", HttpStatus.OK);
    }


}
