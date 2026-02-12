package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.PromotionDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Promotion;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PromotionService {
    Promotion createPromotion(PromotionDTO promotionDTO);
    List<Promotion> getAllPromotion();
    Promotion getPromotionByID(int id);
    Promotion updatePromotionByID(PromotionDTO promotionDTO, int id);
    void deletePromotionByID(int id);
    Page<Promotion> getAllPromotionPage(String code, String description, LocalDateTime startDay, LocalDateTime endDay, Pageable pageable);

    String checkPromotionCode(String code);
}
