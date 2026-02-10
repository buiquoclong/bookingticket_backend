package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.WaitingSeat;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaitingSeatRepository extends JpaRepository<WaitingSeat, Integer> {
    List<WaitingSeat> findByTrip_Id(int tripId);
    Optional<WaitingSeat> findByTripIdAndSeatId(int tripId, int seatId);
    boolean existsByTripIdAndSeatIdIn(int tripId, List<Integer> seatIds);
    @Modifying
    @Transactional
    @Query("DELETE FROM WaitingSeat w WHERE w.trip.id = :tripId AND w.seat.id IN :seatIds")
    void deleteByTripIdAndSeatIds(@Param("tripId") int tripId, @Param("seatIds") List<Integer> seatIds);
    // Xóa WaitingSeat theo tripId và seatId
    void deleteByTrip_IdAndSeat_Id(int tripId, int seatId);
}
