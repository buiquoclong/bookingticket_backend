package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.SeatReservationDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatReservation;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatReservationRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatReservationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatReservationServiceImpl implements SeatReservationService {
    private SeatReservationRepository seatReservationRepository;
    private BookingRepository bookingRepository;
    private SeatRepository seatRepository;
    private TripRepository tripRepository;

    public SeatReservationServiceImpl(SeatReservationRepository seatReservationRepository, BookingRepository bookingRepository, SeatRepository seatRepository, TripRepository tripRepository) {
        this.seatReservationRepository = seatReservationRepository;
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public SeatReservation saveSeatReservation(SeatReservationDTO seatReservationDTO) {
        SeatReservation seatReservation = new SeatReservation();
        Booking booking =  bookingRepository.findById(seatReservationDTO.getBookingId()).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", seatReservationDTO.getBookingId()));
        Seat seat =  seatRepository.findById(seatReservationDTO.getSeatId()).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", seatReservationDTO.getSeatId()));
        Trip trip =  tripRepository.findById(seatReservationDTO.getTripId()).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", seatReservationDTO.getTripId()));
        seatReservation.setBooking(booking);
        seatReservation.setTrip(trip);
        seatReservation.setSeat(seat);
        seatReservation.setStatus(1);
        seatReservation.setCreatedAt(LocalDateTime.now());
        seatReservation.setUpdatedAt(LocalDateTime.now());
        return seatReservationRepository.save(seatReservation);
    }
    //tìm kiếm theo chuyến
//    @Override
//    public List<SeatReservation> getSeatReservationsByTripId(int tripId) {
//        return seatReservationRepository.findByBooking_Trip_Id(tripId);
//    }

    @Override
    public List<SeatReservation> getSeatReservationsByTripId(int tripId) {
        return seatReservationRepository.findByTrip_Id(tripId);
    }

    @Override
    public List<SeatReservation> getSeatReservationsByBookingId(int bookingId) {
        return seatReservationRepository.findByBooking_Id(bookingId);
    }

    @Override
    public List<SeatReservation> getAllSeatReservation() {
        return seatReservationRepository.findAll();
    }

    @Override
    public SeatReservation getSeatReservationByID(int id) {
        return seatReservationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SeatReservation", "Id", id));
    }

    @Override
    public SeatReservation updateSeatReservationByID(SeatReservation seatReservation, int id) {
        return null;
    }

    @Override
    public void deleteSeatReservationByID(int id) {
        seatReservationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SeatReservation", "Id", id));
        seatReservationRepository.deleteById(id);
    }

    // xóa theo bookingId
    @Override
    public void deleteSeatReservationsByBookingId(int bookingId) {
        List<SeatReservation> seatReservations = seatReservationRepository.findByBooking_Id(bookingId);
        if (seatReservations != null && !seatReservations.isEmpty()) {
            seatReservationRepository.deleteAll(seatReservations);
        } else {
            throw new ResourceNotFoundException("Seat Reservations", "Booking Id", bookingId);
        }
    }

    @Override
    public Page<SeatReservation> getAllSeatReservationPage(Pageable pageable) {
        return seatReservationRepository.findAll(pageable);
    }
}
