package egenius.user.application;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendEmailAuthentication(String email) throws MessagingException;

    void checkEmail(String email);

    void verifyEmailCode(String email, String code);
}
