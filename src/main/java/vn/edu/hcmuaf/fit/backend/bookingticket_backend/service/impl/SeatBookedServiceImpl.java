package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.SeatBooked;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatBookedRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatBookedService;

import java.util.List;

@Service
public class SeatBookedServiceImpl implements SeatBookedService {
    private SeatBookedRepository seatBookedRepository;

    public SeatBookedServiceImpl(SeatBookedRepository seatBookedRepository) {
        this.seatBookedRepository = seatBookedRepository;
    }

    @Override
    public SeatBooked saveSeatBooked(SeatBooked seatBooked) {
        return seatBookedRepository.save(seatBooked);
    }

    @Override
    public List<SeatBooked> getAllSeatBooked() {
        return seatBookedRepository.findAll();
    }

    @Override
    public SeatBooked getSeatBookedByID(int id) {
        return seatBookedRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SeatBooked", "Id", id));
    }

    @Override
    public SeatBooked updateSeatBookedByID(SeatBooked seatBooked, int id) {
        return null;
    }

    @Override
    public void deleteSeatBookedByID(int id) {
        seatBookedRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("SeatBooked", "Id", id));
        seatBookedRepository.deleteById(id);
    }
}
