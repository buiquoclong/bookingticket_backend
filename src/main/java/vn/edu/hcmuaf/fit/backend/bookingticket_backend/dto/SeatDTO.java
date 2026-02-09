package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SeatDTO {
    private int id;
    private String name;
    private int kindVehicleId;
    private int status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public SeatDTO(int id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
