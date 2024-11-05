package org.example.projeto_trainee.services.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EmailService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private JavaMailSender mailSender;

    @Value ("${spring.mail.sender}")
    private String sender;

    public void sendEmail(String to, String subject, String body) {
        new Thread(
                () -> {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom(sender);
                    message.setTo(to);
                    message.setSubject(subject);
                    message.setText(body);
                    mailSender.send(message);
                    logger.info("Email sent to " + to);
                }
        ).start();
    }
}
