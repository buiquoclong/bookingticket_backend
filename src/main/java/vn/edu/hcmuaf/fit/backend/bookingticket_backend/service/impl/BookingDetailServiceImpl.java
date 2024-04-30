package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDetailDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingDetailRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingDetailRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingDetailService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingDetailService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingDetailServiceImpl implements BookingDetailService {
    private BookingDetailRepository bookingDetailRepository;
    private BookingRepository bookingRepository;
    private TripRepository tripRepository;

//    public BookingDetailServiceImpl(BookingDetailRepository bookingDetailRepository) {
//        this.bookingDetailRepository = bookingDetailRepository;
//    }


    public BookingDetailServiceImpl(BookingDetailRepository bookingDetailRepository, BookingRepository bookingRepository, TripRepository tripRepository) {
        this.bookingDetailRepository = bookingDetailRepository;
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public BookingDetail saveBookingDetail(BookingDetailDTO bookingDetailDTO) {
        BookingDetail bookingDetail = new BookingDetail();
        Booking booking =  bookingRepository.findById(bookingDetailDTO.getBookingId()).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", bookingDetailDTO.getBookingId()));
        Trip trip =  tripRepository.findById(bookingDetailDTO.getTripId()).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", bookingDetailDTO.getTripId()));
        bookingDetail.setId(generateBookingDetailId());
        bookingDetail.setBooking(booking);
        bookingDetail.setTrip(trip);
        bookingDetail.setRoundTrip(bookingDetailDTO.getRoundTrip());
        bookingDetail.setQuantity(bookingDetailDTO.getQuantity());
        bookingDetail.setSeatName(bookingDetailDTO.getSeatName());
        bookingDetail.setPrice(bookingDetailDTO.getPrice());
        bookingDetail.setPointCatch(bookingDetailDTO.getPointCatch());
        bookingDetail.setNote(bookingDetailDTO.getNote());
        bookingDetail.setCreatedAt(LocalDateTime.now());
        return bookingDetailRepository.save(bookingDetail);
    }

    public String generateBookingDetailId(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 8;
        StringBuilder ticketCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            ticketCode.append(randomChar);

        }
        return ticketCode.toString();
    }

    @Override
    public List<BookingDetail> getAllBookingDetail() {
        return bookingDetailRepository.findAll();
    }

    @Override
    public BookingDetail getBookingDetailByID(String id) {
        return bookingDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("BookingDetail", "Id", id));
    }

    @Override
    public BookingDetail updateBookingDetailByID(BookingDetail bookingDetail, String id) {
        return null;
    }

    @Override
    public void deleteBookingDetailByID(String id) {
        bookingDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("BookingDetail", "Id", id));
        bookingDetailRepository.deleteById(id);
    }
}
