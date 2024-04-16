package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatBooked;

import java.util.List;

public interface SeatBookedService {
    SeatBooked saveSeatBooked(SeatBooked seatBooked);
    List<SeatBooked> getAllSeatBooked();
    SeatBooked getSeatBookedByID(int id);
    SeatBooked updateSeatBookedByID(SeatBooked seatBooked, int id);
    void deleteSeatBookedByID(int id);
}
