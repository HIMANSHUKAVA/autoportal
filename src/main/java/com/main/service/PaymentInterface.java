package com.main.service;

import java.util.List;

import com.main.entity.Payment;
import com.main.entity.PaymentOrderResponseDTO;
import com.main.entity.PaymentRequestDTO;

public interface PaymentInterface {

	
	public PaymentOrderResponseDTO insertdeta(int user_id , int car_id , PaymentRequestDTO dto);
	
	public List<Payment>viewpayment();
	
	public Payment updatestatus(int id , String status);
	
	public Payment remenderppayment(int id);
	
	public Payment updateamount(int id , double amount);
	
	public Payment fetchSinglePaymentData(int id);
	
	public Payment fetchdetausginlink(int paymentid , int userid);
	
	
}
