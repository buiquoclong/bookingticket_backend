package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.TripSearchDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.util.List;

public interface TripService {
    Trip saveTrip(Trip trip);
    List<Trip> getAllTrip();
    Trip getTripByID(int id);
    Trip updateTripByID(TripDTO tripDTO, int id);
    void deleteTripByID(int id);
    List<Trip> searchTrips(TripSearchDTO tripSearchDTO);
    List<Trip> searchTripsByTime(TripSearchDTO tripSearchDTO);
    List<Trip> searchTripsByVehicleType(TripSearchDTO tripSearchDTO);

    List<Trip> searchTripsByTimeAndVehicleType(TripSearchDTO tripSearchDTO);
}
