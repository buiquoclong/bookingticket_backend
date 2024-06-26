package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.BookingDetailDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Trip;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingDetailRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.specification.BookingDetailSpecifications;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingDetailService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingDetailService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingDetailServiceImpl implements BookingDetailService {
    private BookingDetailRepository bookingDetailRepository;
    private BookingRepository bookingRepository;
    private TripRepository tripRepository;
    private UserRepository userRepository;

//    public BookingDetailServiceImpl(BookingDetailRepository bookingDetailRepository) {
//        this.bookingDetailRepository = bookingDetailRepository;
//    }


//    public BookingDetailServiceImpl(BookingDetailRepository bookingDetailRepository, BookingRepository bookingRepository, TripRepository tripRepository) {
//        this.bookingDetailRepository = bookingDetailRepository;
//        this.bookingRepository = bookingRepository;
//        this.tripRepository = tripRepository;
//    }


    public BookingDetailServiceImpl(BookingDetailRepository bookingDetailRepository, BookingRepository bookingRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.bookingDetailRepository = bookingDetailRepository;
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingDetail createBookingDetail(BookingDetailDTO bookingDetailDTO) {
        BookingDetail bookingDetail = new BookingDetail();
        Booking booking =  bookingRepository.findById(bookingDetailDTO.getBookingId()).orElseThrow(() ->
                new ResourceNotFoundException("Booking", "Id", bookingDetailDTO.getBookingId()));
        Trip trip =  tripRepository.findById(bookingDetailDTO.getTripId()).orElseThrow(() ->
                new ResourceNotFoundException("Trip", "Id", bookingDetailDTO.getTripId()));
        bookingDetail.setId(generateUniqueBookingDetailId());
        bookingDetail.setBooking(booking);
        bookingDetail.setTrip(trip);
        bookingDetail.setRoundTrip(bookingDetailDTO.getRoundTrip());
        bookingDetail.setQuantity(bookingDetailDTO.getQuantity());
        bookingDetail.setSeatName(bookingDetailDTO.getSeatName());
        bookingDetail.setPrice(bookingDetailDTO.getPrice());
        bookingDetail.setPointCatch(bookingDetailDTO.getPointCatch());
        bookingDetail.setNote(bookingDetailDTO.getNote());
        bookingDetail.setCreatedAt(LocalDateTime.now());
        return bookingDetailRepository.save(bookingDetail);
    }

    public String generateUniqueBookingDetailId() {
        String id;
        do {
            id = generateBookingDetailId();
        } while (bookingDetailRepository.existsById(id));
        return id;
    }

    public String generateBookingDetailId(){
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 8;
        StringBuilder ticketCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char randomChar = characters.charAt(randomIndex);
            ticketCode.append(randomChar);

        }
        return ticketCode.toString();
    }

    // get all detail by bookingid
    @Override
    public List<BookingDetail> getBookingDetailsByBookingId(int bookingId) {
        return bookingDetailRepository.findByBookingId(bookingId);
    }

    // get all detail by userid
    @Override
    public List<BookingDetail> getAllBookingDetailByUserId(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        List<Booking> bookings = user.getBookings();
        List<BookingDetail> allBookingDetails = new ArrayList<>();
        for (Booking booking : bookings) {
            allBookingDetails.addAll(booking.getBookingDetails());
        }
        return allBookingDetails;
    }

    @Override
    public Page<BookingDetail> getBookingDetailsByUserId(int userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        List<Booking> bookings = user.getBookings();
        List<BookingDetail> allBookingDetails = new ArrayList<>();
        for (Booking booking : bookings) {
            allBookingDetails.addAll(booking.getBookingDetails());
        }
        // Sử dụng Pageable để phân trang
//        int start = (int) pageable.getOffset();
//        int end = (start + pageable.getPageSize()) > allBookingDetails.size() ? allBookingDetails.size() : (start + pageable.getPageSize());
//        Page<BookingDetail> page = new PageImpl<>(allBookingDetails.subList(start, end), pageable, allBookingDetails.size());
//        return page;
        return new PageImpl<>(allBookingDetails, pageable, allBookingDetails.size());
    }

//    @Override
//    public Page<BookingDetail> getBookingDetailsByUserId(int userId, Pageable pageable, String id) {
//        User user = userRepository.findById(userId).orElseThrow(() ->
//                new ResourceNotFoundException("User", "Id", userId));
//        List<Booking> bookings = user.getBookings();
//        List<BookingDetail> allBookingDetails = new ArrayList<>();
//        for (Booking booking : bookings) {
//            allBookingDetails.addAll(booking.getBookingDetails());
//        }
//
//        // Sử dụng Specification để lọc theo các tiêu chí
//        if (id == null|| id.isEmpty()) {
//            return new PageImpl<>(allBookingDetails, pageable, allBookingDetails.size());
//        }else {
//            List<BookingDetail> filteredBookingDetails = allBookingDetails.stream()
//                    .filter(bookingDetail -> bookingDetail.getId().contains(id))
//                    .collect(Collectors.toList());
//
//            return new PageImpl<>(filteredBookingDetails, pageable, allBookingDetails.size());
//
//        }
//
//    }
@Override
public Page<BookingDetail> getBookingDetailsByUserId(int userId, Pageable pageable, String id) {
    User user = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "Id", userId));
    List<Booking> bookings = user.getBookings();
    List<BookingDetail> allBookingDetails = new ArrayList<>();
    for (Booking booking : bookings) {
        allBookingDetails.addAll(booking.getBookingDetails());
    }

    // Sử dụng Specification để lọc theo các tiêu chí
    List<BookingDetail> filteredBookingDetails;
    if (id == null || id.isEmpty()) {
        filteredBookingDetails = allBookingDetails;
    } else {
        filteredBookingDetails = allBookingDetails.stream()
                .filter(bookingDetail -> String.valueOf(bookingDetail.getId()).contains(id))
                .collect(Collectors.toList());
    }

    // Thực hiện phân trang thủ công
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), filteredBookingDetails.size());
    List<BookingDetail> pagedBookingDetails;

    if (start > end) {
        pagedBookingDetails = new ArrayList<>(); // Trả về danh sách trống nếu chỉ số bắt đầu vượt quá kích thước danh sách
    } else {
        pagedBookingDetails = filteredBookingDetails.subList(start, end);
    }

    return new PageImpl<>(pagedBookingDetails, pageable, filteredBookingDetails.size());
}



    @Override
    public List<BookingDetail> getAllBookingDetail() {
        return bookingDetailRepository.findAll();
    }

    @Override
    public BookingDetail getBookingDetailByID(String id) {
        return bookingDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("BookingDetail", "Id", id));
    }

    @Override
    public BookingDetail updateBookingDetailByID(BookingDetail bookingDetail, String id) {
        return null;
    }

    @Override
    public void deleteBookingDetailByID(String id) {
        bookingDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("BookingDetail", "Id", id));
        bookingDetailRepository.deleteById(id);
    }
}
