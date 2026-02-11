package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Old_car_payment;
import com.main.entity.PaymentOrderResponseDTO;
import com.main.entity.PaymentRequestDTO;
import com.main.service.old_car_interface;
import com.main.service.old_car_payment_interface;

@RestController
//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")
@RequestMapping("/buyer")
public class old_car_payment_controller {

	
	@Autowired
	old_car_payment_interface r;
	
	
	
	@PostMapping("/oldcar/payment/add/{user_id}/{old_car_id}")
	public ResponseEntity<PaymentOrderResponseDTO>insertdeta(@PathVariable int user_id , @PathVariable int old_car_id , @RequestBody PaymentRequestDTO dto)
	{
		PaymentOrderResponseDTO p1 = r.insertpayment(user_id, old_car_id, dto);
		
		
		return new ResponseEntity(p1 , HttpStatus.OK);
	}
	
	
	
	
	
}
