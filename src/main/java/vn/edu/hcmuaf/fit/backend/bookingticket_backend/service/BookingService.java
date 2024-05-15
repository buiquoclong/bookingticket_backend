package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.util.List;

public interface BookingService {
    Booking saveBooking(BookingDTO bookingDTO);
    List<Booking> getAllBooking();
    Booking getBookingByID(int id);
    Booking updateBookingByID(BookingDTO bookingDTO, int id);
    void deleteBookingByID(int id);
    List<Booking> getBookingByUserId(int userId);
    Page<Booking> getAllBookingPage(Pageable pageable);
    Page<Booking> getBookingByUserIdPageable(int userId, Pageable pageable);
}
