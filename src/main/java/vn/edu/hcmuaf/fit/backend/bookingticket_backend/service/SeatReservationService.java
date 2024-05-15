package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.SeatReservationDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatReservation;

import java.util.List;

public interface SeatReservationService {
    SeatReservation saveSeatReservation(SeatReservationDTO seatReservationDTO);
    List<SeatReservation> getAllSeatReservation();
    SeatReservation getSeatReservationByID(int id);
    SeatReservation updateSeatReservationByID(SeatReservation seatReservation, int id);
    void deleteSeatReservationByID(int id);
    List<SeatReservation> getSeatReservationsByTripId(int tripId);
    List<SeatReservation> getSeatReservationsByBookingId(int bookingId);
    void deleteSeatReservationsByBookingId(int bookingId);
    Page<SeatReservation> getAllSeatReservationPage(Pageable pageable);
}
