package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.CityDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CityService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/city")
@CrossOrigin("http://localhost:3000")
public class CityController {
    private CityService cityService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // Get all City
    @GetMapping
    public List<City> getAllCities(){return cityService.getAllCity();}

    // Create a new City
    @PostMapping
    public ResponseEntity<City> createCity(@RequestPart("city") CityDTO cityDTO, @RequestPart("file") MultipartFile file, HttpServletRequest request) {
        try {
            String token = jwtTokenUtils.extractJwtFromRequest(request);
            if (token == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo thành phố tên: "+ cityDTO.getName(), 1);
            logService.createLog(logData);
            City city = cityService.createCity(cityDTO, file);
            return new ResponseEntity<>(city, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
    public ResponseEntity<City> updateCityById(@PathVariable("id") int id,
                                               @RequestPart("city") CityDTO cityDTO,
                                               @RequestPart(value = "file", required = false) MultipartFile file,
                                               HttpServletRequest request) {
        try {
            String token = jwtTokenUtils.extractJwtFromRequest(request);
            if (token == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật Thành phố Id: "+ id, 2);
            logService.createLog(logData);
            City updatedCity = cityService.updateCityByID(cityDTO, file, id);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete city by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCityById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Xóa thành phố Id: "+ id, 2);
        logService.createLog(logData);
        cityService.deleteCityByID(id);
        return new ResponseEntity<>("City " + id + " is deleted successfully", HttpStatus.OK);
    }

}
