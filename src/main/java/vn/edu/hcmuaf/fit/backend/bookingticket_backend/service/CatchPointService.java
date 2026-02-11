package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.CatchPointDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.CatchPoint;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Review;

import java.util.List;

public interface CatchPointService {
    CatchPoint createCatchPoint(CatchPointDTO catchPointDTO);
    List<CatchPoint> getAllCatchPoint();
    CatchPoint getCatchPointByID(int id);
    CatchPoint updateCatchPointByID(CatchPointDTO catchPointDTO, int id);
    void deleteCatchPointByID(int id);
    Page<CatchPoint> getAllCatchPointPage(String address, String name, Integer routeId, Pageable pageable);
    List<CatchPoint> getCatchPointsByRouteId(int routeId);
}
