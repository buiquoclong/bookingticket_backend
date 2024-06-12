package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Driver;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;

import java.util.List;

public interface KindVehicleService {
    KindVehicle createKindVehicle(KindVehicle kindVehicle);
    List<KindVehicle> getAllKindVehicle();
    KindVehicle getKindVehicleByID(int id);
    KindVehicle updateKindVehicleByID(KindVehicle kindVehicle, int id);
    void deleteKindVehicleByID(int id);
}
