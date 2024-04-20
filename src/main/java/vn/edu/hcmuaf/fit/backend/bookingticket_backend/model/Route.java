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
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_route")
    private String name;

    @ManyToOne
    @JoinColumn(name = "diemdi") // đây là khóa ngoại liên kết với City
    private City diemDi;

    @ManyToOne
    @JoinColumn(name = "diemden") // đây là khóa ngoại liên kết với City
    private City diemDen;

    @Column(name = "khoangcach")
    private String khoangCach;

    @Column(name = "thoigiandi")
    private String timeOfRoute;

    @Column(name = "trangthai")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Trip> trips;
}
