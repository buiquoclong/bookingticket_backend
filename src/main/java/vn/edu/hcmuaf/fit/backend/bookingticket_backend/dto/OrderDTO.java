package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    private int orderId;
    private int tripId;
    private int userId;
    private LocalDateTime dayBook = LocalDateTime.now();
    private LocalDateTime timeStart;
    private int status;
    private String pointCatch;
    private String note;
    private String kindPay;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
