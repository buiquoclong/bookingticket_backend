package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatReservation;

import java.util.List;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Integer>, JpaSpecificationExecutor<SeatReservation> {
    List<SeatReservation> findByTrip_Id(int tripId);
    List<SeatReservation> findByBooking_Id(int bookingId);
    boolean existsByTripIdAndSeatIdIn(int tripId, List<Integer> seatIds);
}
