package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDetailDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;

import java.util.List;

public interface BookingDetailService {
    BookingDetail saveBookingDetail(BookingDetailDTO orderDetailDTO);
    List<BookingDetail> getAllBookingDetail();
    BookingDetail getBookingDetailByID(String id);
    BookingDetail updateBookingDetailByID(BookingDetail orderDetail, String id);
    void deleteBookingDetailByID(String id);
    List<BookingDetail> getBookingDetailsByBookingId(int bookingId);
    List<BookingDetail> getAllBookingDetailByUserId(int userId);
    Page<BookingDetail> getBookingDetailsByUserId(int userId, Pageable pageable);
}
