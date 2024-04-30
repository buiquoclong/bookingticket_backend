package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDetailDTO {
    private String id;
    private int bookingId;
    private int tripId;
    private int roundTrip;
    private int quantity;
    private String seatName;
    private int price;
    private String pointCatch;
    private String note;
    private LocalDateTime createdAt = LocalDateTime.now();
}
