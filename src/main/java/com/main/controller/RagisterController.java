package com.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.main.Security.JwtUtil;
import com.main.entity.Ragister;
import com.main.service.ragisterinterface;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class RagisterController {

	@Autowired
	ragisterinterface r;
	
	@Autowired JwtUtil util;
	
	@GetMapping(value="/")
	public String handl()
	{
		return "hello himanshu";
	}
	
	@PostMapping("/add")
	public ResponseEntity<Ragister> insertdeta(@RequestBody Ragister s) {

           Ragister s2 =  r.insertdeta(s);
           
           
           return new ResponseEntity<>(s2,HttpStatus.CREATED);

	}

	
	@PostMapping("/login-person")
	public ResponseEntity<?> logindeta(@RequestParam("email")String email , @RequestParam("password")String password)
	{

		Ragister user = r.login(email, password);
		
		
		String token = util.generatetoken(user.getUsername(), "ROLE_"+user.getRole());
		
		Map<String, Object> response= new HashMap();
		
		response.put("user", user);
		response.put("token", token);
		
		user.setPassword(null);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/reset-password")
	public String resetpassword(@RequestParam("email")String email , @RequestParam("password")String password)
	{
		
		return r.findbyemail(email, password);
		
		
		
		
	}
	
	
	@PostMapping("/check-email")
	public  ResponseEntity<String> checkemail(@RequestParam("email") String email)
	{
		
		Optional<Ragister>finduser =  r.findByEmail1(email);
		
		
		if(finduser.isPresent())
		{
			return  ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("User Already Registered");
		}
		else
		{
	        return ResponseEntity.ok("Email Available");

		}
	}
	
	
}
