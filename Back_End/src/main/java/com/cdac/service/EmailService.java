package com.cdac.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailService {

    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(String toEmail, String firstName) {
        String subject = "Welcome to Elegant Jewellary!";
        String body = "<h1>Hi " + firstName + ",</h1>" +
                      "<p>Thank you for registering with us. Enjoy shopping!</p>" +
                      "<p>Best Regards,<br>Team Elegant</p>";

        sendEmail(toEmail, subject, body);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);  // Enable HTML content
            helper.setFrom("your_email@gmail.com"); // Ensure the sender's email is set

            mailSender.send(message);
            LOGGER.info("Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Error sending email", e);
        }
    }
}
