package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TripSearchDTO {
    private int diemDiId;
    private int diemDenId;
    private LocalDate dayStart;
    private LocalTime timeStartFrom;
    private LocalTime timeStartTo;
    private int kindVehicleId;
    private int sort;
}
