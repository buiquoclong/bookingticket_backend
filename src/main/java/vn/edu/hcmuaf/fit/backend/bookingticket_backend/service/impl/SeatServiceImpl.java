package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatService;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public List<Seat> getAllSeat() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatByID(int id) {
        return seatRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Seat", "Id", id));
    }

    @Override
    public Seat updateSeatByID(Seat seat, int id) {
        return null;
    }

    @Override
    public void deleteSeatByID(int id) {
        seatRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Seat", "Id", id));
        seatRepository.deleteById(id);
    }
}
