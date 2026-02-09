package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.KindVehicleDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Contact;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.KindVehicleService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/kindVehicle")
@CrossOrigin("http://localhost:3000")
public class KindVehicleController {
    private KindVehicleService kindVehicleService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public KindVehicleController(KindVehicleService kindVehicleService) {
        this.kindVehicleService = kindVehicleService;
    }

    // Get all KindVehicle
    @GetMapping
    public List<KindVehicle> getAllKindVehicles(){
        return kindVehicleService.getAllKindVehicle();
    }

    // Create a new KindVehicle
    @PostMapping
    public ResponseEntity<KindVehicle> createKindVehicle(@RequestBody KindVehicleDTO kindVehicleDTO, HttpServletRequest request){
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
            KindVehicle createKindVehicle = kindVehicleService.createKindVehicle(kindVehicleDTO);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo loại xe tên: "+ kindVehicleDTO.getName(), 1);
            logService.createLog(logData);
            return new ResponseEntity<>(createKindVehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get KindVehicle by id
    @GetMapping("{id}")
    public ResponseEntity<KindVehicle> getKindVehicleById(@PathVariable ("id") int id){
        return new ResponseEntity<>(kindVehicleService.getKindVehicleByID(id), HttpStatus.OK);
    }

    // Update KindVehicle by id
    @PutMapping("{id}")
    public ResponseEntity<KindVehicle> updateKindVehicleById(@PathVariable ("id") int id, @RequestBody KindVehicleDTO kindVehicleDTO, HttpServletRequest request){
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
        try{
            KindVehicle updateKindVehicle = kindVehicleService.updateKindVehicleByID(kindVehicleDTO, id);

            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật loại xe Id: "+ id, 2);
            logService.createLog(logData);
            return new ResponseEntity<>(updateKindVehicle, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete KindVehicle by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKindVehicleById(@PathVariable ("id") int id, HttpServletRequest request){
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
            kindVehicleService.deleteKindVehicleByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData =  logService.convertToLogDTO(userId, "Xóa loại xe Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("KindVehicle " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("KindVehicle " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllKindVehicleByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<KindVehicle> kindVehiclePage = kindVehicleService.getAllKindVehiclePage(name,pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("kindVehicles", kindVehiclePage.getContent());
        response.put("currentPage", kindVehiclePage.getNumber());
        response.put("totalItems", kindVehiclePage.getTotalElements());
        response.put("totalPages", kindVehiclePage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
