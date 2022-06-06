package com.example.springboot.service;

import com.example.springboot.controller.HelloController;
import com.example.springboot.utils.ProjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class MailService {

    @Value("classpath:static/attachment/goldenRay.jpg")
    Resource resourceFile;

    @Autowired
    private JavaMailSender emailSender;

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noReply@gmail.com");
            message.setTo("davidefi91@hotmail.it");
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException e) {
            logger.debug("MailException thrown: " + e.getMessage());
        }
    }

    public void sendMessageWithAttachment(String to, String subject, String text) throws MessagingException {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noReply@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setPriority(1);
            helper.addAttachment("goldenRay.jpg", resourceFile);

            emailSender.send(message);
        } catch (MessagingException e) {
            logger.debug("MailException thrown: " + e.getMessage());
        }
    }

    public void sendMessageWithZippedAttachment(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noReply@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            helper.setPriority(1);
            helper.addAttachment("attachment.zip", ProjectUtils.zipFile(resourceFile));

            emailSender.send(message);
        } catch (MessagingException | IOException e) {
            logger.debug("MailException thrown: " + e.getMessage());
        }
    }
}
