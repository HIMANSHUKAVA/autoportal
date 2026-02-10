package com.main.service;

import java.util.List;

import com.main.entity.Old_car_payment;
import com.main.entity.PaymentOrderResponseDTO;
import com.main.entity.PaymentRequestDTO;

public interface old_car_payment_interface {

	
	PaymentOrderResponseDTO insertpayment(int user_id , int old_car_id ,PaymentRequestDTO dto );
	
	List<Old_car_payment>viewall();
	
	Old_car_payment updatestatus(int id , String status);
	
}
