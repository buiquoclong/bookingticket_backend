package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.MonthlyRevenueDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(int userId);
    // thống kê tổng doanh thu
    @Query("SELECT SUM(b.total) FROM Booking b WHERE b.isPaid != 2")
    Integer findTotalRevenue();
    // thống kê doanh thu theo ngày
//    @Query("SELECT SUM(b.total) FROM Booking b WHERE DATE(b.dayBook) = :date")
//    Integer findTotalRevenueByDay(@Param("date") LocalDate date);
    @Query("SELECT SUM(b.total) FROM Booking b WHERE DATE(b.dayBook) = :date AND b.isPaid != 2")
    Integer findTotalRevenueByDay(@Param("date") LocalDate date);

    // doanh thu theo tháng
    @Query("SELECT SUM(b.total) FROM Booking b WHERE YEAR(b.dayBook) = :year AND MONTH(b.dayBook) = :month AND b.isPaid != 2")
    Integer findTotalRevenueByMonth(@Param("year") int year, @Param("month") int month);

    // list doanh thu theo 9 tháng
    @Query(value = "SELECT new vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.MonthlyRevenueDTO(EXTRACT(MONTH FROM b.dayBook), CAST(SUM(b.total) AS int), EXTRACT(YEAR FROM b.dayBook)) " +
            "FROM Booking b " +
            "WHERE b.dayBook BETWEEN :startDate AND :endDate AND b.isPaid != 2" +
            "GROUP BY EXTRACT(YEAR FROM b.dayBook), EXTRACT(MONTH FROM b.dayBook) " +
            "ORDER BY EXTRACT(YEAR FROM b.dayBook) DESC, EXTRACT(MONTH FROM b.dayBook) DESC")
    List<MonthlyRevenueDTO> findRevenueForLastNineMonths(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
