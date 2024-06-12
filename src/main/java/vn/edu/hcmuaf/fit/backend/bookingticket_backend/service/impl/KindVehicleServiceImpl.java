package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.KindVehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.KindVehicleService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.KindVehicleService;

import java.util.List;

@Service
public class KindVehicleServiceImpl implements KindVehicleService {
    private KindVehicleRepository kindVehicleRepository;

    public KindVehicleServiceImpl(KindVehicleRepository kindVehicleRepository) {
        this.kindVehicleRepository = kindVehicleRepository;
    }

    @Override
    public KindVehicle createKindVehicle(KindVehicle KindVehicle) {
        return kindVehicleRepository.save(KindVehicle);
    }

    @Override
    public List<KindVehicle> getAllKindVehicle() { return kindVehicleRepository.findAll();}

    @Override
    public KindVehicle getKindVehicleByID(int id) {
        return kindVehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", id));
    }

    @Override
    public KindVehicle updateKindVehicleByID(KindVehicle kindVehicle, int id) {
        return null;
    }

    @Override
    public void deleteKindVehicleByID(int id) {
        kindVehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", id));
        kindVehicleRepository.deleteById(id);
    }
}
