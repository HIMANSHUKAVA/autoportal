package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Old_car_payment;
import com.main.entity.Payment;
import com.main.service.PaymentInterface;
import com.main.service.RazorpayService;
import com.main.service.old_car_payment_interface;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")
public class CommonAuth {

	@Autowired
	PaymentInterface s1;
	
	@Autowired
	old_car_payment_interface old;
	
	@Autowired
	RazorpayService s5;
	
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
	
	
	
	
	
	@GetMapping("/fetch/oldcarlink/payment/{id}")
	public  ResponseEntity<Old_car_payment>fetchdetaoldcar(@PathVariable int id)
	{
		Old_car_payment op =  old.fetchsinglepaymentbyoldcar(id);
		
		
		return ResponseEntity.ok(op);
	}
	
	
	@GetMapping("/payment/linkbymobail/{paymentid}/{userid}")
	public ResponseEntity<Old_car_payment> fetchpaymentbyoldcar(
	        @PathVariable int paymentid,
	        @PathVariable int userid)
	{
	     Old_car_payment p = old.fetchdetausinglink(userid, paymentid);
	     return ResponseEntity.ok(p);
	}

	
	@PutMapping("/update/oldcarpaidamount/{id}")
	public ResponseEntity<Old_car_payment>updateoldcaramount(@PathVariable int id  , @RequestParam("amount")
	Double amount
	)
	{
		Old_car_payment p = old.old_doubleAMount(id,amount);
		
		return  ResponseEntity.ok(p);
		
	}
	
	
	
	@PutMapping("/pending/amount/{id}")
	public ResponseEntity<Payment>updatependingamount(@PathVariable int id  , @RequestParam("amount") double amount)
	{
		
		Payment s = s1.updateamount(id, amount);
				
		return  new ResponseEntity<Payment>(s , HttpStatus.OK);
	}
	
	@PostMapping("/create-oldcar-pending-order/{paymentid}")
	public ResponseEntity<String> createPendingOrder(@PathVariable int paymentid) throws RazorpayException {

	    Old_car_payment p = old.fetchsinglepaymentbyoldcar(paymentid);

	    if (p.getPendingAmount() <= 0) {
	        return ResponseEntity.badRequest().body("No pending amount");
	    }

	    String orderId = s5.createOrder(p.getPendingAmount());

	    p.setRazorpayOrderId(orderId);
	    old.save(p);
	    

	    return ResponseEntity.ok(orderId);
	}

	
}
