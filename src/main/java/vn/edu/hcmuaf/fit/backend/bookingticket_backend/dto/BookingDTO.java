package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    private int id;
    private String userName;
    private String email;
    private String phone;
    private int userId;
    private LocalDateTime dayBook;
    private int total;
    private String kindPay;
    private int isPaid;
    private int roundTrip;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
