package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.RouteDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.VehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Route;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Vehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.RouteService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/route")
@CrossOrigin("http://localhost:3000")
public class RouteController {
    private RouteService routeService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

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
    public ResponseEntity<Route> createRoute(@RequestBody RouteDTO routeDTO, HttpServletRequest request){

        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            Route createRoute = routeService.createRoute(routeDTO);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo tuyến tên: "+ routeDTO.getName(), 1);
            logService.createLog(logData);
            return new ResponseEntity<>(createRoute, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Route by id
    @GetMapping("{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable ("id") int id){
        return new ResponseEntity<>(routeService.getRouteByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllRouteByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Route> routePage = routeService.getAllRoutePage(name, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("routes", routePage.getContent());
        response.put("currentPage", routePage.getNumber());
        response.put("totalItems", routePage.getTotalElements());
        response.put("totalPages", routePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Route by id
    @PutMapping("{id}")
    public ResponseEntity<Route> updateRouteById(@PathVariable ("id") int id, @RequestBody RouteDTO routeDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try{
            Route updateRoute = routeService.updateRouteByID(routeDTO, id);

            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật tuyến Id: "+ id, 2);
            logService.createLog(logData);
            return new ResponseEntity<>(updateRoute, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete route by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            routeService.deleteRouteByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData =  logService.convertToLogDTO(userId, "Xóa tuyến Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("Route " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Route " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/active")
    public List<Route> getActiveRoutes() {
        return routeService.getActiveRoutes();
    }

}
