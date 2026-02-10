package com.main.entity;

public class PaymentRequestDTO {

	
	 private double totalAmount;
	    private double paidBookingAmount;
	    private String paymentMethod;
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
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
	    
	    
}
