package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripSearchDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TripService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public List<Trip> getAllTrip() {
        return tripRepository.findAll();
    }

    @Override
    public Trip getTripByID(int id) {
        return tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", id));
    }

    @Override
    public Trip updateTripByID(Trip trip, int id) {
        return null;
    }

    @Override
    public void deleteTripByID(int id) {
        tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", id));
        tripRepository.deleteById(id);
    }

//    @Override
//    public List<Trip> searchTrips(TripSearchDTO tripSearchDTO) {
//        // Lấy thông tin từ DTO
//        int diemDiId = tripSearchDTO.getDiemDiId();
//        int diemDenId = tripSearchDTO.getDiemDenId();
//        LocalDate dayStart = tripSearchDTO.getDayStart();
//
//        // Gọi repository để thực hiện truy vấn
//        return tripRepository.findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStart(diemDiId, diemDenId, dayStart);
//    }
    @Override
    public List<Trip> searchTrips(TripSearchDTO tripSearchDTO) {
        int diemDiId = tripSearchDTO.getDiemDiId();
        int diemDenId = tripSearchDTO.getDiemDenId();
        LocalDate dayStart = tripSearchDTO.getDayStart();

        List<Trip> allTrips = tripRepository.findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStart(diemDiId, diemDenId, dayStart);
        return allTrips.stream()
                .filter(trip -> trip.getVehicle().getEmptySeat() > 0)
                .collect(Collectors.toList());
    }
}
