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
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CityService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
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
    public ResponseEntity<City> createCity(@RequestPart("city") CityDTO cityDTO, @RequestPart(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (jwtTokenUtils.isTokenExpired(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtTokenUtils.extractUserId(token);
        Integer userRole = jwtTokenUtils.extractRole(token);

        if (userRole == null ||  (userRole != 2 && userRole != 3)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            City city = cityService.createCity(cityDTO, file);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo thành phố tên: "+ cityDTO.getName(), 1);
            logService.createLog(logData);
            return new ResponseEntity<>(city, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
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
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<City> cityPage = cityService.getAllCityPage(name,pageable);
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
        String token = jwtTokenUtils.extractJwtFromRequest(request);

        if (jwtTokenUtils.isTokenExpired(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtTokenUtils.extractUserId(token);
        Integer userRole = jwtTokenUtils.extractRole(token);

        if (userRole == null ||  (userRole != 2 && userRole != 3)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            City updatedCity = cityService.updateCityByID(cityDTO, file, id);

            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật Thành phố Id: "+ id, 2);
            logService.createLog(logData);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete city by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCityById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (jwtTokenUtils.isTokenExpired(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = jwtTokenUtils.extractUserId(token);
        Integer userRole = jwtTokenUtils.extractRole(token);

        if (userRole == null ||  (userRole != 2 && userRole != 3)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            cityService.deleteCityByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData =  logService.convertToLogDTO(userId, "Xóa thành phố Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("City " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("City " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
