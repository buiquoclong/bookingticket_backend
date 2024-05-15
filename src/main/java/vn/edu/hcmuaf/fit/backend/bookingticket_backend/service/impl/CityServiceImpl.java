package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.CityRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CityService;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityByID(int id) {
        return cityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
    }

    @Override
    public City updateCityByID(City city, int id) {
        return null;
    }

    @Override
    public void deleteCityByID(int id) {
        cityRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
        cityRepository.deleteById(id);
    }

    @Override
    public Page<City> getAllCityPage(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }
}
