package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.DriverDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;

import java.util.List;

public interface DriverService {
    Driver saveDriver(DriverDTO driverDTO);
    List<Driver> getAllDriver();
    Driver getDriverByID(int id);
    Driver updateDriverByID(Driver driver, int id);
    void deleteDriverByID(int id);
}
