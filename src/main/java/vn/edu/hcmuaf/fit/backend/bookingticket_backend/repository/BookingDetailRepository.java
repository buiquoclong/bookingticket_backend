package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;

import java.util.List;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, String> {
    List<BookingDetail> findByBookingId(int bookingId);
}