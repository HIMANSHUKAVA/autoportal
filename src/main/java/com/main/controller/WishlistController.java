package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Wishlist;
import com.main.service.WishlistInterface;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "http://localhost:5173")
public class WishlistController {

	
	@Autowired
	WishlistInterface r;
	
	
	@GetMapping("/wishlist")
	public String frontcontroller() {
		// TODO Auto-generated method stub
		return "hello himanshu kava";
	}
	

	
	@PostMapping("/wishlist/add/{user_id}/{car_id}")
	public ResponseEntity<Wishlist>insertdeta(@PathVariable int user_id  , @PathVariable int car_id)
	{
		
		
		
		Wishlist s1 =  r.Insertdeta(user_id, car_id);
		
		return  
				new ResponseEntity<>(s1, HttpStatus.OK);
	}
	
	
	@GetMapping("/wishlist/view")
	public ResponseEntity<List<Wishlist>>Viewall()
	{
		List<Wishlist>v = r.ViewWishlist();
		
		return new ResponseEntity<>(v , HttpStatus.OK);
	}
	
	
    @DeleteMapping("/wishlist/delete/{id}")
    public void deletewishlist(@PathVariable int id)
    {
    	r.deletewishlist(id);
    	
    }
    
    
 }
