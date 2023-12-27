package com.example.sendemail.service;

import com.example.sendemail.entity.EmailDatails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl  implements  EmailService{
   @Autowired private JavaMailSender javaMailSender;

   @Value( "${spring.mail.username}") private String sender;

    @Override
    public String sendSimpleMail(EmailDatails emailDatails) {
        try{

            //creating a simple mail messege
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            // setting up neccesry details
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDatails.getRecipient());
            mailMessage.setText(emailDatails.getMsgBody());
            mailMessage.setSubject(emailDatails.getSubject());

            // sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully";

        }
        catch(Exception e){
            return "Error While Sending Mail";
        }

    }

    //method 2

    @Override
    public String sendMailWithAttachment(EmailDatails emailDatails) {
        //creating a mime msg
        MimeMessage mimeMessage=  javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try{
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDatails.getRecipient());
            mimeMessageHelper.setText(emailDatails.getMsgBody());
            mimeMessageHelper.setSubject(emailDatails.getSubject());

            //addding a attachment
            FileSystemResource file= new FileSystemResource(
                    new File(emailDatails.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
