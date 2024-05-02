package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.PromotionDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Promotion;

import java.util.List;

public interface PromotionService {
    Promotion savePromotion(PromotionDTO promotionDTO);
    List<Promotion> getAllPromotion();
    Promotion getPromotionByID(int id);
    Promotion updatePromotionByID(Promotion promotion, int id);
    void deletePromotionByID(int id);
}
