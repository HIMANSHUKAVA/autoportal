package com.main.service;

import java.util.List;

import com.main.entity.Wishlist;

public interface WishlistInterface {

	
	
	public Wishlist Insertdeta(int user_id , int car_id);
	public List<Wishlist>ViewWishlist();
	
	public void deletewishlist(int id);
	
	
}
