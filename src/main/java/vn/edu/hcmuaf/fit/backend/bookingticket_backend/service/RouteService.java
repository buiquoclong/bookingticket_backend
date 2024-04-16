package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;

import java.util.List;

public interface RouteService {
    Route saveRoute(Route route);
    List<Route> getAllRoute();
    Route getRouteByID(int id);
    Route updateRouteByID(Route route, int id);
    void deleteRouteByID(int id);
}
