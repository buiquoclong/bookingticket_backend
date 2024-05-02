package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.RouteDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.RouteService;

import java.util.List;

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

    // Update Route by id
    @PutMapping("{id}")
    public ResponseEntity<Route> updateRouteById(@PathVariable ("id") int id, @RequestBody Route route){
        return new ResponseEntity<>(routeService.updateRouteByID(route, id), HttpStatus.OK);
    }

    // Delete route by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable ("id") int id){
        routeService.deleteRouteByID(id);
        return new ResponseEntity<>("Route " + id + " is deleted successfully", HttpStatus.OK);
    }

}
