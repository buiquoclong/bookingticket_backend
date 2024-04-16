package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.util.List;

public interface SeatService {
    Seat saveSeat(Seat seat);
    List<Seat> getAllSeat();
    Seat getSeatByID(int id);
    Seat updateSeatByID(Seat seat, int id);
    void deleteSeatByID(int id);
}
