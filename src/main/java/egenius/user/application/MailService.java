package egenius.user.application;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendEmail(String email) throws MessagingException;
    Boolean verifyEmailCode(String email, String code);
}
