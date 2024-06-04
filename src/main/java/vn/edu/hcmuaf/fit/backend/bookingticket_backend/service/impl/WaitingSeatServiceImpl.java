package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.SeatReservationDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.WaitingSeatDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatReservationService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.WaitingSeatService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WaitingSeatServiceImpl implements WaitingSeatService {
    private SeatReservationRepository seatReservationRepository;
    private WaitingSeatRepository waitingSeatRepository;
    private SeatRepository seatRepository;
    private TripRepository tripRepository;

    public WaitingSeatServiceImpl(WaitingSeatRepository waitingSeatRepository, SeatRepository seatRepository, TripRepository tripRepository) {
        this.waitingSeatRepository = waitingSeatRepository;
        this.seatRepository = seatRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public WaitingSeat saveWaitingSeat(WaitingSeatDTO waitingSeatDTO) {
        WaitingSeat waitingSeat = new WaitingSeat();
        Trip trip =  tripRepository.findById(waitingSeatDTO.getTripId()).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", waitingSeatDTO.getTripId()));
        Seat seat =  seatRepository.findById(waitingSeatDTO.getSeatId()).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", waitingSeatDTO.getSeatId()));
        waitingSeat.setTrip(trip);
        waitingSeat.setSeat(seat);
        waitingSeat.setCreatedAt(LocalDateTime.now());
        return waitingSeatRepository.save(waitingSeat);
    }

    @Override
    public List<WaitingSeat> getWaitingSeatsByTripId(int tripId) {
        return waitingSeatRepository.findByTrip_Id(tripId);
    }

    @Override
    public List<WaitingSeat> getAllWaitingSeat() {
        return waitingSeatRepository.findAll();
    }

    @Override
    public WaitingSeat getWaitingSeatByID(int id) {
        return waitingSeatRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("WaitingSeat", "Id", id));
    }

    @Override
    public WaitingSeat updateWaitingSeatByID(WaitingSeatDTO waitingSeatDTO, int id) {
        return null;
    }

    @Override
    public void deleteWaitingSeatByID(int id) {
        waitingSeatRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("WaitingSeat", "Id", id));
        waitingSeatRepository.deleteById(id);
    }

    @Override
    public void deleteWaitingSeatByTripAndSeatId(WaitingSeatDTO waitingSeatDTO) {
        WaitingSeat waitingSeat = waitingSeatRepository.findByTripIdAndSeatId(waitingSeatDTO.getTripId(), waitingSeatDTO.getSeatId()).orElseThrow(() ->
                new ResourceNotFoundException("WaitingSeat", "TripId and SeatId", waitingSeatDTO.getTripId() + " and " + waitingSeatDTO.getSeatId()));
        waitingSeatRepository.delete(waitingSeat);
    }
}
