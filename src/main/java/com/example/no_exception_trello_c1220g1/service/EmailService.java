package com.example.no_exception_trello_c1220g1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void sendEmail(String email) {
                    // Create a Simple MailMessage.
                    SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(MyConstants.FRIEND_EMAIL);
                    message.setTo(email);
                    message.setSubject("INVITATION TO GROUP.");
                    message.setText("You received an invitation to the trello group. Access now: http://localhost:8080");

                    // Send Message!
                    this.emailSender.send(message);
    }
}
