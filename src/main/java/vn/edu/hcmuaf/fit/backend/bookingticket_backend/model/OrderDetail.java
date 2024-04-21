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

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "chuyendi_id")
    private Trip trip;

    @Column(name = "thoigiandi")
    private LocalDateTime timeStart;

    @Column(name = "soghe")
    private int numSeat;

    @Column(name = "tenghe")
    private String seatName;

    @Column(name = "gia")
    private int price;

    @Column(name = "tongtien")
    private int total;

    @Column(name = "noidon")
    private String pointCatch;

    @Column(name = "ghichu")
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
