package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDetailDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingRequest;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.MonthlyRevenueDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.BookingSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.*;

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

     @Autowired
     private SeatService seatService;

     @Autowired
     private BookingDetailService bookingDetailService;

     @Autowired
     private TripService tripService;

     @Autowired
     private SeatReservationService seatReservationService;

     @Autowired
     private SeatReservationRepository seatReservationRepository;

     @Autowired
     private SeatRepository seatRepository;

     @Autowired
     private TripRepository tripRepository;

     @Autowired
     @Lazy
     private EmailService emailService;

//    public BookingSeviceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
//        this.bookingRepository = bookingRepository;
//        this.userRepository = userRepository;
//    }


    public BookingSeviceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

//    @Override
//    public Booking createBooking(BookingDTO bookingDTO) {
//        Booking booking = new Booking();
//        booking.setUserName(bookingDTO.getUserName());
//        booking.setEmail(bookingDTO.getEmail());
//        booking.setPhone(bookingDTO.getPhone());
//        booking.setDayBook(LocalDateTime.now());
//        booking.setTotal(bookingDTO.getTotal());
//        booking.setKindPay(bookingDTO.getKindPay());
//        booking.setIsPaid(bookingDTO.getIsPaid());
//        booking.setRoundTrip(bookingDTO.getRoundTrip());
//        booking.setCreatedAt(LocalDateTime.now());
//        booking.setUpdatedAt(LocalDateTime.now());
//
//        if (bookingDTO.getUserId() != 0) {
//            User user = userRepository.findById(bookingDTO.getUserId()).orElseThrow(() ->
//                    new ResourceNotFoundException("User", "Id", bookingDTO.getUserId()));
//            booking.setUser(user);
//        }
//        return bookingRepository.save(booking);
//    }


    @Override
    @Transactional
    public Booking createBooking(BookingRequest request) throws MessagingException {
        // 1. Check conflict
        if (seatService.checkSeatsConflict(request.getTripId(), request.getSelectedSeatIds())) {
            throw new RuntimeException("Một hoặc nhiều ghế đã được đặt hoặc đang chờ đặt!");
        }

        // 2. Tạo hóa đơn chính
        Booking booking = new Booking();
        booking.setUserName(request.getUserName());
        booking.setEmail(request.getEmail());
        booking.setPhone(request.getPhone());
        booking.setTotal(request.getTotal());
        booking.setKindPay(request.getKindPay());
        if ("CASH".equalsIgnoreCase(request.getKindPay())) {
            booking.setKindPay("Thanh toán khi lên xe");
            booking.setIsPaid(request.getIsPaid() != null ? request.getIsPaid() : 0);
        } else {
            booking.setKindPay(request.getKindPay());
            booking.setIsPaid(request.getIsPaid() != null ? request.getIsPaid() : 0);
        }
        booking.setRoundTrip(request.getRoundTrip());
        if (request.getUserId() != 0) {
            User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "Id", request.getUserId()));
            booking.setUser(user);
        }
        booking = bookingRepository.save(booking);

        // 3. Tạo chi tiết và update trip lượt đi
        BookingDetailDTO bookingDetailDTO = new BookingDetailDTO();
        bookingDetailDTO.setBookingId(booking.getId());
        bookingDetailDTO.setTripId(request.getTripId());
        bookingDetailDTO.setRoundTrip(0);
        bookingDetailDTO.setQuantity(request.getSelectedSeatIds().size());
        bookingDetailDTO.setSeatName(request.getSelectedSeatNames());
        bookingDetailDTO.setPrice(request.getTotalPrice());
        bookingDetailDTO.setPointCatch(request.getPickupLocation());
        bookingDetailDTO.setNote(request.getNote());
        bookingDetailService.createBookingDetail(bookingDetailDTO);

        tripService.updateTripSeats(request.getTripId(), request.getSelectedSeatIds());
        insertSeatReservation(booking.getId(), request.getTripId(), request.getSelectedSeatIds());

        // 4. Nếu khứ hồi, tạo chi tiết lượt về
        if (request.getRoundTrip() == 1) {

            BookingDetailDTO bookingDetailDTOReturn = new BookingDetailDTO();
            bookingDetailDTOReturn.setBookingId(booking.getId());
            bookingDetailDTOReturn.setTripId(request.getTripIdReturn());
            bookingDetailDTOReturn.setRoundTrip(1);
            bookingDetailDTOReturn.setQuantity(request.getSelectedSeatIdsReturn().size());
            bookingDetailDTOReturn.setSeatName(request.getSelectedSeatNamesReturn());
            bookingDetailDTOReturn.setPrice(request.getTotalPriceReturn());
            bookingDetailDTOReturn.setPointCatch(request.getPickupLocationReturn());
            bookingDetailDTOReturn.setNote(request.getNoteReturn());
            bookingDetailService.createBookingDetail(bookingDetailDTOReturn);

            tripService.updateTripSeats(request.getTripIdReturn(), request.getSelectedSeatIdsReturn());

            insertSeatReservation(booking.getId(), request.getTripIdReturn(), request.getSelectedSeatIdsReturn());
        }

        // 5. Gửi mail
        if ("CASH".equalsIgnoreCase(request.getKindPay())) {
            // kiểm tra sendMail
            if (Boolean.TRUE.equals(request.getSendMail())) {
                emailService.sendBookingDetailsEmail(booking.getId());
            }
        }

        return booking;
    }
    private void insertSeatReservation(int bookingId, int tripId, List<Integer> seatIds) {
        for (int seatId : seatIds) {
            SeatReservation sr = new SeatReservation();

            Booking booking =  bookingRepository.findById(bookingId).orElseThrow(() ->
                    new ResourceNotFoundException("Booking", "Id", bookingId));
            Seat seat =  seatRepository.findById(seatId).orElseThrow(() ->
                    new ResourceNotFoundException("Seat", "Id", seatId));
            Trip trip =  tripRepository.findById(tripId).orElseThrow(() ->
                    new ResourceNotFoundException("Trip", "Id", tripId));
            sr.setBooking(booking);
            sr.setTrip(trip);
            sr.setSeat(seat);
            sr.setStatus(1);
            sr.setCreatedAt(LocalDateTime.now());
            sr.setUpdatedAt(LocalDateTime.now());
            seatReservationRepository.save(sr);
        }
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
    @Transactional
    public Booking cancelBooking(int bookingId) {
        // Lấy booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", bookingId));

        // 1. Cập nhật trạng thái booking là đã hủy
        booking.setIsPaid(2); // 0: chưa thanh toán, 1: đã thanh toán, 2: đã hủy
        booking.setUpdatedAt(LocalDateTime.now());
        bookingRepository.save(booking);

        // 2. Xóa các seat reservation liên quan và cập nhật emptySeat
        List<SeatReservation> seatReservations = seatReservationRepository.findByBooking_Id(bookingId);
        if (seatReservations != null && !seatReservations.isEmpty()) {
            for (SeatReservation sr : seatReservations) {
                Trip trip = sr.getTrip();
                trip.setEmptySeat(trip.getEmptySeat() + 1); // trả lại ghế
                tripRepository.save(trip);
            }
            seatReservationRepository.deleteAll(seatReservations);
        }

        // 3. BookingDetail giữ nguyên để lưu lịch sử

        return booking; // Trả về booking đã hủy
    }



    @Override
    public Booking updateBookingByID(BookingDTO bookingDTO, int id) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", id));

        // Chỉ update nếu field khác null / khác 0
        if (bookingDTO.getUserName() != null && !bookingDTO.getUserName().isEmpty()) {
            existingBooking.setUserName(bookingDTO.getUserName());
        }
        if (bookingDTO.getEmail() != null && !bookingDTO.getEmail().isEmpty()) {
            existingBooking.setEmail(bookingDTO.getEmail());
        }
        if (bookingDTO.getPhone() != null && !bookingDTO.getPhone().isEmpty()) {
            existingBooking.setPhone(bookingDTO.getPhone());
        }
        if (bookingDTO.getKindPay() != null && !bookingDTO.getKindPay().isEmpty()) {
            existingBooking.setKindPay(bookingDTO.getKindPay());
        }
        if (bookingDTO.getIsPaid() != 0) { // 0 = không thay đổi
            existingBooking.setIsPaid(bookingDTO.getIsPaid());
        }
        if (bookingDTO.getRoundTrip() != 0) {
            existingBooking.setRoundTrip(bookingDTO.getRoundTrip());
        }
        if (bookingDTO.getTotal() != 0) {
            existingBooking.setTotal(bookingDTO.getTotal());
        }
        if (bookingDTO.getUserId() != 0) {
            User user = userRepository.findById(bookingDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "Id", bookingDTO.getUserId()));
            existingBooking.setUser(user);
        }

        if (bookingDTO.getDayBook() != null) {
            existingBooking.setDayBook(bookingDTO.getDayBook());
        }

        // Luôn cập nhật updatedAt
        existingBooking.setUpdatedAt(LocalDateTime.now());

        return bookingRepository.save(existingBooking);
    }


    @Override
    public void deleteBookingByID(int id) {
        bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", id));
        bookingRepository.deleteById(id);
    }
}
