package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.CityDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface CityService {
    City createCity(CityDTO cityDTO, MultipartFile file) throws IOException, GeneralSecurityException;
    List<City> getAllCity();
    City getCityByID(int id);
    City updateCityByID(CityDTO cityDTO, MultipartFile file, int id) throws IOException, GeneralSecurityException;
    void deleteCityByID(int id);
    Page<City> getAllCityPage(String name, Pageable pageable);
}
