package com.ohgiraffers.hitechautoworks.auth.service;

import com.ohgiraffers.hitechautoworks.auth.dto.MailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void check(MailDTO mail) throws IOException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(mail.getAddress());
        helper.setSubject(mail.getTitle());
        helper.setText(mail.getMessage(), true); // HTML 형식으로 설정

        mailSender.send(message);
    }

    public void sendContact(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());

        mailSender.send(message);
    }
}
