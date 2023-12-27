package com.example.sendemail.controller;


import com.example.sendemail.entity.EmailDatails;
import com.example.sendemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired private EmailService emailService;
    //send a simple mail

    @PostMapping("/sendMail")
    public String sendMail( @RequestBody EmailDatails emailDatails){
        String status=emailService.sendSimpleMail(emailDatails);
        return status;
    }

    //sending mail with attachment

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDatails emailDatails){
        String status= emailService.sendMailWithAttachment(emailDatails);
        return status;
    }

}
