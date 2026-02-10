package com.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Addtocart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cart_id;

	

	@ManyToOne
	@JoinColumn(name = "car_id")
	Cars car;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	Ragister use;

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public Cars getCar() {
		return car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}

	public Ragister getUse() {
		return use;
	}

	public void setUse(Ragister use) {
		this.use = use;
	}

	
	
	
	
	
}
