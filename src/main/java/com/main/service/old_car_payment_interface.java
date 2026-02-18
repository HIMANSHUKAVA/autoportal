package com.main.service;

import java.util.List;

import com.main.entity.Old_car_payment;
import com.main.entity.PaymentOrderResponseDTO;
import com.main.entity.PaymentRequestDTO;

public interface old_car_payment_interface {

	
	PaymentOrderResponseDTO insertpayment(int user_id , int old_car_id ,PaymentRequestDTO dto );
	
	List<Old_car_payment>viewall();
	
	Old_car_payment updatestatus(int id , String status);
	public Old_car_payment oldcarpaymentlink(int id);
	Old_car_payment fetchsinglepaymentbyoldcar(int id);
	Old_car_payment fetchdetausinglink(int userid , int paymentid);
//	public List<Old_car_payment>viewall();
	public Old_car_payment old_doubleAMount(int id , Double Amount);
	Old_car_payment save(Old_car_payment p);

	
	
	
}
