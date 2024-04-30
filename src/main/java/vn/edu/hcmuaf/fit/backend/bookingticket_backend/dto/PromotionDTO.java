package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PromotionDTO {
    private int id;
    private String code;
    private String description;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private int discount;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
