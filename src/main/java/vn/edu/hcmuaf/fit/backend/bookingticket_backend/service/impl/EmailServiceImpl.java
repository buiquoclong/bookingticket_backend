package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Booking;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.BookingDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingDetailService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.BookingService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.EmailService;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingDetailService bookingDetailService;



    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendVerificationEmail(String to, String code) throws MessagingException {
        Context context = new Context();
        context.setVariable("verificationCode", code);

        String content = templateEngine.process("verify", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


        helper.setFrom("roadlineboooking@gmail.com");
        helper.setTo(to);
        helper.setText(content, true);
        helper.setSubject("Xác Nhận Email Đăng Ký");

        mailSender.send(message);
    }
    @Override
    public void sendForgotPasswordEmail(String email, String newPassword) throws MessagingException {
        Context context = new Context();
        context.setVariable("newPassword", newPassword);

        String content = templateEngine.process("forget", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


        helper.setFrom("roadlineboooking@gmail.com");
        helper.setTo(email);
        helper.setText(content, true);
        helper.setSubject("Xác Nhận Email Quên Mật khẩu");

        mailSender.send(message);
    }

    @Override
    public void sendBookingDetailsEmail(int bookingId) throws MessagingException {
        Booking booking = bookingService.getBookingByID(bookingId);
        List<BookingDetail> bookingDetails = bookingDetailService.getBookingDetailsByBookingId(bookingId);

        Context context = new Context();
        context.setVariable("booking", booking);
        context.setVariable("bookingDetails", bookingDetails);

        String content = templateEngine.process("booking", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom("roadlineboooking@gmail.com");
        helper.setTo(booking.getEmail());
        helper.setText(content, true);
        helper.setSubject("Xác nhận đặt vé thành công");

        mailSender.send(message);
    }

    @Override
    public void sendAccountCreatedByAdminEmail(String to, String name, String password) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("email", to);
        context.setVariable("password", password);

        String content = templateEngine.process("create_user", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
        );

        helper.setFrom("roadlineboooking@gmail.com");
        helper.setTo(to);
        helper.setText(content, true);
        helper.setSubject("Tài khoản của bạn đã được tạo thành công");

        mailSender.send(message);
    }

}
