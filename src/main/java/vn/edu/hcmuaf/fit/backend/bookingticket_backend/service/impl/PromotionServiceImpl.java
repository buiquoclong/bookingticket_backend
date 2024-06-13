package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.PromotionDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Promotion;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.PromotionRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.PromotionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    private PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();
        promotion.setCode(generateCode());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setStartDay(promotionDTO.getStartDay());
        promotion.setEndDay(promotionDTO.getEndDay());
        promotion.setDiscount(promotionDTO.getDiscount());
        promotion.setCreatedAt(LocalDateTime.now());
        promotion.setUpdatedAt(LocalDateTime.now());
        return promotionRepository.save(promotion);
    }
    public String generateCode(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 8;
        StringBuilder ticketCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            ticketCode.append(randomChar);

        }
        return ticketCode.toString();
    }

    @Override
    public List<Promotion> getAllPromotion() { return promotionRepository.findAll();}

    @Override
    public Promotion getPromotionByID(int id) {
        return promotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion", "Id", id));
    }

    @Override
    public Promotion updatePromotionByID(PromotionDTO promotionDTO, int id) {
        Promotion existingPromotion = promotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion", "Id", id));
        existingPromotion.setCode(promotionDTO.getCode());
        existingPromotion.setDescription(promotionDTO.getDescription());
        existingPromotion.setStartDay(promotionDTO.getStartDay());
        existingPromotion.setEndDay(promotionDTO.getEndDay());
        existingPromotion.setDiscount(promotionDTO.getDiscount());
        existingPromotion.setUpdatedAt(LocalDateTime.now());
        return promotionRepository.save(existingPromotion);
    }

    @Override
    public void deletePromotionByID(int id) {
        promotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion", "Id", id));
        promotionRepository.deleteById(id);
    }

    @Override
    public Page<Promotion> getAllPromotionPage(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    @Override
    public String checkPromotionCode(String code) {
        List<Promotion> promotions = promotionRepository.findAll();
        for (Promotion promotion : promotions) {
            if (promotion.getCode().equalsIgnoreCase(code)) {
                LocalDateTime now = LocalDateTime.now();
                if (now.isAfter(promotion.getStartDay()) && now.isBefore(promotion.getEndDay())) {
                    return String.valueOf(promotion.getDiscount());
                }
            }
        }
        return "NULL";
    }
}
