package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.CityDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

import java.io.IOException;
import java.util.List;

public interface CityService {
    City saveCity(CityDTO cityDTO, MultipartFile file) throws IOException;
    List<City> getAllCity();
    City getCityByID(int id);
    City updateCityByID(CityDTO cityDTO, MultipartFile file, int id) throws IOException;
    void deleteCityByID(int id);
    Page<City> getAllCityPage(Pageable pageable);
}
