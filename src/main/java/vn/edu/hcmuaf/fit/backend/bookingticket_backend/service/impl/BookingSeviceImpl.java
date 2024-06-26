package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.MonthlyRevenueDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.TripRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.BookingSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingSeviceImpl implements BookingService {
     private BookingRepository bookingRepository;
     private UserRepository userRepository;
     private TripRepository tripRepository;

//    public BookingSeviceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
//        this.bookingRepository = bookingRepository;
//        this.userRepository = userRepository;
//    }


    public BookingSeviceImpl(BookingRepository bookingRepository, UserRepository userRepository, TripRepository tripRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    @Override
    public Booking createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setUserName(bookingDTO.getUserName());
        booking.setEmail(bookingDTO.getEmail());
        booking.setPhone(bookingDTO.getPhone());
        booking.setDayBook(LocalDateTime.now());
        booking.setTotal(bookingDTO.getTotal());
        booking.setKindPay(bookingDTO.getKindPay());
        booking.setIsPaid(bookingDTO.getIsPaid());
        booking.setRoundTrip(bookingDTO.getRoundTrip());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        if (bookingDTO.getUserId() != 0) {
            User user = userRepository.findById(bookingDTO.getUserId()).orElseThrow(() ->
                    new ResourceNotFoundException("User", "Id", bookingDTO.getUserId()));
            booking.setUser(user);
        }
        return bookingRepository.save(booking);
    }


    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingByID(int id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
    }

    // get booking by userId
    @Override
    public List<Booking> getBookingByUserId(int userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Page<Booking> getAllBookingPage(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Page<Booking> getBookingByUserIdPageable(int userId, Pageable pageable) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return new PageImpl<>(bookings, pageable, bookings.size());
    }

    @Override
    public long getTotalBookings() {
        return bookingRepository.count();
    }

    @Override
    public Integer getTotalRevenue() {
        return bookingRepository.findTotalRevenue();
    }

    // thống kê doanh thu theo ngày
    @Override
    public Integer getTotalRevenueByDay(LocalDate date) {
        return bookingRepository.findTotalRevenueByDay(date);
    }

    @Override
    public Integer getTotalRevenueByMonth(YearMonth yearMonth) {
        return bookingRepository.findTotalRevenueByMonth(yearMonth.getYear(), yearMonth.getMonthValue());
    }

    @Override
    public List<MonthlyRevenueDTO> getRevenueForLastNineMonths() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMonths(8).withDayOfMonth(1).toLocalDate().atStartOfDay();

        List<MonthlyRevenueDTO> revenues = bookingRepository.findRevenueForLastNineMonths(startDate, endDate);

        // Chuyển danh sách kết quả thành map để dễ truy cập
        Map<YearMonth, Integer> revenueMap = revenues.stream()
                .collect(Collectors.toMap(
                        r -> YearMonth.of(r.getYear(), r.getMonth()),
                        MonthlyRevenueDTO::getRevenue));

        // Tạo danh sách kết quả cuối cùng với các tháng đầy đủ theo thứ tự từ quá khứ đến hiện tại
        List<MonthlyRevenueDTO> result = new ArrayList<>();
        for (int i = 8; i >= 0; i--) {
            YearMonth yearMonth = YearMonth.now().minusMonths(i);
            double revenue = revenueMap.getOrDefault(yearMonth, 0);
            result.add(new MonthlyRevenueDTO(yearMonth.getMonthValue(), (int) revenue, yearMonth.getYear()));
        }

        return result;
    }

    @Override
    public Integer countPaidBookingsByMonth(YearMonth yearMonth) {
        return bookingRepository.countPaidBookingsByMonth(yearMonth.getYear(), yearMonth.getMonthValue());
    }

    @Override
    public Integer countCancelledBookingsByMonth(YearMonth yearMonth) {
        return bookingRepository.countCancelledBookingsByMonth(yearMonth.getYear(), yearMonth.getMonthValue());
    }

    @Override
    public Page<Booking> getAllBookingPage(Pageable pageable, Integer id, String userName, String email, String phone, Integer userId, String kindPay, Integer isPaid, Integer roundTrip) {
        // Sử dụng Specification để lọc theo các tiêu chí
        Specification<Booking> spec = Specification.where(BookingSpecifications.hasId(id))
                .and(BookingSpecifications.hasUserName(userName))
                .and(BookingSpecifications.hasEmail(email))
                .and(BookingSpecifications.hasPhone(phone))
                .and(BookingSpecifications.hasUserId(userId))
                .and(BookingSpecifications.hasKindPay(kindPay))
                .and(BookingSpecifications.hasIsPaid(isPaid))
                .and(BookingSpecifications.hasRoundTrip(roundTrip));

        return bookingRepository.findAll(spec, pageable);
    }

    @Override
    public Booking updateBookingByID(BookingDTO bookingDTO, int id) {
        Booking existingBooking = bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", id));
        existingBooking.setUserName(bookingDTO.getUserName());
        existingBooking.setEmail(bookingDTO.getEmail());
        existingBooking.setPhone(bookingDTO.getPhone());
        existingBooking.setIsPaid(bookingDTO.getIsPaid());
        existingBooking.setRoundTrip(bookingDTO.getRoundTrip());
        existingBooking.setUpdatedAt(LocalDateTime.now());
        if (bookingDTO.getUserId() != 0) {
            User user = userRepository.findById(bookingDTO.getUserId()).orElseThrow(() ->
                    new ResourceNotFoundException("User", "Id", bookingDTO.getUserId()));
            existingBooking.setUser(user);
        }
        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBookingByID(int id) {
        bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", id));
        bookingRepository.deleteById(id);
    }
}
