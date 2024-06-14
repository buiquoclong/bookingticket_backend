package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.KindVehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Contact;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;

import java.util.List;

public interface KindVehicleService {
    KindVehicle createKindVehicle(KindVehicleDTO kindVehicleDTO);
    List<KindVehicle> getAllKindVehicle();
    KindVehicle getKindVehicleByID(int id);
    KindVehicle updateKindVehicleByID(KindVehicleDTO kindVehicleDTO, int id);
    void deleteKindVehicleByID(int id);
    Page<KindVehicle> getAllKindVehiclePage(Pageable pageable);
}
