package com.main.entity;

public class PaymentOrderResponseDTO {

	
	private int paymentId;
    private String razorpayOrderId;
    private double amount;
    
    
    
	public PaymentOrderResponseDTO(int paymentId, String razorpayOrderId, double amount) {
		super();
		this.paymentId = paymentId;
		this.razorpayOrderId = razorpayOrderId;
		this.amount = amount;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
    
    
	
	
}
