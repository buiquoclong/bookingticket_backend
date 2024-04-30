package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.util.List;

public interface SeatService {
    Seat saveSeat(Seat seat);
    List<Seat> getAllSeat();
    Seat getSeatByID(int id);
    Seat updateSeatByID(Seat seat, int id);
    void deleteSeatByID(int id);
    @Query("SELECT s FROM Seat s JOIN FETCH s.kindVehicle kv WHERE kv.id = :kindVehicleId")
    List<Seat> getAllSeatsByKindVehicleId(@Param("kindVehicleId") int kindVehicleId);
}
