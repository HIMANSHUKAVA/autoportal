package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	@GetMapping("/payment/link/{paymentid}/{userid}")
	public ResponseEntity<Payment>fetchpayment(@PathVariable int paymentid , @PathVariable int userid)
	{
		 Payment p = s1.fetchdetausginlink(paymentid, userid);
		 
		 return ResponseEntity.ok(p);
		 
	}
	
	
	@PutMapping("/pending/amount/{id}")
	public ResponseEntity<Payment>updatependingamount(@PathVariable int id  , @RequestParam("amount") double amount)
	{
		
		Payment s = s1.updateamount(id, amount);
				
		return  new ResponseEntity<Payment>(s , HttpStatus.OK);
	}
	
	
}
