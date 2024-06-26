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
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripSearchDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TripService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/trip")
@CrossOrigin("http://localhost:3000")
public class TripController {
    private TripService tripService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    //Get all Trip
    @GetMapping
    public List<Trip> getAllTrips(){return tripService.getAllTrip();}

    // Create a new Trip
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody TripDTO tripDTO, HttpServletRequest request){

        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            Trip createTrip = tripService.createTrip(tripDTO);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo chuyến đi ", 1);
            logService.createLog(logData);
            return new ResponseEntity<>(createTrip, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Trip By id
    @GetMapping("{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable ("id") int id){
        return new ResponseEntity<>(tripService.getTripByID(id), HttpStatus.OK);
    }

    // phân trang
//    @GetMapping("page")
//    public ResponseEntity<Map<String, Object>> getAllTripByPage(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Trip> tripPage = tripService.getAllTripPage(pageable);
//        Map<String, Object> response = new HashMap<>();
//        response.put("trips", tripPage.getContent());
//        response.put("currentPage", tripPage.getNumber());
//        response.put("totalItems", tripPage.getTotalElements());
//        response.put("totalPages", tripPage.getTotalPages());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllTripByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer routeId,
            @RequestParam(required = false) LocalDate dayStart) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Trip> tripPage = tripService.getTrips(routeId, dayStart, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("trips", tripPage.getContent());
        response.put("currentPage", tripPage.getNumber());
        response.put("totalItems", tripPage.getTotalElements());
        response.put("totalPages", tripPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update Trip by id
    @PutMapping("{id}")
    public ResponseEntity<Trip> updateTripById(@PathVariable ("id") int id, @RequestBody TripDTO tripDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try{
            Trip updateTrip = tripService.updateTripByID(tripDTO, id);

            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật chuyến đi Id: "+ id, 2);
            logService.createLog(logData);
            return new ResponseEntity<>(updateTrip, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("p1/{id}")
    public ResponseEntity<Trip> updateTripById1(@PathVariable ("id") int id, @RequestBody TripDTO tripDTO){
        return new ResponseEntity<>(tripService.updateTripByID(tripDTO, id), HttpStatus.OK);
    }

    // Delete Trip by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTripById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            tripService.deleteTripByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData =  logService.convertToLogDTO(userId, "Xóa chuyến đi Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("Trip " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Trip " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Trip>> searchTrips(@RequestBody TripSearchDTO tripSearchDTO) {
        List<Trip> foundTrips = tripService.searchTrips(tripSearchDTO);
        return new ResponseEntity<>(foundTrips, HttpStatus.OK);
    }
}
