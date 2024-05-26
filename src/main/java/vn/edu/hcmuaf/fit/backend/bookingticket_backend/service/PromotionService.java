package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.PromotionDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Promotion;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.util.List;

public interface PromotionService {
    Promotion savePromotion(PromotionDTO promotionDTO);
    List<Promotion> getAllPromotion();
    Promotion getPromotionByID(int id);
    Promotion updatePromotionByID(PromotionDTO promotionDTO, int id);
    void deletePromotionByID(int id);
    Page<Promotion> getAllPromotionPage(Pageable pageable);
}
