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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.BookDrive;
import com.main.service.BookServiceInterface;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "http://localhost:5173")
public class DriveBookController {

	@Autowired
	BookServiceInterface s;
	
	@PostMapping("/booking/add/{user_id}/{car_id}")
	public ResponseEntity<BookDrive>AddBook(@PathVariable int user_id , @PathVariable int car_id , @RequestBody BookDrive bd )
	{
		
		BookDrive s1  =  s.AddBook(user_id, car_id, bd);
		
		return new ResponseEntity<>(s1,HttpStatus.CREATED);
	}
	

	
	
}
