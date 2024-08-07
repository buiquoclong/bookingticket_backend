package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.SeatDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.KindVehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.SeatSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.TripSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.SeatService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    private SeatRepository seatRepository;
    private KindVehicleRepository kindVehicleRepository;

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

    // phân trang
    @Override
    public Page<Seat> getAllSeatPage(String name, String kindVehicleName, Pageable pageable) {
        Specification<Seat> spec = Specification.where(SeatSpecifications.hasName(name)
                .and(SeatSpecifications.hasUserKindVehicleName(kindVehicleName)));
        return seatRepository.findAll(spec, pageable);
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
