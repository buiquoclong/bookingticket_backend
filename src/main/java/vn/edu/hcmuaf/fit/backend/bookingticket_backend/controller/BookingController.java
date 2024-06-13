package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.MonthlyRevenueDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.EmailService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/booking")
@CrossOrigin("http://localhost:3000")
public class BookingController {
    private BookingService bookingService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

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
        return new ResponseEntity<>(bookingService.createBooking(bookingDTO), HttpStatus.CREATED);
    }

    @PostMapping("/for-emp")
    public ResponseEntity<Booking> createBookingForEmployee(@RequestBody BookingDTO bookingDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Đặt vé", 1);
        logService.createLog(logData);
        return new ResponseEntity<>(bookingService.createBooking(bookingDTO), HttpStatus.CREATED);
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
    public ResponseEntity<Booking> updateBookingById(@PathVariable("id") int id, @RequestBody BookingDTO bookingDTO, HttpServletRequest request) {
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));

        Booking updatedBooking;
        try {
            updatedBooking = bookingService.updateBookingByID(bookingDTO, id);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Lấy giá trị status tương ứng
        String statusMessage;
        switch (updatedBooking.getIsPaid()) {
            case 0:
                statusMessage = "Chưa thanh toán";
                break;
            case 1:
                statusMessage = "Đã thanh toán";
                break;
            case 2:
                statusMessage = "Đã hủy";
                break;
            default:
                statusMessage = "Không xác định";
        }

        // Ghi log cập nhật trạng thái
        LogDTO logData = logService.convertToLogDTO(userId, "Cập nhật trạng thái booking Id: " + id + " thành " + statusMessage, 2);
        logService.createLog(logData);

        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    // Delete booking by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookingById(@PathVariable ("id") int id){
        bookingService.deleteBookingByID(id);
        return new ResponseEntity<>("Booking " + id + " is deleted successfully", HttpStatus.OK);
    }

    // send mail booking
    @PostMapping("/sendBookingEmail/{bookingId}")
    public String sendBookingEmail(@PathVariable int bookingId) {
        try {
            emailService.sendBookingDetailsEmail(bookingId);
            return "Email sent successfully";
        } catch (MessagingException e) {
            return "Error while sending email";
        }
    }

    // thống kê tổng doanh thu
    @GetMapping("/totalAll")
    public Integer getTotalRevenue() {
        return bookingService.getTotalRevenue();
    }

    // thống kê doanh thu theo ngày
    @GetMapping("/total-by-day")
    public Integer getRevenueByDay(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return bookingService.getTotalRevenueByDay(localDate);
    }

    // thống kê doanh thu theo tháng
    @GetMapping("/total-by-month")
    public Integer getRevenueByMonth(@RequestParam("yearMonth") String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        return bookingService.getTotalRevenueByMonth(ym);
    }

    @GetMapping("/total/lastNineMonths")
    public List<MonthlyRevenueDTO> getRevenueForLastNineMonths() {
        return bookingService.getRevenueForLastNineMonths();
    }
}
