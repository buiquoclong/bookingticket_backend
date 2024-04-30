package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripSearchDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.DriverRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.RouteRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.VehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TripService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;
    private RouteRepository routeRepository;
    private VehicleRepository vehicleRepository;
    private DriverRepository driverRepository;

//    public TripServiceImpl(TripRepository tripRepository) {
//        this.tripRepository = tripRepository;
//    }

    public TripServiceImpl(TripRepository tripRepository, RouteRepository routeRepository, VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.routeRepository = routeRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
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
    public Trip updateTripByID(TripDTO tripDTO, int id) {
        Trip existingTrip = tripRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Trip", "Id", id));
        Route route =  routeRepository.findById(tripDTO.getRouteId()).orElseThrow(() ->
                new ResourceNotFoundException("Route", "Id", tripDTO.getRouteId()));
        Vehicle vehicle =  vehicleRepository.findById(tripDTO.getVehicleId()).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", tripDTO.getVehicleId()));
        Driver driver =  driverRepository.findById(tripDTO.getDriverId()).orElseThrow(() ->
                new ResourceNotFoundException("Driver", "Id", tripDTO.getDriverId()));
        existingTrip.setRoute(route);
        existingTrip.setVehicle(vehicle);
        existingTrip.setDayStart(tripDTO.getDayStart());
        existingTrip.setTimeStart(tripDTO.getTimeStart());
        existingTrip.setPrice(tripDTO.getPrice());
        existingTrip.setDriver(driver);
        existingTrip.setEmptySeat(tripDTO.getEmptySeat());
        existingTrip.setUpdatedAt(LocalDateTime.now());
        return tripRepository.save(existingTrip);
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
                .filter(trip -> trip.getEmptySeat() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Trip> searchTripsByTime(TripSearchDTO tripSearchDTO) {
        return tripRepository.findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndTimeStartBetween(
                        tripSearchDTO.getDiemDiId(),
                        tripSearchDTO.getDiemDenId(),
                        tripSearchDTO.getDayStart(),
                        tripSearchDTO.getTimeStartFrom(),
                        tripSearchDTO.getTimeStartTo()
                ).stream()
                .filter(trip -> trip.getEmptySeat() > 0)
                .collect(Collectors.toList());
    }
    @Override
    public List<Trip> searchTripsByVehicleType(TripSearchDTO tripSearchDTO) {
        int diemDiId = tripSearchDTO.getDiemDiId();
        int diemDenId = tripSearchDTO.getDiemDenId();
        LocalDate dayStart = tripSearchDTO.getDayStart();
        String vehicleName = tripSearchDTO.getVehicleName();

        return tripRepository.findByRouteDiemDiIdAndRouteDiemDenIdAndDayStartAndVehicleName(diemDiId, diemDenId, dayStart, vehicleName).stream()
                .filter(trip -> trip.getEmptySeat() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Trip> searchTripsByTimeAndVehicleType(TripSearchDTO tripSearchDTO) {
        return tripRepository.findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStartAndTimeStartBetweenAndVehicleName(
                        tripSearchDTO.getDiemDiId(),
                        tripSearchDTO.getDiemDenId(),
                        tripSearchDTO.getDayStart(),
                        tripSearchDTO.getTimeStartFrom(),
                        tripSearchDTO.getTimeStartTo(),
                        tripSearchDTO.getVehicleName()
                ).stream()
                .filter(trip -> trip.getEmptySeat() > 0)
                .collect(Collectors.toList());
    }
}
