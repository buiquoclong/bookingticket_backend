package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookingRequest {
    private String userName;
    private String email;
    private String phone;
    private int total;
    private String kindPay;
    private Integer isPaid;
    private Integer roundTrip;
    private int userId;
    private Boolean sendMail;

    private int tripId;
    private List<Integer> selectedSeatIds;
    private String selectedSeatNames;
    private int totalPrice;
    private String pickupLocation;
    private String note;

    private int tripIdReturn;
    private List<Integer> selectedSeatIdsReturn;
    private String selectedSeatNamesReturn;
    private int totalPriceReturn;
    private String pickupLocationReturn;
    private String noteReturn;
}
