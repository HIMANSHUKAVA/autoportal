package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.main.mail.Admin;
import com.main.repo.contectrepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {


    private final JavaMailSender mailSender;

    @Autowired
    Admin a;
    
//    constructor
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendotp(String toMail, int otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            
            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; background-color: #f9fafb; padding: 20px;'>" +
                        "<div style='max-width: 500px; margin: auto; background: white; border-radius: 10px; " +
                        "box-shadow: 0 2px 10px rgba(0,0,0,0.1); padding: 20px;'>" +
                            "<h2 style='color: #2563eb; text-align: center;'>🔐 OTP Verification</h2>" +
                            "<p style='font-size: 16px; color: #333;'>Dear User,</p>" +
                            "<p style='font-size: 16px; color: #333;'>Your One-Time Password (OTP) for verification is:</p>" +
                            "<h1 style='text-align: center; color: #111827; background: #f3f4f6; padding: 10px 20px; border-radius: 8px;'>" + otp + "</h1>" +
                            "<p style='font-size: 14px; color: #555;'>Please use this OTP within 5 minutes. Do not share it with anyone for security reasons.</p>" +
                            "<hr/>" +
                            "<p style='font-size: 13px; color: #888; text-align: center;'>© 2025 AutoPortal. All rights reserved.</p>" +
                        "</div>" +
                    "</div>";

            helper.setTo(toMail);
            helper.setSubject("AutoPortal OTP Verification");
            helper.setText(htmlContent, true); // HTML mail

            mailSender.send(message);
            System.out.println(" OTP Email Sent Successfully to: " + toMail);
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Failed to send OTP: " + e.getMessage());
        }
    }
    
    
    
    public void sendResetLink(String toMail, String resetLink) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String htmlContent =
                    "<div style='font-family: Arial, sans-serif; background-color: #f9fafb; padding: 20px;'>" +
                        "<div style='max-width: 500px; margin: auto; background: white; border-radius: 10px; " +
                        "box-shadow: 0 2px 10px rgba(0,0,0,0.1); padding: 20px;'>" +
                            "<h2 style='color: #2563eb; text-align: center;'>🔄 Password Reset Request</h2>" +
                            "<p style='font-size: 16px; color: #333;'>Dear User,</p>" +
                            "<p style='font-size: 16px; color: #333;'>We received a request to reset your password.</p>" +
                            "<p style='font-size: 16px; color: #333;'>Click the button below to reset your password:</p>" +
                            "<div style='text-align: center; margin: 30px 0;'>" +
                                "<a href='" + resetLink + "' style='background-color: #2563eb; color: white; padding: 12px 25px; " +
                                "border-radius: 8px; text-decoration: none; font-size: 16px;'>Reset Password</a>" +
                            "</div>" +
                            "<p style='font-size: 14px; color: #555;'>If you did not request a password reset, please ignore this email.</p>" +
                            "<hr/>" +
                            "<p style='font-size: 13px; color: #888; text-align: center;'>© 2025 AutoPortal. All rights reserved.</p>" +
                        "</div>" +
                    "</div>";

            helper.setTo(toMail);
            helper.setSubject("AutoPortal Password Reset Link");
            helper.setText(htmlContent, true);

            mailSender.send(message);
            System.out.println(" Reset Link Email Sent Successfully to: " + toMail);
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Failed to send reset link: " + e.getMessage());
        }
    }
    
    
    public void sendbooking(String tomail  , String subject , String body) throws MessagingException
    {
    	
    	MimeMessage message = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message , true);
    	
    	try
    	{
    	helper.setTo(tomail);
    	helper.setSubject(subject);
    	helper.setText(body);
    	
    	
    	mailSender.send(message);
    	System.out.println("Success");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Failed");
			// TODO: handle exception
		}
    	
    	
    	
    }
    
    public void contectmesagefromuser(String toemail , String subject , String body) throws MessagingException
    {
    	MimeMessage message =  mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message , true);
    	
    	try
    	{
    		helper.setTo(toemail);
    		helper.setSubject(subject);
    		helper.setText(body);
    		
    		
    		mailSender.send(message);
    		System.out.println("Success");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("Failed");
    	}
    }

    
    public void contectmessagefromadmin(String subject , String body)
    {
    	MimeMessage message  =  mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	
    	
    	try
    	{
    		helper.setTo(a.getEmail());
    		helper.setSubject(subject);
    		helper.setText(body);
    		
    	     mailSender.send(message);
    		System.out.println("Success for Admin");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("FAILED FOR ADMIN");
    	}
    	
    }
    
    
    public void PaymentSuccessFromUser(String tomail  , String subject , String body)
    {

    	MimeMessage message  =  mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	
    	try
    	{
    		helper.setTo(tomail);
    		helper.setText(body);
    		helper.setSubject(subject);
    		
    		mailSender.send(message);
    		
    		System.out.println("Payment message Success from user");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
    }
    
    
    public void PaymentSuccesFromAdmin(String body)
    {
    	MimeMessage message =  mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	
    	try
    	{
    		helper.setTo(a.getEmail());
    		helper.setSubject("Payment Status");
    		helper.setText(body);
    		
    		mailSender.send(message);
    	   System.out.println("Payment success from admin");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
    public void BookStatusMessageFromuser(String body , String email)
    {
    	MimeMessage message =  mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	
    	try
    	{
    		helper.setTo(email);
    		helper.setSubject("Book Drive Confirmetion");
    		helper.setText(body);
    		
    		
    		mailSender.send(message);
    		System.out.println("Message Success from Book Drive COnformetion");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    

    public void BookStatusRejectedMessageFromuser(String body , String email)
    {
    	MimeMessage message =  mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	
    	try
    	{
    		helper.setTo(email);
    		helper.setSubject("Book Drive Rejected");
    		helper.setText(body);
    		
    		
    		mailSender.send(message);
    		System.out.println("Message Success from Book Drive Rejected Conformetion");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    
    public void contectMessageFromuser(String body , String email)
    {
    	MimeMessage message =  mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	
    	try
    	{
    		helper.setTo(email);
    		helper.setSubject("About Qyary");
    		helper.setText(body);
    		
    		
    		mailSender.send(message);
    		System.out.println("Message Success from contect user");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}
