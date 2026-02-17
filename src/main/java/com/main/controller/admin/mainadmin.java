package com.main.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.main.Security.JwtUtil;
import com.main.entity.Cars;
import com.main.entity.Payment;
import com.main.entity.admin.Admins;
import com.main.service.getcarshow;
import com.main.service.Admin.Adminservice;

import ch.qos.logback.classic.pattern.Util;

@RestController
//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")
@RequestMapping("/auth")
public class mainadmin {

	@Autowired
	Adminservice s;
	
	@Autowired
	getcarshow a;
	
	@Autowired JwtUtil util;
	
	@PostMapping("/insertadmin")
	public ResponseEntity<Admins>insertdeta(@RequestBody Admins a)
	{
		Admins k = s.insertadmin(a);
		
		return new ResponseEntity(k , HttpStatus.CREATED);
	}
	
	
	
	
	

	@DeleteMapping("/delete")
	public void deleteadmin(@PathVariable int id)
	{
		s.Deleteadmin(id);
		
		
	}
	
	@PostMapping("/login-check")
	public ResponseEntity<?> logincheck(@RequestBody Admins request) {

	    Admins s3 = s.logincheck(request.getEmail(), request.getPassword());

	    if (s3 == null) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body("Invalid email or password");
	    }

	    String token = util.generatetoken(s3.getUsername(), s3.getRole());

	    s3.setPassword(null);

	    Map<String, Object> response = new HashMap<>();
	    response.put("user", s3);
	    response.put("token", token);

	    return ResponseEntity.ok(response);
	}


	
	
	
	

	
}
