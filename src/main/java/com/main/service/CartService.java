package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Addtocart;
import com.main.entity.Cars;
import com.main.entity.Ragister;
import com.main.repo.Cartrepo;
import com.main.repo.carrepository;
import com.main.repo.ragisterrepo;

@Service
public class CartService implements Cartinterface {

	
	@Autowired
	Cartrepo r;
	
	
	@Autowired
	ragisterrepo w;
	
	@Autowired
	carrepository c;
	
	@Override
	public Addtocart cartadddeta(int user_id, int car_id) {
		// TODO Auto-generated method stub
		
		
		Ragister s1 = w.findById(user_id).orElseThrow();
		Cars s2 =  c.findById(car_id).orElseThrow();
		
		
		Addtocart c1 =  new Addtocart();
		c1.setCar(s2);
		c1.setUse(s1);
		
		
		return r.save(c1);
	}

	@Override
	public List<Addtocart> viewallcart() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public void deletedeta(int id) {
		// TODO Auto-generated method stub
		
		r.deleteById(id);
		
		
	}

	
}
