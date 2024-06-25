package com.sidof.security.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author sidof
 * @Since 18/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    public static final String USER_ACCOUNT_VERIFICATION = "USER ACCOUNT VERIFYCATION";

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String sendFrom;
    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendSimpleEmailMessage(String to, String name, String link) throws BadRequestException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(USER_ACCOUNT_VERIFICATION);
            message.setFrom(sendFrom);
            message.setTo(to);
            message.setText("Click here below to activate your account." +
                    "\n  " + link);
            mailSender.send(message);
        } catch (Exception exception) {
            log.error("Failed to send email: {}", exception.getMessage());
            throw new BadRequestException(String.format("Failed to send email: %s", exception.getMessage()));
        }
    }
}
