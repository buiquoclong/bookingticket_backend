package vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyRevenueDTO {
    private int month;
    private int year;
    private int revenue;

    public MonthlyRevenueDTO(int month, int revenue, int year) {
        this.month = month;
        this.year = year;
        this.revenue = revenue;
    }
}
