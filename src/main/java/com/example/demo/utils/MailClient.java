package com.example.demo.utils;

import com.example.demo.dto.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@Component
public class MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    public void sendMail(Email email){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setPort(465);
        mailSender.setUsername(email.getUsername());
        mailSender.setPassword(email.getPassword());
        mailSender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.ssl.enable", "true");
        p.setProperty("mail.smtp.starttls.required", "true");
        p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.socketFactory.fallback", "false");
        p.setProperty("mail.smtp.socketFactory.port", "465");
        mailSender.setJavaMailProperties(p);


        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(email.getUsername());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("send email error" + e.getMessage());
        }
    }
}
