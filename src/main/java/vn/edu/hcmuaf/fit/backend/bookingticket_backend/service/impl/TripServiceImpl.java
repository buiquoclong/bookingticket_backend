package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.TripService;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public List<Trip> getAllTrip() {
        return tripRepository.findAll();
    }

    @Override
    public Trip getTripByID(int id) {
        return tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", id));
    }

    @Override
    public Trip updateTripByID(Trip trip, int id) {
        return null;
    }

    @Override
    public void deleteTripByID(int id) {
        tripRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", id));
        tripRepository.deleteById(id);
    }
}
