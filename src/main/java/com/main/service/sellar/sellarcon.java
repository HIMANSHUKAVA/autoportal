package com.main.service.sellar;

import java.util.List;

import com.main.entity.sellar.Sellarcontect;

public interface sellarcon {

	public Sellarcontect insertcontect(Sellarcontect n);
	
	public List<Sellarcontect>viewsellarcontect();
	
	public Sellarcontect fetchsingledeta(int id);
	
	
	public Sellarcontect updatestatuscontect(int id , String status , String message);
	
	
	
}
