/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymaventutorial.app.Services;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Keton
 */
@Service
public class AppMailService{
    
    @Autowired
    private JavaMailSender emailSender;

    public AppMailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean sendEmail(String sendTo, String subject, String simpleContent){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(sendTo);
            message.setSubject(subject);
            message.setText(simpleContent);
            emailSender.send(message);
            return true;
        }catch(MailException ME){
            Logger.getLogger(AppMailService.class.getName()).log(Level.SEVERE, null, ME);
            System.err.println("-----------------Something wents wrong!!!!");
        }
        return false;
    }
    
    
    
}
