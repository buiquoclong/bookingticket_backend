package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
public class SeatCheckRequest {
    private int tripId;
    private int tripIdReturn;
    private List<Integer> seatIds;
    private List<Integer> seatIdsReturn;
}
