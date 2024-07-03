package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer>, JpaSpecificationExecutor<Trip> {
   // Search chuyến
   List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStart(int diemDiId, int diemDenId, LocalDate dayStart);

    // Tìm chuyến theo điểm đi, điểm đến, ngày khởi hành và thời gian khởi hành nằm trong khoảng cho trước
    List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndTimeStartBetween(
            int diemDiId, int diemDenId, LocalDate dayStart, LocalTime timeStartFrom, LocalTime timeStartTo);

    // Tìm chuyến theo điểm đi, điểm đến, ngày khởi hành và loại xe
    List<Trip> findByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndVehicle_KindVehicle_Id(
            int diemDiId, int diemDenId, LocalDate dayStart, int kindVehicleId);

    // Tìm chuyến theo điểm đi, điểm đến, ngày khởi hành, thời gian khởi hành nằm trong khoảng cho trước và loại xe
    List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndTimeStartBetweenAndVehicle_KindVehicle_Id(
            int diemDiId, int diemDenId, LocalDate dayStart, LocalTime timeStartFrom, LocalTime timeStartTo, int kindVehicleId);

    // Sắp xếp chuyến theo giá tăng dần hoặc giảm dần
    List<Trip> findAllByOrderByPriceAsc();
    List<Trip> findAllByOrderByPriceDesc();
    @Query("SELECT t.id FROM Trip t WHERE t.dayStart = :dayStart")
    List<Integer> findTripIdsByDayStart(LocalDate dayStart);

}
