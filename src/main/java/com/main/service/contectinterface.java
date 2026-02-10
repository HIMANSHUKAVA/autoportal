package com.main.service;

import java.util.List;

import com.main.entity.contect;

public interface contectinterface {

	
	contect insertdeta(contect c);
	List<contect>viewcontect();
	public void deletecontect(int id);
	contect updaecpntect(int id , String status , String message);
}
