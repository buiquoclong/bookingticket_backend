package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailDTO {
    private String OrderDetailId;
    private int orderId;
    private int numSeat;
    private int seatId;
    private int price;
    private int total;
    private LocalDateTime createdAt = LocalDateTime.now();

}
