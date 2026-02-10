package com.main.service;

import java.util.List;

import com.main.entity.Addtocart;

public interface Cartinterface {

	
	public Addtocart cartadddeta(int user_id , int car_id);
	public List<Addtocart>viewallcart();
	public void deletedeta(int id);
	
	
}
