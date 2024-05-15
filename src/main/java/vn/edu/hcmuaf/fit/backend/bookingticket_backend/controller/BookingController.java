package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // lấy booking theo userid phân trang
    @GetMapping("/user/{userId}/page")
    public ResponseEntity<Map<String, Object>> getBookingByUserId(
            @PathVariable int userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Booking> bookingPage = bookingService.getBookingByUserIdPageable(userId, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("bookings", bookingPage.getContent());
        response.put("currentPage", bookingPage.getNumber());
        response.put("totalItems", bookingPage.getTotalElements());
        response.put("totalPages", bookingPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllSeatByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Booking> bookingPage = bookingService.getAllBookingPage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("bookings", bookingPage.getContent());
        response.put("currentPage", bookingPage.getNumber());
        response.put("totalItems", bookingPage.getTotalElements());
        response.put("totalPages", bookingPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
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
