package com.example.sendemail.service;

import com.example.sendemail.entity.EmailDatails;

public interface EmailService {
    String  sendSimpleMail(EmailDatails emailDatails);

    String sendMailWithAttachment(EmailDatails emailDatails);
}
