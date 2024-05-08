package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;

import java.util.List;

public interface BookingService {
    Booking saveBooking(BookingDTO bookingDTO);
    List<Booking> getAllBooking();
    Booking getBookingByID(int id);
    Booking updateBookingByID(BookingDTO bookingDTO, int id);
    void deleteBookingByID(int id);
    List<Booking> getBookingByUserId(int userId);
}
