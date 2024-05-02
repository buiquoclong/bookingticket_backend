package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

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
    public Promotion savePromotion(PromotionDTO promotionDTO) {
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
    public Promotion updatePromotionByID(Promotion promotion, int id) {
        return null;
    }

    @Override
    public void deletePromotionByID(int id) {
        promotionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Promotion", "Id", id));
        promotionRepository.deleteById(id);
    }
}
