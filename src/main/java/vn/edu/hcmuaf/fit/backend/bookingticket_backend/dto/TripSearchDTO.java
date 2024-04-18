package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class TripSearchDTO {
    private int diemDiId;
    private int diemDenId;
    private LocalDate dayStart;
//    private LocalDate timeStart;
}
