package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDetailDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/booking_detail")
@CrossOrigin("http://localhost:3000")
public class BookingDetailController {
    private BookingDetailService bookingDetailService;

    public BookingDetailController(BookingDetailService bookingDetailService) {
        this.bookingDetailService = bookingDetailService;
    }

    //Get all BookingDetail
    @GetMapping
    public List<BookingDetail> getAllOrderDetails(){return bookingDetailService.getAllBookingDetail();}

    // Create a new BookingDetail
    @PostMapping
    public ResponseEntity<BookingDetail> createOrderDetail(@RequestBody BookingDetailDTO bookingDetailDTO){
        return  new ResponseEntity<>(bookingDetailService.createBookingDetail(bookingDetailDTO), HttpStatus.CREATED);
    }

    // Lấy danh sách các booking detail dựa trên booking ID
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<BookingDetail>> getBookingDetailsByBookingId(@PathVariable int bookingId) {
        List<BookingDetail> bookingDetails = bookingDetailService.getBookingDetailsByBookingId(bookingId);
        return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
    }

    // Lấy dánh sách các bookingdetail theo userId
    @GetMapping("/user/{userId}/booking_details")
    public ResponseEntity<List<BookingDetail>> getAllBookingDetailsByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(bookingDetailService.getAllBookingDetailByUserId(userId), HttpStatus.OK);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<Map<BookingDetail>> getBookingDetailsByUserId(@PathVariable int userId,
//                                                                         @RequestParam(defaultValue = "0") int page,
//                                                                         @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<BookingDetail> bookingDetails = bookingDetailService.getBookingDetailsByUserId(userId, pageable);
//        return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
//    }
    @GetMapping("/user/{userId}/booking_details/page")
    public ResponseEntity<Map<String, Object>> getBookingDetailsByUserId(
            @PathVariable int userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BookingDetail> bookingDetailsPage = bookingDetailService.getBookingDetailsByUserId(userId, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("bookingDetails", bookingDetailsPage.getContent());
        response.put("currentPage", bookingDetailsPage.getNumber());
        response.put("totalItems", bookingDetailsPage.getTotalElements());
        response.put("totalPages", bookingDetailsPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Get BookingDetail by id
    @GetMapping("{id}")
    public ResponseEntity<BookingDetail> getBookingDetailById(@PathVariable ("id") String id){
        return new ResponseEntity<>(bookingDetailService.getBookingDetailByID(id), HttpStatus.OK);
    }

    // Update BookingDetail by id
    @PutMapping("{id}")
    public ResponseEntity<BookingDetail> updateOrderDetailById(@PathVariable ("id") String id, @RequestBody BookingDetail orderDetail){
        return new ResponseEntity<>(bookingDetailService.updateBookingDetailByID(orderDetail, id), HttpStatus.OK);
    }

    //Delete BookingDetail by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderDetailById(@PathVariable ("id") String id){
        bookingDetailService.deleteBookingDetailByID(id);
        return new ResponseEntity<>("BookingDetail " + id + " is deleted successfully", HttpStatus.OK);
    }
}
