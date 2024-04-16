package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogDTO {
    private int logId;
    private User userId;
    private String message;
    private int level;
    private LocalDateTime createdAt = LocalDateTime.now();
}
