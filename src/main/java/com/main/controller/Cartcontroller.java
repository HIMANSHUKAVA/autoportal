package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Addtocart;
import com.main.service.Cartinterface;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")

public class Cartcontroller {

  
	
	@Autowired
	Cartinterface s;



	
	
	@PostMapping("/addtocart/add/{user_id}/{car_id}")
	public ResponseEntity<Addtocart>insertdeta(@PathVariable int user_id , @PathVariable int car_id)
	{
		
		
		Addtocart c = s.cartadddeta(user_id, car_id);
		
		
		return new ResponseEntity<>(c,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/addtocart/view")
	public ResponseEntity<List<Addtocart>>  viewall()
	{
		List<Addtocart>s1 = s.viewallcart();
		
		return new ResponseEntity<>(s1 , HttpStatus.OK);
	}
	
	@DeleteMapping("/addtocart/delete/{id}")
	public void deletedeta(@PathVariable int id)
	{
	      s.deletedeta(id);	
	}
	
	
}
