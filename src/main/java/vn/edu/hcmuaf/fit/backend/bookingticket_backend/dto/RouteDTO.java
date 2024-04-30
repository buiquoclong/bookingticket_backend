package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RouteDTO {
    private int id;
    private String name;
    private int diemdi;
    private int diemden;
    private String khoangCach;
    private LocalDateTime timeOfRoute;
    private int status;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
