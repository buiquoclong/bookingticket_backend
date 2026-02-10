package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.config.VNPayConfig;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Seat;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingDetailRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.BookingRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.SeatRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.WaitingSeatRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.EmailService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("http://localhost:3000")
public class VNPayController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    @Lazy
    private EmailService emailService;

    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private WaitingSeatRepository waitingSeatRepository;

    @GetMapping("payment-callback")
    public void paymentCallback(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException, MessagingException {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        String bookingIdStr = queryParams.get("bookingId");

        if (bookingIdStr == null || bookingIdStr.isEmpty()) {
            response.sendRedirect("http://localhost:3000/payment-failed");
            return;
        }

        int bookingId = Integer.parseInt(bookingIdStr);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", bookingId));

        // Thanh toán thành công hay thất bại
        if ("00".equals(vnp_ResponseCode)) {
            booking.setIsPaid(1);
            booking.setKindPay("Thanh toán bằng VNPay");
            bookingRepository.save(booking);
            emailService.sendBookingDetailsEmail(booking.getId());
        } else {
            booking.setIsPaid(2);
            bookingRepository.save(booking);
        }
        // Truy vấn BookingDetail theo bookingId
        List<BookingDetail> details = bookingDetailRepository.findByBookingId(bookingId);

        for (BookingDetail detail : details) {
            int tripId = detail.getTrip().getId();
            String[] seatNames = detail.getSeatName().split(","); // ví dụ "A1,A2"

            for (String seatName : seatNames) {
                // Tìm tất cả Seat cùng tên ghế và loại xe đúng (nếu biết kindVehicle)
                List<Seat> seats = seatRepository.findByNameAndKindVehicle_Id(seatName, detail.getTrip().getVehicle().getKindVehicle().getId());

                for (Seat seat : seats) {
                    waitingSeatRepository.deleteByTrip_IdAndSeat_Id(tripId, seat.getId());
                }
            }
        }

        // Redirect về frontend
        if (booking.getIsPaid() == 1) {
            response.sendRedirect("http://localhost:3000/payment-success");
        } else {
            response.sendRedirect("http://localhost:3000/payment-failed");
        }
//        if (bookingId != null && !bookingId.isEmpty()) {
//            Booking booking = bookingRepository.findById(Integer.parseInt(bookingId))
//                    .orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", Integer.parseInt(bookingId)));
//
//            if ("00".equals(vnp_ResponseCode)) {
//                // Thanh toán thành công VNPay
//                booking.setIsPaid(1);
//                booking.setKindPay("Thanh toán bằng VNPay");
//                bookingRepository.save(booking);
//
//
//                // Gửi mail xác nhận
//                emailService.sendBookingDetailsEmail(booking.getId());
//
//                response.sendRedirect("http://localhost:3000/payment-success");
//            } else {
//                // Thanh toán thất bại VNPay
//                booking.setIsPaid(2); // hủy booking nếu muốn
//                bookingRepository.save(booking);
//
//                response.sendRedirect("http://localhost:3000/payment-failed");
//            }
//        }
    }
//    @GetMapping("payment-callback-booking")
//    public void paymentCallbackBooking(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException, MessagingException, MessagingException {
//        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
//        String bookingId = queryParams.get("bookingId");
//
//        if (bookingId != null && !bookingId.isEmpty()) {
//            Booking booking = bookingRepository.findById(Integer.parseInt(bookingId))
//                    .orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", Integer.parseInt(bookingId)));
//
//            if ("00".equals(vnp_ResponseCode)) {
//                // Thanh toán thành công VNPay
//                booking.setIsPaid(1);
//                booking.setKindPay("Thanh toán bằng VNPay");
//                bookingRepository.save(booking);
//
//                // Gửi mail xác nhận
//                emailService.sendBookingDetailsEmail(booking.getId());
//
//                response.sendRedirect("http://localhost:3000/my-booking");
//            } else {
//                // Thanh toán thất bại VNPay
//                booking.setIsPaid(2); // hủy booking nếu muốn
//                bookingRepository.save(booking);
//
//                response.sendRedirect("http://localhost:3000/payment-failed");
//            }
//        }
//    }
@GetMapping("payment-callback-booking")
public void paymentCallbackBooking(@RequestParam Map<String, String> queryParams,
                                   HttpServletResponse response) throws IOException, MessagingException {

    String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
    String bookingIdStr = queryParams.get("bookingId");

    if (bookingIdStr == null || bookingIdStr.isEmpty()) {
        response.sendRedirect("http://localhost:3000/payment-failed");
        return;
    }

    int bookingId = Integer.parseInt(bookingIdStr);

    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", bookingId));

    // Thanh toán thành công hay thất bại
    if ("00".equals(vnp_ResponseCode)) {
        booking.setIsPaid(1);
        booking.setKindPay("Thanh toán bằng VNPay");
        bookingRepository.save(booking);

        // Gửi mail xác nhận
        emailService.sendBookingDetailsEmail(booking.getId());
    } else {
        booking.setIsPaid(2);
        bookingRepository.save(booking);
    }

    // Xóa ghế đang chờ trong waiting_seat
    List<BookingDetail> details = bookingDetailRepository.findByBookingId(bookingId);

    for (BookingDetail detail : details) {
        int tripId = detail.getTrip().getId();
        String[] seatNames = detail.getSeatName().split(","); // ví dụ "A1,A2"

        for (String seatName : seatNames) {
            // Tìm tất cả Seat cùng tên ghế + loại xe đúng
            List<Seat> seats = seatRepository.findByNameAndKindVehicle_Id(
                    seatName,
                    detail.getTrip().getVehicle().getKindVehicle().getId()
            );

            for (Seat seat : seats) {
                waitingSeatRepository.deleteByTrip_IdAndSeat_Id(tripId, seat.getId());
            }
        }
    }

    // Redirect về frontend
    if (booking.getIsPaid() == 1) {
        response.sendRedirect("http://localhost:3000/my-booking");
    } else {
        response.sendRedirect("http://localhost:3000/payment-failed");
    }
}


    @GetMapping("pay")
    public String getPay(@RequestParam("total") int total, @RequestParam("bookingId") int bookingId, HttpServletRequest request) throws UnsupportedEncodingException{
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = total* 100L;
//        String bankCode = "NCB";


        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = request.getRemoteAddr();

        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

//        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef + "||bookingId:" + bookingId);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        Calendar cld = Calendar.getInstance(timeZone);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(timeZone);
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

        System.out.println(paymentUrl);
        return paymentUrl;
    }

    @GetMapping("pay-booking")
    public ResponseEntity<Map<String, String>> getPayNow(@RequestParam("total") int total, @PathParam("bookingId") Integer bookingId, HttpServletRequest request) throws UnsupportedEncodingException{
        System.out.println("bookingId: " + bookingId);
        System.out.println("total: " + total);
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = total* 100L;
        String bankCode = "NCB";

        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = request.getRemoteAddr();

        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl1+"?bookingId="+bookingId);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
        Calendar cld = Calendar.getInstance(timeZone);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(timeZone);
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        Map<String, String> response = new HashMap<>();
        response.put("url", paymentUrl);
        return ResponseEntity.ok(response);
    }
}
