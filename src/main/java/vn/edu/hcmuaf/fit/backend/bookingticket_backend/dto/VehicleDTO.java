package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VehicleDTO {
    private int id;
    private String name;
    private int kindVehicleId;
    private String vehicleNumber;
    private int value;
    private int status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
