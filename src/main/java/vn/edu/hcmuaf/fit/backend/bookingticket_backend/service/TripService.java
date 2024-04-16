package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.util.List;

public interface TripService {
    Trip saveTrip(Trip trip);
    List<Trip> getAllTrip();
    Trip getTripByID(int id);
    Trip updateTripByID(Trip trip, int id);
    void deleteTripByID(int id);
}
