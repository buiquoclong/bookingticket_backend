package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendVerificationEmail(String to, String code) throws MessagingException;
    void sendForgotPasswordEmail(String email, String newPassword) throws MessagingException;
}
