package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.RouteDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.RouteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/route")
@CrossOrigin("http://localhost:3000")
public class RouteController {
    private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // Get all Route
    @GetMapping
    public List<Route> getAllRoutes(){return routeService.getAllRoute();}

    // Create a new Route
//    @PostMapping
//    public ResponseEntity<Route> createRoute(@RequestBody RouteDTO routeDTO){
//        return new ResponseEntity<>(routeService.saveRoute(routeDTO), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody RouteDTO routeDTO){
        return new ResponseEntity<>(routeService.saveRoute(routeDTO), HttpStatus.CREATED);
    }

    // Get Route by id
    @GetMapping("{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable ("id") int id){
        return new ResponseEntity<>(routeService.getRouteByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllSeatByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Route> routePage = routeService.getAllRoutePage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("routes", routePage.getContent());
        response.put("currentPage", routePage.getNumber());
        response.put("totalItems", routePage.getTotalElements());
        response.put("totalPages", routePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Route by id
    @PutMapping("{id}")
    public ResponseEntity<Route> updateRouteById(@PathVariable ("id") int id, @RequestBody RouteDTO routeDTO){
        return new ResponseEntity<>(routeService.updateRouteByID(routeDTO, id), HttpStatus.OK);
    }

    // Delete route by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable ("id") int id){
        routeService.deleteRouteByID(id);
        return new ResponseEntity<>("Route " + id + " is deleted successfully", HttpStatus.OK);
    }

}
