package com.main.entity;



import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int paymentId;

	    private double totalAmount;

	    private double paidBookingAmount;

	    private double pendingAmount;

	    private String transactionNumber;

	    @CreationTimestamp
	    @Column(updatable = false)
	    private Timestamp paymentAt;

	    private String paymentStatus = "PENDING";

	    private String paymentMethod;

	    @ManyToOne
	    @JoinColumn(name = "car_id")
	    @JsonIgnoreProperties({"payments"})
	    private Cars car;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    @JsonIgnoreProperties({"payments"})
	    private Ragister r;

	    @Column(name = "razorpay_order_id")
	    private String razorpayOrderId;
	    
	    
	    
	    public String getRazorpayOrderId() {
			return razorpayOrderId;
		}

		public void setRazorpayOrderId(String razorpayOrderId) {
			this.razorpayOrderId = razorpayOrderId;
		}

		public void setPaymentAt(Timestamp paymentAt) {
			this.paymentAt = paymentAt;
		}

		public int getPaymentId() {
	        return paymentId;
	    }

	    public void setPaymentId(int paymentId) {
	        this.paymentId = paymentId;
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

	    public String getTransactionNumber() {
	        return transactionNumber;
	    }

	    public void setTransactionNumber(String transactionNumber) {
	        this.transactionNumber = transactionNumber;
	    }

	    public Timestamp getPaymentAt() {
	        return paymentAt;
	    }

	    public String getPaymentStatus() {
	        return paymentStatus;
	    }

	    public void setPaymentStatus(String paymentStatus) {
	        this.paymentStatus = paymentStatus;
	    }

	    public String getPaymentMethod() {
	        return paymentMethod;
	    }

	    public void setPaymentMethod(String paymentMethod) {
	        this.paymentMethod = paymentMethod;
	    }

	    public Cars getCar() {
	        return car;
	    }

	    public void setCar(Cars car) {
	        this.car = car;
	    }

	    public Ragister getR() {
	        return r;
	    }

	    public void setR(Ragister r) {
	        this.r = r;
	    }

	   
    
    
    
	
}
