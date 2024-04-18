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
@Table(name = "orderdetail")
public class OrderDetail {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "soghe")
    private int numSeat;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "gia")
    private int price;

    @Column(name = "tongtien")
    private int total;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
