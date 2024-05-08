package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("api/booking")
@CrossOrigin("http://localhost:3000")
public class BookingController {
    private BookingService bookingService;

//    public BookingController(BookingService BookingService) {
//        this.BookingService = BookingService;
//    }


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Get all Booking
    @GetMapping
    public List<Booking> getAllBookings(){return bookingService.getAllBooking();}

    // Create a new Booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO){
        return new ResponseEntity<>(bookingService.saveBooking(bookingDTO), HttpStatus.CREATED);
    }

    // Get Booking by id
    @GetMapping("{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable ("id") int id){
        return new ResponseEntity<>(bookingService.getBookingByID(id), HttpStatus.OK);
    }

    //get booking by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingByUserId(@PathVariable int userId) {
        List<Booking> bookings = bookingService.getBookingByUserId(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Update Booking by id
    @PutMapping("{id}")
    public ResponseEntity<Booking> updateBookingById(@PathVariable ("id") int id, @RequestBody BookingDTO bookingDTO){
        return new ResponseEntity<>(bookingService.updateBookingByID(bookingDTO, id), HttpStatus.OK);
    }

    // Delete city by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookingById(@PathVariable ("id") int id){
        bookingService.deleteBookingByID(id);
        return new ResponseEntity<>("Booking " + id + " is deleted successfully", HttpStatus.OK);
    }
}
