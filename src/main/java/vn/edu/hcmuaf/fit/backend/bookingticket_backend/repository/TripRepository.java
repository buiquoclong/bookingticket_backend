package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    // Search chuyến
    List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStart(int diemDiId, int diemDenId, LocalDate dayStart);

    // Search theo giờ
    List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndTimeStartBetween(
            int diemDiId, int diemDenId, LocalDate dayStart, LocalTime timeStartFrom, LocalTime timeStartTo);

    // Search theo loại xe
    List<Trip> findByRouteDiemDiIdAndRouteDiemDenIdAndDayStartAndVehicleName(int diemDiId, int diemDenId, LocalDate dayStart, String vehicleName);

    // Search theo giờ và loại xe
    List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndTimeStartBetweenAndVehicleName(
            int diemDiId, int diemDenId, LocalDate dayStart, LocalTime timeStartFrom, LocalTime timeStartTo, String vehicleName);

}
