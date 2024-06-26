package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.SeatReservationDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatReservation;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatReservationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/seat_reservation")
@CrossOrigin("http://localhost:3000")
public class SeatReservationController {
    private SeatReservationService seatReservationService;

    public SeatReservationController(SeatReservationService seatReservationService) {
        this.seatReservationService = seatReservationService;
    }

    // Get all SeatReservation
    @GetMapping
    public List<SeatReservation> getAllSeatReservations(){return seatReservationService.getAllSeatReservation();}

    //get by trip
//    @GetMapping("/trip/{tripId}")
//    public List<SeatReservation> getSeatReservationsByTripId(@PathVariable("tripId") int tripId) {
//        return seatReservationService.getSeatReservationsByTripId(tripId);
//    }
    @GetMapping("/trip/{tripId}")
    public List<SeatReservation> getSeatReservationsByTripId(@PathVariable("tripId") int tripId) {
        return seatReservationService.getSeatReservationsByTripId(tripId);
    }
    @GetMapping("/booking/{bookingId}")
    public List<SeatReservation> getSeatReservationsByBookingId(@PathVariable("bookingId") int bookingId) {
        return seatReservationService.getSeatReservationsByBookingId(bookingId);
    }

    // Create a new SeatReservation
    @PostMapping
    public ResponseEntity<SeatReservation> createSeatReservation(@RequestBody SeatReservationDTO seatReservationDTO){
        return new ResponseEntity<>(seatReservationService.createSeatReservation(seatReservationDTO), HttpStatus.CREATED);
    }

    // Get SeatReservation By id
    @GetMapping("{id}")
    public ResponseEntity<SeatReservation> getSeatReservationById(@PathVariable ("id") int id){
        return new ResponseEntity<>(seatReservationService.getSeatReservationByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllSeatReservationByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SeatReservation> seatReservationPage = seatReservationService.getAllSeatReservationPage(name,pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("seatReservations", seatReservationPage.getContent());
        response.put("currentPage", seatReservationPage.getNumber());
        response.put("totalItems", seatReservationPage.getTotalElements());
        response.put("totalPages", seatReservationPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update SeatReservation by id
    @PutMapping("{id}")
    public ResponseEntity<SeatReservation> updateSeatReservationById(@PathVariable ("id") int id, @RequestBody SeatReservation SeatReservation){
        return new ResponseEntity<>(seatReservationService.updateSeatReservationByID(SeatReservation, id), HttpStatus.OK);
    }

    // Delete SeatReservation by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeatReservationById(@PathVariable ("id") int id){
        seatReservationService.deleteSeatReservationByID(id);
        return new ResponseEntity<>("SeatReservation " + id + " is deleted successfully", HttpStatus.OK);
    }

    // Delete seatReservation theo bookingId
    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> deleteSeatReservationsByBookingId(@PathVariable("bookingId") int bookingId){
        seatReservationService.deleteSeatReservationsByBookingId(bookingId);
        return new ResponseEntity<>("SeatReservations with Booking Id " + bookingId + " are deleted successfully", HttpStatus.OK);
    }

}
