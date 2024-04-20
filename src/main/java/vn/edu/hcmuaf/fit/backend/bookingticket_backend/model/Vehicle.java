package vn.edu.hcmuaf.fit.backend.bookingticket_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EntityScan
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "loaixe")
    private String name;

    @Column(name = "bienso")
    private String vehicleNumber;

    @Column(name = "succhua")
    private int value;

    @Column(name = "controng")
    private int emptySeat;

    @Column(name = "trangthai")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    @JsonIgnore
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @JsonIgnore
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Trip trip;
}
