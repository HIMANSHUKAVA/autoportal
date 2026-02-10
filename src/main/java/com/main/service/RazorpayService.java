package com.main.service;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
@Service
public class RazorpayService {

	
	@Value("${razorpay.key.id}")
	public String key_id;
	@Value("${razorpay.key.secret}")
	public String secret;
	

	
	public String createOrder(double amount) throws RazorpayException {

		
		System.out.println("KEY ID = " + key_id);
		System.out.println("SECRET = " + secret);

		
	    System.out.println("KEY_ID = " + key_id);
	    System.out.println("SECRET = " + secret);
	    System.out.println("AMOUNT = " + amount);

	    RazorpayClient client = new RazorpayClient(key_id, secret);

	    JSONObject options = new JSONObject();
	    options.put("amount", (int)(amount * 100));
	    options.put("currency", "INR");
	    options.put("receipt", UUID.randomUUID().toString());

	    Order order = client.orders.create(options);

	    System.out.println("RAZORPAY RESPONSE = " + order.toString());

	    return order.get("id");
	}

	
	
}
