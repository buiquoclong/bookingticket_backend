package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private int userId;
    private LocalDateTime dayBook;
    private int total;
    private String kindPay;
    private int status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
