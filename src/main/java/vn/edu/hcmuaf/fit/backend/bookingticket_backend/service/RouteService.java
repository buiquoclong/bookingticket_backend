package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.RouteDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;

import java.util.List;

public interface RouteService {
    Route saveRoute(RouteDTO routeDTO);
    List<Route> getAllRoute();
    Route getRouteByID(int id);
    Route updateRouteByID(Route route, int id);
    void deleteRouteByID(int id);
}
