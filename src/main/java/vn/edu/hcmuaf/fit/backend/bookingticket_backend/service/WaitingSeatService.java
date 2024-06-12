package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;


import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.WaitingSeatDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.WaitingSeat;

import java.util.List;

public interface WaitingSeatService {
    WaitingSeat createWaitingSeat(WaitingSeatDTO waitingSeatDTO);
    List<WaitingSeat> getAllWaitingSeat();
    WaitingSeat getWaitingSeatByID(int id);
    WaitingSeat updateWaitingSeatByID(WaitingSeatDTO waitingSeatDTO, int id);
    void deleteWaitingSeatByID(int id);
    void deleteWaitingSeatByTripAndSeatId(WaitingSeatDTO waitingSeatDTO);
    List<WaitingSeat> getWaitingSeatsByTripId(int tripId);
}
