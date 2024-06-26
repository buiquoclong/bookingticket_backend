package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.CatchPointDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.CatchPoint;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.CatchPointService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/catch-point")
@CrossOrigin("http://localhost:3000")
public class CatchPointController {
    private CatchPointService catchPointService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public CatchPointController(CatchPointService catchPointService) {
        this.catchPointService = catchPointService;
    }

    // Get all CatchPoint
    @GetMapping
    public List<CatchPoint> getAllCatchPoints(){
        return catchPointService.getAllCatchPoint();
    }

    // Get CatchPoints by routeId
    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<CatchPoint>> getCatchPointsByRouteId(@PathVariable("routeId") int routeId) {
        return new ResponseEntity<>(catchPointService.getCatchPointsByRouteId(routeId), HttpStatus.OK);
    }

    // Create a new CatchPoint
    @PostMapping
    public ResponseEntity<CatchPoint> createCatchPoint(@RequestBody CatchPointDTO catchPointDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            CatchPoint createdCatchPoint = catchPointService.createCatchPoint(catchPointDTO);

            // Ghi log sau khi tạo thành công
            LogDTO logData = logService.convertToLogDTO(userId, "Tạo điểm đón của tuyến: " + catchPointDTO.getRouteId(), 1);
            logService.createLog(logData);

            return new ResponseEntity<>(createdCatchPoint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get CatchPoint by id
    @GetMapping("{id}")
    public ResponseEntity<CatchPoint> getCatchPointById(@PathVariable ("id") int id){
        return new ResponseEntity<>(catchPointService.getCatchPointByID(id), HttpStatus.OK);
    }

    //get CatchPoint by userId
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<CatchPoint>> getCatchPointByUserId(@PathVariable int userId) {
//        List<CatchPoint> CatchPoints = CatchPointService.getCatchPointByUserId(userId);
//        return new ResponseEntity<>(CatchPoints, HttpStatus.OK);
//    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllCatchPointByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String address) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CatchPoint> catchPointPage = catchPointService.getAllCatchPointPage(address,pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("catchPoints", catchPointPage.getContent());
        response.put("currentPage", catchPointPage.getNumber());
        response.put("totalItems", catchPointPage.getTotalElements());
        response.put("totalPages", catchPointPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update CatchPoint by id
    @PutMapping("{id}")
    public ResponseEntity<CatchPoint> updateCatchPointById(@PathVariable ("id") int id, @RequestBody CatchPointDTO catchPointDTO, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            CatchPoint updatedCatchPoint = catchPointService.updateCatchPointByID(catchPointDTO, id);

            // Ghi log sau khi cập nhật thành công
            LogDTO logData = logService.convertToLogDTO(userId, "Cập nhật điểm đón Id: " + id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>(updatedCatchPoint, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete CatchPoint by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCatchPointById(@PathVariable ("id") int id, HttpServletRequest request){
        String token = jwtTokenUtils.extractJwtFromRequest(request);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        int userId = Integer.parseInt(jwtTokenUtils.extractUserId(token));
        try {
            catchPointService.deleteCatchPointByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData = logService.convertToLogDTO(userId, "Xóa điểm đón Id: " + id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("CatchPoint " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("CatchPoint " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
