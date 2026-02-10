
package com.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity

public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int wishlist_id;
	
	@ManyToOne
	@JoinColumn(name= "user_id")
	Ragister r;
	
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	Cars c;


	public int getWishlist_id() {
		return wishlist_id;
	}


	public void setWishlist_id(int wishlist_id) {
		this.wishlist_id = wishlist_id;
	}


	public Ragister getR() {
		return r;
	}


	public void setR(Ragister r) {
		this.r = r;
	}


	public Cars getC() {
		return c;
	}


	public void setC(Cars c) {
		this.c = c;
	}
	
	
	
	
}
