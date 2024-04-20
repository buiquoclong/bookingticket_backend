package vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
//    List<Trip> findTripsByDiemDiIdAndDiemDenIdAndTimeStart(int diemDiId, int diemDenId, LocalDateTime timeStart);
//    List<Trip> findTripsByDiemDiAndDiemDenAndTimeStart(int diemDi, int diemDen, LocalDate timeStart);
//    List<Trip> findTripsByRoute_DiemDiAndRoute_DiemDenAndTimeStart(int diemDi, int diemDen, LocalDate timeStart);

    List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStart(int diemDiId, int diemDenId, LocalDate dayStart);
    List<Trip> findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndVehicle_EmptySeatGreaterThan(int diemDiId, int diemDenId, LocalDate dayStart, int emptySeats);


}
