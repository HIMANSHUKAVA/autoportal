package com.main.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.main.mail.Admin;

import jakarta.mail.internet.MimeMessage;

@Service
public class sellerEmail {

	 private final JavaMailSender mailSender;
	 
	 
	    

	 public sellerEmail(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	 @Autowired
	 Admin a;
	 
	 public void carrequestmessagefromsellar(String tomail , String body)
	 {
		 MimeMessage message = mailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message);
		 
		 
		 try
		 {
			 helper.setTo(tomail);
			 helper.setSubject( "Car Request Submitted - AUTO PORTAL");
			 helper.setText(body);
			 
			 mailSender.send(message);
			 
			 System.out.println("Message succcess from sellar");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	 }
	 
	 public void carrequestmessagefromadmin(String body)
	 {
		 MimeMessage message = mailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message);
		 String subject = "New Car Selling Request - Approval Required";

		 try
		 {
			 helper.setTo(a.getEmail());
			 helper.setSubject(subject);
			 helper.setText(body);
			 
			 mailSender.send(message);
			 
			 System.out.println("Message succcess from Admin");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
	 }

	
}
