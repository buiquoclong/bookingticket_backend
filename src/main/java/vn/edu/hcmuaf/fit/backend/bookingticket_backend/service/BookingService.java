package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingRequest;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.MonthlyRevenueDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest bookingRequest) throws MessagingException;
    List<Booking> getAllBooking();
    Booking getBookingByID(int id);
    Booking updateBookingByID(BookingDTO bookingDTO, int id);
    void deleteBookingByID(int id);
    List<Booking> getBookingByUserId(int userId);
    Page<Booking> getAllBookingPage(Pageable pageable);
    Page<Booking> getBookingByUserIdPageable(int userId, Pageable pageable);
    // Tổng bill
    long getTotalBookings();
    // thống kê tổng doanh thu
    public Integer getTotalRevenue();
    // thống kê doanh thu theo ngày
    public Integer getTotalRevenueByDay(LocalDate date);
    // thống kê doanh thu theo tháng
    public Integer getTotalRevenueByMonth(YearMonth yearMonth);

    // doanh thu trong 9 tháng
    public List<MonthlyRevenueDTO> getRevenueForLastNineMonths();

    // số vé đã thanh toán
    Integer countPaidBookingsByMonth(YearMonth yearMonth);
    // Số vé đã bị hủy
    Integer countCancelledBookingsByMonth(YearMonth yearMonth);
    Page<Booking> getAllBookingPage(Pageable pageable, Integer id, String userName, String email, String phone, Integer userId, String kindPay, Integer isPaid, Integer roundTrip);
    Booking cancelBooking(int bookingId);

}
