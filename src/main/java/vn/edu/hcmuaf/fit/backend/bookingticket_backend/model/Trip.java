package vn.edu.hcmuaf.fit.backend.bookingticket_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tuyen_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "phuongtien_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @Column(name = "ngaykhoihanh")
    private LocalDate dayStart;

    @Column(name = "giokhoihanh")
    private LocalTime timeStart;

    @Column(name = "giave")
    private int price;

    @ManyToOne
    @JoinColumn(name = "taixe_id", referencedColumnName = "id")
    private Driver driver;

    @Column(name = "controng")
    private int emptySeat;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<SeatBooked> seatBookedList;

    @JsonIgnore
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
