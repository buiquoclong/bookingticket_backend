package vn.edu.hcmuaf.fit.backend.bookingticket_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@EntityScan
@Table(name = "booking_detail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingDetail {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
//    @JsonIgnoreProperties("orderDetails")
    @JsonBackReference
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(name = "round_trip_ticket")
    private int roundTrip;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "price")
    private int price;


    @Column(name = "noidon")
    private String pointCatch;

    @Column(name = "ghichu")
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
