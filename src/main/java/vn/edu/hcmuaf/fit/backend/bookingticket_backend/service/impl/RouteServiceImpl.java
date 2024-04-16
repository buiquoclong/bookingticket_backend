package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.RouteRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.RouteService;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteByID(int id) {
        return routeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Route", "Id", id));
    }

    @Override
    public Route updateRouteByID(Route route, int id) {
        return null;
    }

    @Override
    public void deleteRouteByID(int id) {
        routeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Route", "Id", id));
        routeRepository.deleteById(id);
    }
}
