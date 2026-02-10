package com.main.controller;

import java.security.Identity;
import java.util.List;

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

import com.main.entity.contect;
import com.main.service.contectinterface;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "http://localhost:5173")
public class contectcontroller {

	
	@Autowired
	contectinterface c;
	
	
	@GetMapping("/contect")
	public String help()
	{
		return "hellp";
	}
	
	
	@PostMapping("/contect/save")
	public ResponseEntity<contect>insertdeta(@RequestBody contect s)
	{
		contect s1 =  c.insertdeta(s);
		
		return new ResponseEntity<>(s1 , HttpStatus.OK);
	}
	
	
	
	
	
}
