package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.PromotionDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Promotion;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.PromotionService;

import java.util.List;

@RestController
@RequestMapping("api/promotion")
@CrossOrigin("http://localhost:3000")
public class PromotionController {
    private PromotionService promotionService;

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
    public ResponseEntity<Promotion> createPromotion(@RequestBody PromotionDTO PromotionDTO){
        return new ResponseEntity<>(promotionService.savePromotion(PromotionDTO), HttpStatus.CREATED);
    }

    // Get Promotion by id
    @GetMapping("{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable ("id") int id){
        return new ResponseEntity<>(promotionService.getPromotionByID(id), HttpStatus.OK);
    }

    // Update Promotion by id
    @PutMapping("{id}")
    public ResponseEntity<Promotion> updatePromotionById(@PathVariable ("id") int id, @RequestBody Promotion Promotion){
        return new ResponseEntity<>(promotionService.updatePromotionByID(Promotion, id), HttpStatus.OK);
    }

    // Delete Promotion by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePromotionById(@PathVariable ("id") int id){
        promotionService.deletePromotionByID(id);
        return new ResponseEntity<>("Promotion " + id + " is deleted successfully", HttpStatus.OK);
    }
}
