package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TripDTO {
    private int tripId;
    private int routeId;
    private int vehicleId;
    private LocalDateTime timeStart;
    private int price;
    private int driverId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}