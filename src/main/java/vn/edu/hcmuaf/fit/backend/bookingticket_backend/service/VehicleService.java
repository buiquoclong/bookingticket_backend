package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle createVehicle(VehicleDTO vehicleDTO);
    List<Vehicle> getAllVehicle();
    Vehicle getVehicleByID(int id);
    Vehicle updateVehicleByID(VehicleDTO vehicleDTO, int id);
    void deleteVehicleByID(int id);
    List<Vehicle> getVehiclesByKindVehicleId(int kindVehicleId);
    Page<Vehicle> getAllVehiclePage(String name, String kindVehiclename, String vehicleNumber, Pageable pageable);
}
