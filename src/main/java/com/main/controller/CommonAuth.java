package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Payment;
import com.main.service.PaymentInterface;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")
public class CommonAuth {

	@Autowired
	PaymentInterface s1;
	
	@GetMapping("/fetch/single/payment/data/{id}")
	public ResponseEntity<Payment>FetchSinglePayment(@PathVariable int id)
	{
		Payment p = s1.fetchSinglePaymentData(id);
		
		return new ResponseEntity<Payment>(p,HttpStatus.OK);
	}
	
}
