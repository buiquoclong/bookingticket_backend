package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(VehicleDTO vehicleDTO);
    List<Vehicle> getAllVehicle();
    Vehicle getVehicleByID(int id);
    Vehicle updateVehicleByID(Vehicle vehicle, int id);
    void deleteVehicleByID(int id);
    List<Vehicle> getVehiclesByKindVehicleId(int kindVehicleId);
}
