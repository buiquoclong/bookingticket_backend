package vn.edu.hcmuaf.fit.backend.bookingticket_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@EntityScan
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)  // foreign key in Log table
    private User user;

    @Column(name = "message")
    private String message;

    @Column(name = "level")
    private int level;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
