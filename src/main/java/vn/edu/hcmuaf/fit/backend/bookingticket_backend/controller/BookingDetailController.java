package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDetailDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingDetailService;

import java.util.List;

@RestController
@RequestMapping("api/boking_detail")
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
        return  new ResponseEntity<>(bookingDetailService.saveBookingDetail(bookingDetailDTO), HttpStatus.CREATED);
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


    // Get BookingDetail by id
    @GetMapping("{id}")
    public ResponseEntity<BookingDetail> getOrderDetailById(@PathVariable ("id") String id){
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
