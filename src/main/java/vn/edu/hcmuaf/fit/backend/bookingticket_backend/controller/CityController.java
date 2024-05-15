package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/city")
@CrossOrigin("http://localhost:3000")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // Get all City
    @GetMapping
    public List<City> getAllCities(){return cityService.getAllCity();}

    // Create a new City
    @PostMapping
    public ResponseEntity<City> creatCity(@RequestBody City city){
        return new ResponseEntity<>(cityService.saveCity(city), HttpStatus.CREATED);
    }

    // Get City by id
    @GetMapping("{id}")
    public ResponseEntity<City> getCityById(@PathVariable ("id") int id){
        return new ResponseEntity<>(cityService.getCityByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllCityByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<City> cityPage = cityService.getAllCityPage(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("cities", cityPage.getContent());
        response.put("currentPage", cityPage.getNumber());
        response.put("totalItems", cityPage.getTotalElements());
        response.put("totalPages", cityPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update City by id
    @PutMapping("{id}")
    public ResponseEntity<City> updateCityById(@PathVariable ("id") int id, @RequestBody City city){
        return new ResponseEntity<>(cityService.updateCityByID(city, id), HttpStatus.OK);
    }

    // Delete city by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCityById(@PathVariable ("id") int id){
        cityService.deleteCityByID(id);
        return new ResponseEntity<>("City " + id + " is deleted successfully", HttpStatus.OK);
    }

}
