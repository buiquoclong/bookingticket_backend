package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.RouteDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.util.List;

public interface RouteService {
    Route saveRoute(RouteDTO routeDTO);
    List<Route> getAllRoute();
    Route getRouteByID(int id);
    Route updateRouteByID(Route route, int id);
    void deleteRouteByID(int id);
    Page<Route> getAllRoutePage(Pageable pageable);
}
