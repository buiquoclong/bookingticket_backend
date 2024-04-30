package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingSeviceImpl implements BookingService {
     private BookingRepository bookingRepository;
     private UserRepository userRepository;
     private TripRepository tripRepository;

//    public BookingSeviceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
//        this.bookingRepository = bookingRepository;
//        this.userRepository = userRepository;
//    }


    public BookingSeviceImpl(BookingRepository bookingRepository, UserRepository userRepository, TripRepository tripRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public Booking saveBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setUserName(bookingDTO.getUserName());
        booking.setEmail(bookingDTO.getEmail());
        booking.setPhone(bookingDTO.getPhone());
        booking.setDayBook(LocalDateTime.now());
        booking.setTotal(bookingDTO.getTotal());
        booking.setKindPay(bookingDTO.getKindPay());
        booking.setIsPaid(bookingDTO.getIsPaid());
        booking.setRoundTrip(bookingDTO.getRoundTrip());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        if (bookingDTO.getUserId() != 0) {
            User user = userRepository.findById(bookingDTO.getUserId()).orElseThrow(() ->
                    new ResourceNotFoundException("User", "Id", bookingDTO.getUserId()));
            booking.setUser(user);
        }        return bookingRepository.save(booking);
    }


    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingByID(int id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
    }

    @Override
    public Booking updateBookingByID(Booking booking, int id) {
        return null;
    }

    @Override
    public void deleteBookingByID(int id) {
        bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", id));
        bookingRepository.deleteById(id);
    }
}
