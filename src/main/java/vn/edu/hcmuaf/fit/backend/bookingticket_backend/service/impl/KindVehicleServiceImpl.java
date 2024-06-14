package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.KindVehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.KindVehicleRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.KindVehicleService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.KindVehicleService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KindVehicleServiceImpl implements KindVehicleService {
    private KindVehicleRepository kindVehicleRepository;

    public KindVehicleServiceImpl(KindVehicleRepository kindVehicleRepository) {
        this.kindVehicleRepository = kindVehicleRepository;
    }

    @Override
    public KindVehicle createKindVehicle(KindVehicleDTO kindVehicleDTO) {
        KindVehicle kindVehicle = new KindVehicle();
        kindVehicle.setName(kindVehicleDTO.getName());
        kindVehicle.setCreatedAt(LocalDateTime.now());
        kindVehicle.setUpdatedAt(LocalDateTime.now());
        return kindVehicleRepository.save(kindVehicle);
    }

    @Override
    public List<KindVehicle> getAllKindVehicle() { return kindVehicleRepository.findAll();}

    @Override
    public KindVehicle getKindVehicleByID(int id) {
        return kindVehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", id));
    }

    @Override
    public KindVehicle updateKindVehicleByID(KindVehicleDTO kindVehicleDTO, int id) {
        KindVehicle existingKindVehicle = kindVehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", id));
        existingKindVehicle.setName(kindVehicleDTO.getName());
        existingKindVehicle.setUpdatedAt(LocalDateTime.now());
        return kindVehicleRepository.save(existingKindVehicle);
    }

    @Override
    public void deleteKindVehicleByID(int id) {
        kindVehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("KindVehicle", "Id", id));
        kindVehicleRepository.deleteById(id);
    }

    @Override
    public Page<KindVehicle> getAllKindVehiclePage(Pageable pageable) {
        return kindVehicleRepository.findAll(pageable);
    }
}
