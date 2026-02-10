package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Cars;
import com.main.entity.Ragister;
import com.main.entity.Wishlist;
import com.main.repo.WishlistRepo;
import com.main.repo.carrepository;
import com.main.repo.ragisterrepo;

@Service
public class WishlistService implements WishlistInterface {

	
	@Autowired
	WishlistRepo r;
	
	@Autowired 
	ragisterrepo s;
	
	@Autowired
	carrepository c;
	
	

	@Override
	public List<Wishlist> ViewWishlist() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public void deletewishlist(int id) {
		// TODO Auto-generated method stub
		
		r.deleteById(id);
	}

	@Override
	public Wishlist Insertdeta(int user_id, int car_id) {
		// TODO Auto-generated method stub
		
		
		Ragister a =   s.findById(user_id).orElseThrow();
		Cars m =  c.findById(car_id).orElseThrow();
		
		
		Wishlist w = new Wishlist();
		w.setC(m);
		w.setR(a);
		
		return r.save(w);
		
	}

	
	
	
}
