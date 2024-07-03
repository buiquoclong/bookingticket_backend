package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.KindVehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.VehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.TripSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.VehicleSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.VehicleService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;
    private KindVehicleRepository kindVehicleRepository;
    @Autowired
    private TripRepository tripRepository;

//    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
//        this.vehicleRepository = vehicleRepository;
//    }

    public VehicleServiceImpl(VehicleRepository vehicleRepository, KindVehicleRepository kindVehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.kindVehicleRepository = kindVehicleRepository;
    }

    @Override
    public Vehicle createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        KindVehicle kindVehicle = kindVehicleRepository.findById(vehicleDTO.getKindVehicleId()).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", vehicleDTO.getKindVehicleId()));
        vehicle.setName(vehicleDTO.getName());
        vehicle.setKindVehicle(kindVehicle);
        vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        vehicle.setValue(vehicleDTO.getValue());
        vehicle.setStatus(vehicleDTO.getStatus());
        vehicle.setCreatedAt(LocalDateTime.now());
        vehicle.setUpdatedAt(LocalDateTime.now());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    // lấy danh sách xe theo loại xe
    @Override
    public List<Vehicle> getVehiclesByKindVehicleId(int kindVehicleId) {
        List<Vehicle> vehicles = vehicleRepository.findByKindVehicleId(kindVehicleId);
        return vehicles.stream()
                .filter(vehicle -> vehicle.getStatus() == 1)
                .collect(Collectors.toList());
    }
    // Lấy danh sách các xe khả dụng khi tạo chuyến
    @Override
    public List<Vehicle> findAvailableVehiclesByKindVehicleId(int kindVehicleId, LocalDate dayStart) {
        List<Vehicle> allVehicles = vehicleRepository.findByKindVehicleId(kindVehicleId); // Lấy tất cả các xe theo loại xe

        // Lọc những xe không có trong danh sách các chuyến có cùng ngày bắt đầu và có status = 1
        List<Integer> tripIds = tripRepository.findTripIdsByDayStart(dayStart);
        List<Vehicle> availableVehicles = allVehicles.stream()
                .filter(vehicle -> vehicle.getStatus() == 1)
                .filter(vehicle -> vehicle.getTrips().stream()
                        .noneMatch(trip -> trip.getDayStart().equals(dayStart) && tripIds.contains(trip.getId())))
                .collect(Collectors.toList());

        return availableVehicles;
    }

    @Override
    public Page<Vehicle> getAllVehiclePage(String name, String kindVehiclename, String vehicleNumber, Pageable pageable) {
        Specification<Vehicle> spec = Specification.where(VehicleSpecifications.hasName(name)
                .and(VehicleSpecifications.hasUserKindVehicleName(kindVehiclename))
                .and(VehicleSpecifications.hasvehicleNumber(vehicleNumber)));
        return vehicleRepository.findAll(spec, pageable);
    }

    @Override
    public Vehicle getVehicleByID(int id) {
        return vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", id));
    }

    @Override
    public Vehicle updateVehicleByID(VehicleDTO vehicleDTO, int id) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", id));
        KindVehicle kindVehicle = kindVehicleRepository.findById(vehicleDTO.getKindVehicleId()).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", vehicleDTO.getKindVehicleId()));
        existingVehicle.setName(vehicleDTO.getName());
        existingVehicle.setKindVehicle(kindVehicle);
        existingVehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
        existingVehicle.setValue(vehicleDTO.getValue());
        existingVehicle.setStatus(vehicleDTO.getStatus());
        existingVehicle.setUpdatedAt(LocalDateTime.now());


        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public void deleteVehicleByID(int id) {
        vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", id));
        vehicleRepository.deleteById(id);
    }
}
