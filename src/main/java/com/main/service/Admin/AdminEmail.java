package com.main.service.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.main.mail.Admin;

import jakarta.mail.internet.MimeMessage;
@Service
public class AdminEmail {

	@Autowired
    Admin a;

	
	JavaMailSender mailsender;
	
	
    public AdminEmail(JavaMailSender mailsender) {
		this.mailsender = mailsender;
	}


	public void RequestPayment(String toemail , String body)
	{
		
		MimeMessage message = mailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try
		{

		helper.setTo(toemail);
		helper.setSubject("Payment Remainder");
		helper.setText(body);
		
    	 mailsender.send(message);
    	 System.out.println("Remainder send");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void oldcarpaymenteremainder(String email , String body) {
		MimeMessage message = mailsender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try
		{
			helper.setFrom("kavahimanshu24@gmail.com");
			helper.setTo(email);
			helper.setSubject("old Car Payment Remainderd ");
			helper.setText(body);
			
			
			mailsender.send(message);
			
			System.out.println("Old Car Payment Remainder Succesfull");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
