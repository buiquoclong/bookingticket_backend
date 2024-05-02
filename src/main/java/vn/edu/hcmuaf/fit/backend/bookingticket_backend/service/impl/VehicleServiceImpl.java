package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.KindVehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.VehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.VehicleService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;
    private KindVehicleRepository kindVehicleRepository;

//    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
//        this.vehicleRepository = vehicleRepository;
//    }

    public VehicleServiceImpl(VehicleRepository vehicleRepository, KindVehicleRepository kindVehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.kindVehicleRepository = kindVehicleRepository;
    }

    @Override
    public Vehicle saveVehicle(VehicleDTO vehicleDTO) {
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
        return vehicleRepository.findByKindVehicleId(kindVehicleId);
    }
    @Override
    public Vehicle getVehicleByID(int id) {
        return vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", id));
    }

    @Override
    public Vehicle updateVehicleByID(Vehicle vehicle, int id) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", id));

        existingVehicle.setName(vehicle.getName());
        existingVehicle.setVehicleNumber(vehicle.getVehicleNumber());
        existingVehicle.setValue(vehicle.getValue());
//        existingVehicle.setEmptySeat(vehicle.getEmptySeat());
        existingVehicle.setStatus(vehicle.getStatus());
        existingVehicle.setUpdatedAt(LocalDateTime.now());

        vehicleRepository.save(existingVehicle);
        return null;
    }

    @Override
    public void deleteVehicleByID(int id) {
        vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle", "Id", id));
        vehicleRepository.deleteById(id);
    }
}
