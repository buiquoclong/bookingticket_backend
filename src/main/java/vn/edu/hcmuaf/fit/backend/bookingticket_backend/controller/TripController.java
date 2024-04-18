package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripSearchDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TripService;

import java.util.List;

@RestController
@RequestMapping("api/trip")
@CrossOrigin("http://localhost:3000")
public class TripController {
    private TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    //Get all Trip
    @GetMapping
    public List<Trip> getAllTrips(){return tripService.getAllTrip();}

    // Create a new Trip
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip){
        return new ResponseEntity<>(tripService.saveTrip(trip), HttpStatus.CREATED);
    }

    // Get Trip By id
    @GetMapping("{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable ("id") int id){
        return new ResponseEntity<>(tripService.getTripByID(id), HttpStatus.OK);
    }

    // Update Trip by id
    @PutMapping("{id}")
    public ResponseEntity<Trip> updateTripById(@PathVariable ("id") int id, @RequestBody Trip trip){
        return new ResponseEntity<>(tripService.updateTripByID(trip, id), HttpStatus.OK);
    }

    // Delete Trip by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTripById(@PathVariable ("id") int id){
        tripService.deleteTripByID(id);
        return new ResponseEntity<>("Trip " + id + " is deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Trip>> searchTrips(@RequestBody TripSearchDTO tripSearchDTO) {
        List<Trip> foundTrips = tripService.searchTrips(tripSearchDTO);
        return new ResponseEntity<>(foundTrips, HttpStatus.OK);
    }
}