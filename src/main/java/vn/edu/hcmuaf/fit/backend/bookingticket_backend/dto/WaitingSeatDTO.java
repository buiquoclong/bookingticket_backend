package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WaitingSeatDTO {
    private int id;
    private int tripId;
    private int seatId;
    private LocalDateTime createdAt = LocalDateTime.now();
}
