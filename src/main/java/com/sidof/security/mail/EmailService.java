package com.sidof.security.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author sidof
 * @Since 13/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */

public interface EmailService {

    void sendSimpleEmailMessage(String to,String name);
}
