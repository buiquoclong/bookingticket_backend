package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.SeatDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.KindVehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatReservationRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.WaitingSeatRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.SeatSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.TripSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;
    private KindVehicleRepository kindVehicleRepository;
    @Autowired
    private SeatReservationRepository seatReservationRepository;
    @Autowired
    private WaitingSeatRepository waitingSeatRepository;


//    public SeatServiceImpl(SeatRepository seatRepository) {
//        this.seatRepository = seatRepository;
//    }

    public SeatServiceImpl(SeatRepository seatRepository, KindVehicleRepository kindVehicleRepository) {
        this.seatRepository = seatRepository;
        this.kindVehicleRepository = kindVehicleRepository;
    }

    @Override
    public Seat createSeat(SeatDTO seatDTO) {
        Seat seat = new Seat();
        KindVehicle kindVehicle = kindVehicleRepository.findById(seatDTO.getKindVehicleId()).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", seatDTO.getKindVehicleId()));
        seat.setName(seatDTO.getName());
        seat.setKindVehicle(kindVehicle);
        seat.setStatus(seatDTO.getStatus());
        seat.setCreatedAt(LocalDateTime.now());
        seat.setUpdatedAt(LocalDateTime.now());
        return seatRepository.save(seat);
    }

    @Override
    public List<Seat> getAllSeat() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> getAllSeatsByKindVehicleId(int kindVehicleId) {
        return seatRepository.findAllByKindVehicleId(kindVehicleId);
    }

    @Override
    public Page<Seat> getAllSeatPage(String name, Integer status, Integer kindVehicleId, Pageable pageable) {
        Specification<Seat> spec = Specification.where(SeatSpecifications.hasName(name))
                .and(SeatSpecifications.hasStatus(status))
                .and(SeatSpecifications.hasKindVehicleId(kindVehicleId));

        return seatRepository.findAll(spec, pageable);
    }

    @Override
    public List<SeatDTO> getSeatsByTrip(int tripId, int kindVehicleId) {
        List<Seat> seats = seatRepository.findAllByKindVehicleId(kindVehicleId);
        List<SeatReservation> reservedSeats = seatReservationRepository.findByTrip_Id(tripId);
        List<WaitingSeat> waitingSeats = waitingSeatRepository.findByTrip_Id(tripId);

        return seats.stream().map(seat -> {
            boolean isReserved = reservedSeats.stream()
                    .anyMatch(r -> r.getSeat().getId() == seat.getId());
            boolean isWaiting = waitingSeats.stream()
                    .anyMatch(w -> w.getSeat().getId() == seat.getId());

            int status = (isReserved || isWaiting) ? 1 : 0;

            return new SeatDTO(seat.getId(), seat.getName(), status);
        }).collect(Collectors.toList());
    }

    @Override
    public boolean checkSeatsConflict(int tripId, List<Integer> seatIds) {
        if (seatIds == null || seatIds.isEmpty()) {
            return false;
        }
        boolean existsReserved = seatReservationRepository.existsByTripIdAndSeatIdIn(tripId, seatIds);
        boolean existsWaiting = waitingSeatRepository.existsByTripIdAndSeatIdIn(tripId, seatIds);

        return existsReserved || existsWaiting;
    }

    @Override
    public Seat getSeatByID(int id) {
        return seatRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Seat", "Id", id));
    }

    @Override
    public Seat updateSeatByID(SeatDTO seatDTO, int id) {
        Seat existingSeat = seatRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Seat", "Id", id));
        KindVehicle kindVehicle = kindVehicleRepository.findById(seatDTO.getKindVehicleId()).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", seatDTO.getKindVehicleId()));
        existingSeat.setName(seatDTO.getName());
        existingSeat.setKindVehicle(kindVehicle);
        existingSeat.setStatus(seatDTO.getStatus());
        existingSeat.setUpdatedAt(LocalDateTime.now());



        return seatRepository.save(existingSeat);
    }

    @Override
    public void deleteSeatByID(int id) {
        seatRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Seat", "Id", id));
        seatRepository.deleteById(id);
    }
}
