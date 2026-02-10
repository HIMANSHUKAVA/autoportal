package com.main.entity;


import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Old_car_payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int paymentId;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	Ragister r;
	
	
	@ManyToOne
	@JoinColumn(name = "old_car_id")
	Old_car car;

	
	@CreationTimestamp
	@Column(updatable = false)
	Timestamp create_at;
	
	String transection_id;
	
	String status ="pending";
	
	
	double totalAmount;
	
	double paidBookingAmount;
	
	double pendingAmount;
	
    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;
	
	String paymentMehtod;
	

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Ragister getR() {
		return r;
	}

	public void setR(Ragister r) {
		this.r = r;
	}

	public Old_car getCar() {
		return car;
	}

	public void setCar(Old_car car) {
		this.car = car;
	}

	public Timestamp getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}

	public String getTransection_id() {
		return transection_id;
	}

	public void setTransection_id(String transection_id) {
		this.transection_id = transection_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getPaidBookingAmount() {
		return paidBookingAmount;
	}

	public void setPaidBookingAmount(double paidBookingAmount) {
		this.paidBookingAmount = paidBookingAmount;
	}

	public double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}

	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}

	public String getPaymentMehtod() {
		return paymentMehtod;
	}

	public void setPaymentMehtod(String paymentMehtod) {
		this.paymentMehtod = paymentMehtod;
	}



	
	
	
	
}
