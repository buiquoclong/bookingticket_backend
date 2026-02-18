package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripSearchDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.DriverRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.RouteRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.VehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.BookingSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.TripSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TripService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
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
    public Trip createTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        Route route =  routeRepository.findById(tripDTO.getRouteId()).orElseThrow(() ->
                new ResourceNotFoundException("Route", "Id", tripDTO.getRouteId()));
        Vehicle vehicle =  vehicleRepository.findById(tripDTO.getVehicleId()).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", tripDTO.getVehicleId()));
        Driver driver =  driverRepository.findById(tripDTO.getDriverId()).orElseThrow(() ->
                new ResourceNotFoundException("Driver", "Id", tripDTO.getDriverId()));
        trip.setRoute(route);
        trip.setVehicle(vehicle);
        trip.setDayStart(tripDTO.getDayStart());
        trip.setTimeStart(tripDTO.getTimeStart());
        trip.setPrice(tripDTO.getPrice());
        trip.setDriver(driver);
        trip.setEmptySeat(vehicle.getValue());
        trip.setStatus(tripDTO.getStatus());
        vehicle.setCreatedAt(LocalDateTime.now());
        vehicle.setUpdatedAt(LocalDateTime.now());
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
        Trip existingTrip = tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", id));

        // ✅ Nếu có routeId thì mới cập nhật
        if (tripDTO.getRouteId() > 0) {
            Route route = routeRepository.findById(tripDTO.getRouteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Route", "Id", tripDTO.getRouteId()));
            existingTrip.setRoute(route);
        }

        // ✅ Nếu có vehicleId thì mới cập nhật
        if (tripDTO.getVehicleId() > 0) {
            Vehicle vehicle = vehicleRepository.findById(tripDTO.getVehicleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle", "Id", tripDTO.getVehicleId()));
            existingTrip.setVehicle(vehicle);
        }

        // ✅ Nếu có driverId thì mới cập nhật
        if (tripDTO.getDriverId() > 0) {
            Driver driver = driverRepository.findById(tripDTO.getDriverId())
                    .orElseThrow(() -> new ResourceNotFoundException("Driver", "Id", tripDTO.getDriverId()));
            existingTrip.setDriver(driver);
        }

        // ✅ Kiểm tra ngày khởi hành
        if (tripDTO.getDayStart() != null) {
            existingTrip.setDayStart(tripDTO.getDayStart());
        }

        // ✅ Kiểm tra giờ khởi hành
        if (tripDTO.getTimeStart() != null) {
            existingTrip.setTimeStart(tripDTO.getTimeStart());
        }

        // ✅ Kiểm tra giá vé
        if (tripDTO.getPrice() > 0) {
            existingTrip.setPrice(tripDTO.getPrice());
        }

        // ✅ Kiểm tra số ghế trống
        if (tripDTO.getEmptySeat() > 0) {
            existingTrip.setEmptySeat(tripDTO.getEmptySeat());
        }

        // ✅ Kiểm tra trạng thái
        if (tripDTO.getStatus() > 0) {
            existingTrip.setStatus(tripDTO.getStatus());
        }

        // ✅ Luôn cập nhật thời gian chỉnh sửa
        existingTrip.setUpdatedAt(LocalDateTime.now());

        return tripRepository.save(existingTrip);
    }


    @Override
    public void deleteTripByID(int id) {
        tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", id));
        tripRepository.deleteById(id);
    }

    @Override
    public List<Trip> searchTrips(TripSearchDTO tripSearchDTO) {
        // Lọc theo điểm đi, điểm đến, ngày khởi hành
        List<Trip> trips = tripRepository.findTripsByRoute_DiemDi_IdAndRoute_DiemDen_IdAndDayStart(
                tripSearchDTO.getDiemDiId(),
                tripSearchDTO.getDiemDenId(),
                tripSearchDTO.getDayStart()
        );

        // Nếu có thông tin thời gian khởi hành, thực hiện lọc thêm
        if (tripSearchDTO.getTimeStartFrom() != null && tripSearchDTO.getTimeStartTo() != null) {
            trips = trips.stream()
                    .filter(trip -> trip.getTimeStart().isAfter(tripSearchDTO.getTimeStartFrom())
                            && trip.getTimeStart().isBefore(tripSearchDTO.getTimeStartTo()))
                    .collect(Collectors.toList());
        }

        // Nếu có thông tin loại xe, thực hiện lọc thêm
        if (tripSearchDTO.getKindVehicleId() != 0) {
            trips = trips.stream()
                    .filter(trip -> trip.getVehicle().getKindVehicle().getId() == tripSearchDTO.getKindVehicleId())
                    .collect(Collectors.toList());
        }

        // Nếu có yêu cầu sắp xếp giá
//        if (tripSearchDTO.getSort() != 0) {
//            // Sắp xếp tăng dần nếu sort = 1, ngược lại sắp xếp giảm dần
//            Sort sort = tripSearchDTO.getSort() == 1 ? Sort.by("price").ascending() : Sort.by("price").descending();
//            trips.sort(Comparator.comparingInt(Trip::getPrice));
//        }
        if (tripSearchDTO.getSort() != 0) {
            // Sắp xếp tăng dần nếu sort = 1, ngược lại sắp xếp giảm dần
            if (tripSearchDTO.getSort() == 1) {
                trips.sort(Comparator.comparingInt(Trip::getPrice)); // Tăng dần
            } else {
                trips.sort(Comparator.comparingInt(Trip::getPrice).reversed()); // Giảm dần
            }
        }

        return trips.stream()
                .filter(trip -> trip.getEmptySeat() > 0 && trip.getStatus() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Trip> getAllTripPage(Pageable pageable) {
        return tripRepository.findAll(pageable);
    }

    @Override
    public Page<Trip> getTrips(Integer routeId, LocalDate dayStart, Pageable pageable) {
        Specification<Trip> spec = Specification.where(TripSpecifications.hasRouteId(routeId)
                .and(TripSpecifications.hasDayStart(dayStart)));
        return tripRepository.findAll(spec, pageable);
    }

    @Override
    public void updateTripSeats(int tripId, List<Integer> seatIds) {
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        trip.setEmptySeat(trip.getEmptySeat() - seatIds.size());
        tripRepository.save(trip);
    }
}
