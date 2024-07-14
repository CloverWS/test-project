package com.example.demo.controller;

import com.example.demo.dto.Email;
import com.example.demo.utils.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailController {

    @Autowired
    private MailClient mailClient;

    @PostMapping("/api/send")
    public void send(@RequestBody Email email){
        mailClient.sendMail(email);
    }
}
