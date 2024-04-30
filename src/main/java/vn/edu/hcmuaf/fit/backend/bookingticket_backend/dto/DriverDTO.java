package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DriverDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
