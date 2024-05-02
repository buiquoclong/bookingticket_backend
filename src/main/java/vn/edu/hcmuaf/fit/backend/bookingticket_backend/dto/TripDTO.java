package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class TripDTO {
    private int id;
    private int routeId;
    private int vehicleId;
    private LocalDate dayStart;
    private LocalTime timeStart;
    private int price;
    private int driverId;
    private int emptySeat;
    private int status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
