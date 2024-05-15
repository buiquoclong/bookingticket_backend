package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

import java.util.List;

public interface CityService {
    City saveCity(City city);
    List<City> getAllCity();
    City getCityByID(int id);
    City updateCityByID(City city, int id);
    void deleteCityByID(int id);
    Page<City> getAllCityPage(Pageable pageable);
}
