package com.main.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.main.service.EmailService;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")

public class EmailController {
	
	private int ganrateotp;
	private EmailService service;
	
	
	@GetMapping("/generate-otp")
	public String generateotp(@RequestParam("email")String email)
	{
	
		ganrateotp = 100000 + new Random().nextInt(900000);
		
		System.out.println(ganrateotp);

	  
	  service.sendotp(email, ganrateotp);
	  return "Otp Sent Successfully"+email;
	  
	}
	
	@PostMapping("/varify-otp")
	public String varifyotp(@RequestBody otprequest request)
	{
		
		
//		
		if(request.getOtp() == ganrateotp)
		{
		
			return "Otp Varified Successfully";
		}
		else
		{
			
			return "Otp Is Wrong Please Try Again Lettar";
		}
		
		
		
	}
	
	
	
	 @PostMapping("/verify-reset-link")
	 public String resetpass(@RequestBody otprequest request)
	 {	
		 if(request.getOtp()==ganrateotp)
		 {
			 String resetLink = "http://172.26.14.87:5173/reset-password?email=" + request.getEmail();
			 
			 
			 service.sendResetLink(request.getEmail() , resetLink);
			 
			 return "Reset Link Sent Successfully to " + request.getEmail();
			 
//			 reset-password'
		 }
		 else
		 {
			 return "Invalid OTP! Cannot send reset link.";
		 }
	 }
	
	 
	 
	 
	 
	
	public EmailController(EmailService service)
	{
		this.service = service;
		
		
	}
	
	public  static class otprequest
	{
		private int otp;
        private String email;
		
		public int getOtp() {
			return otp;
		}

		public void setOtp(int otp) {
			this.otp = otp;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		
		
	}
	
	
	

}
