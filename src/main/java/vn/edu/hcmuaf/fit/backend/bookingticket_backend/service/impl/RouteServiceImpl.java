package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.RouteDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.CityRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.RouteRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.RouteService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private RouteRepository routeRepository;
    private CityRepository cityRepository;

//    public RouteServiceImpl(RouteRepository routeRepository) {
//        this.routeRepository = routeRepository;
//    }

    public RouteServiceImpl(RouteRepository routeRepository, CityRepository cityRepository) {
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Route saveRoute(RouteDTO routeDTO) {
        Route route = new Route();
        City diemdi = cityRepository.findById(routeDTO.getDiemdi()).orElseThrow(() ->
                new ResourceNotFoundException("City1", "Id", routeDTO.getDiemdi()));
        City diemden = cityRepository.findById(routeDTO.getDiemden()).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", routeDTO.getDiemden()));
        route.setName(routeDTO.getName());
        route.setDiemDi(diemdi);
        route.setDiemDen(diemden);
        route.setKhoangCach(routeDTO.getKhoangCach());
        route.setTimeOfRoute(routeDTO.getTimeOfRoute());
        route.setStatus(routeDTO.getStatus());
        route.setCreatedAt(LocalDateTime.now());
        route.setUpdatedAt(LocalDateTime.now());
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
