package com.sidof.security.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final String USER_ACCOUNT_VERIFYCATION = "USER ACCOUNT VERIFYCATION";

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String sendFrom;
    private final JavaMailSender mailSender;
    @Async
    @Override
    public void sendSimpleEmailMessage(String to, String name) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(USER_ACCOUNT_VERIFYCATION);
            message.setFrom(sendFrom);
            message.setTo(to);
            message.setText("Click here below to activate your account." +
                    "\n  "+name);
            mailSender.send(message);


        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }

//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(email, true);
//            helper.setTo(to);
//            helper.setSubject("Confirm your email");
//            helper.setFrom("lyfecompany90@gmail.com");
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
////            log.error("failed to send email", e);
//            throw new IllegalStateException("failed to send email");
//        }

    }
}
