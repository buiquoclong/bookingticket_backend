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
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_city")
    private String name;

    @Column(name = "img")
    private String imgUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Danh sách các tuyến đường bắt đầu từ thành phố này
    @OneToMany(mappedBy = "diemDi")
    @JsonIgnore
    private List<Route> routesStartingHere;

    // Danh sách các tuyến đường kết thúc tại thành phố này
    @OneToMany(mappedBy = "diemDen")
    @JsonIgnore
    private List<Route> routesEndingHere;
}
