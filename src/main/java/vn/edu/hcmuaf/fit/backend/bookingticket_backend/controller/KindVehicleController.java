package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.KindVehicle;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.KindVehicleService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.util.List;

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
    public ResponseEntity<KindVehicle> createKindVehicle(@RequestBody KindVehicle kindVehicle, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Tạo loại xe tên: "+ kindVehicle.getName(), 1);
        logService.createLog(logData);
        return new ResponseEntity<>(kindVehicleService.createKindVehicle(kindVehicle), HttpStatus.CREATED);
    }

    // Get KindVehicle by id
    @GetMapping("{id}")
    public ResponseEntity<KindVehicle> getKindVehicleById(@PathVariable ("id") int id){
        return new ResponseEntity<>(kindVehicleService.getKindVehicleByID(id), HttpStatus.OK);
    }

    // Update KindVehicle by id
    @PutMapping("{id}")
    public ResponseEntity<KindVehicle> updateKindVehicleById(@PathVariable ("id") int id, @RequestBody KindVehicle kindVehicle, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật loại xe Id: "+ id, 2);
        logService.createLog(logData);
        return new ResponseEntity<>(kindVehicleService.updateKindVehicleByID(kindVehicle, id), HttpStatus.OK);
    }

    // Delete KindVehicle by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKindVehicleById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        LogDTO logData =  logService.convertToLogDTO(userId, "Xóa loại xe Id: "+ id, 2);
        logService.createLog(logData);
        kindVehicleService.deleteKindVehicleByID(id);
        return new ResponseEntity<>("KindVehicle " + id + " is deleted successfully", HttpStatus.OK);
    }
}
