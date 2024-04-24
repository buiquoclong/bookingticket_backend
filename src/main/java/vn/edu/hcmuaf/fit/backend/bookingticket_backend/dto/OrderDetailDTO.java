package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailDTO {
    private String id;
    private int orderId;
    private int tripId;
    private int numSeat;
    private String seatName;
    private int price;
    private int total;
    private String pointCatch;
    private String note;
    private LocalDateTime createdAt = LocalDateTime.now();
}
