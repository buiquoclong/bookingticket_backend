package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.DriverDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.DriverRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.DriverService;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver saveDriver(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setEmail(driverDTO.getEmail());
        driver.setPhone(driverDTO.getPhone());
//        driver.setStatus(driverDTO.getStatus());
        driver.setStatus(1);
        driver.setCreatedAt(LocalDateTime.now());
        driver.setUpdatedAt(LocalDateTime.now());
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> getAllDriver() { return driverRepository.findAll();}

    @Override
    public Driver getDriverByID(int id) {
        return driverRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Driver", "Id", id));
    }

    @Override
    public Driver updateDriverByID(Driver driver, int id) {
        return null;
    }

    @Override
    public void deleteDriverByID(int id) {
        driverRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Driver", "Id", id));
        driverRepository.deleteById(id);
    }
}
