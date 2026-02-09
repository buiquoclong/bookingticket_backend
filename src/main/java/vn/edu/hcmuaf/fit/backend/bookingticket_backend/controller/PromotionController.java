package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.LogDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.PromotionDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Promotion;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.LogService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.PromotionService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/promotion")
@CrossOrigin("http://localhost:3000")
public class PromotionController {
    private PromotionService promotionService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private LogService logService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    // Get all Promotion
    @GetMapping
    public List<Promotion> getAllPromotions(){
        return promotionService.getAllPromotion();
    }

    // Create a new Promotion
    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@RequestBody PromotionDTO promotionDTO, HttpServletRequest request){
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
            Promotion createPromotion = promotionService.createPromotion(promotionDTO);

            LogDTO logData =  logService.convertToLogDTO(userId, "Tạo mã giảm giá cho: "+ promotionDTO.getDescription(), 1);
            logService.createLog(logData);

            return new ResponseEntity<>(createPromotion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Promotion by id
    @GetMapping("{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable ("id") int id){
        return new ResponseEntity<>(promotionService.getPromotionByID(id), HttpStatus.OK);
    }

    // phân trang
    @GetMapping("page")
    public ResponseEntity<Map<String, Object>> getAllPromotionByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDay,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDay
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Promotion> promotionPage = promotionService.getAllPromotionPage(code, description, startDay, endDay, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("promotions", promotionPage.getContent());
        response.put("currentPage", promotionPage.getNumber() + 1);
        response.put("totalItems", promotionPage.getTotalElements());
        response.put("totalPages", promotionPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Update Promotion by id
    @PutMapping("{id}")
    public ResponseEntity<Promotion> updatePromotionById(@PathVariable ("id") int id, @RequestBody PromotionDTO promotionDTO, HttpServletRequest request){
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
            Promotion updatePromotion = promotionService.updatePromotionByID(promotionDTO, id);

            LogDTO logData =  logService.convertToLogDTO(userId, "Cập nhật mã giảm giá Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>(updatePromotion, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Promotion by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePromotionById(@PathVariable ("id") int id, HttpServletRequest request){
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
            promotionService.deletePromotionByID(id);

            // Ghi log sau khi hành động thành công
            LogDTO logData =  logService.convertToLogDTO(userId, "Xóa mã giảm giá Id: "+ id, 2);
            logService.createLog(logData);

            return new ResponseEntity<>("Promotion " + id + " is deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Promotion " + id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // check Mã giảm giá
    @GetMapping("/check")
    public ResponseEntity<String> checkPromotionCode(@RequestParam String code) {
//        String discount = promotionService.checkPromotionCode(code);
//        return new ResponseEntity<>(discount, HttpStatus.OK);
        String discount = promotionService.checkPromotionCode(code);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }
}
