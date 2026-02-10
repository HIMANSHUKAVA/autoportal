package com.main.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Admin {

	
	@Value("${spring.mail.username}")
	private String email;
	
	@Value("${app.image.base-url}")
	private String imagebaseurl;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagebaseurl() {
		return imagebaseurl;
	}

	public void setImagebaseurl(String imagebaseurl) {
		this.imagebaseurl = imagebaseurl;
	}
	
	
	
	
}
