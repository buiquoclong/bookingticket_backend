package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.WaitingSeat;

import java.util.List;
import java.util.Optional;

@Repository
public interface WaitingSeatRepository extends JpaRepository<WaitingSeat, Integer> {
    List<WaitingSeat> findByTrip_Id(int tripId);
    Optional<WaitingSeat> findByTripIdAndSeatId(int tripId, int seatId);
}
