package com.main.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Cars;
import com.main.entity.Payment;
import com.main.entity.PaymentOrderResponseDTO;
import com.main.entity.PaymentRequestDTO;
import com.main.entity.Ragister;
import com.main.repo.PaymentRepo;
import com.main.repo.carrepository;
import com.main.repo.ragisterrepo;
import com.main.service.RazorpayService;
import com.main.service.Admin.AdminEmail;
import com.razorpay.RazorpayException;

@Service
public class PaymentService  implements PaymentInterface{

	@Autowired 
	ragisterrepo s;
	
	@Autowired
	carrepository c;
	
	@Autowired
	PaymentRepo r;
	
	@Autowired
	EmailService ser;
	
	@Autowired
	AdminEmail a;
	
	@Autowired
	private RazorpayService razorpayService;


	@Override
	public PaymentOrderResponseDTO insertdeta(int user_id, int car_id, PaymentRequestDTO dto) {

	    // ️⃣ User fetch
	    Ragister r1 = s.findById(user_id)
	            .orElseThrow(() -> new RuntimeException("Register user not found"));

	    // ️⃣ Car fetch
	    Cars c1 = c.findById(car_id)
	            .orElseThrow(() -> new RuntimeException("Car not found"));

	    // 3️ Pending amount calculation
	    double pendingAmount =
	            dto.getTotalAmount() - dto.getPaidBookingAmount();

	    
	    
	    // 4️New Payment object
	    Payment p = new Payment();
	    p.setCar(c1);
	    p.setR(r1);

	    p.setTotalAmount(dto.getTotalAmount());
	    p.setPaidBookingAmount(dto.getPaidBookingAmount());
	    p.setPendingAmount(pendingAmount);

	    p.setPaymentMethod(dto.getPaymentMethod());
	    p.setPaymentStatus("PENDING");
	    p.setTransactionNumber(UUID.randomUUID().toString());

	    // 5️ Save payment
	    Payment savedPayment = r.save(p);

	    // ️⃣ User mail body
	    String body =
	            "Hello " + r1.getUsername() + ",\n\n" +
	            "Your payment request has been created successfully.\n\n" +
	            "📄 Payment Details:\n" +
	            "---------------------------\n" +
	            "Transaction ID : " + savedPayment.getTransactionNumber() + "\n" +
	            "Total Amount   : ₹" + savedPayment.getTotalAmount() + "\n" +
	            "Paid Amount    : ₹" + savedPayment.getPaidBookingAmount() + "\n" +
	            "Pending Amount : ₹" + savedPayment.getPendingAmount() + "\n" +
	            "Payment Method : " + savedPayment.getPaymentMethod() + "\n" +
	            "Payment Date   : " + savedPayment.getPaymentAt() + "\n" +
	            "Status         : PENDING\n\n" +
	            "Your booking will be confirmed once payment is completed.\n\n" +
	            "Thank you for trusting Auto Portal 🚘\n\n" +
	            "Regards,\n" +
	            "Auto Portal Team";

	    ser.PaymentSuccessFromUser(
	            r1.getEmail(),
	            "Payment Initiated - Auto Portal",
	            body
	    );

	    // 7️ Admin mail body
	    String adminBody =
	            "Hello Admin,\n\n" +
	            "A new payment has been received on Auto Portal.\n\n" +
	            "📄 Payment Details:\n" +
	            "---------------------------\n" +
	            "Transaction ID : " + savedPayment.getTransactionNumber() + "\n" +
	            "User Name      : " + r1.getUsername() + "\n" +
	            "User Email     : " + r1.getEmail() + "\n" +
	            "Car ID         : " + c1.getId() + "\n" +
	            "Total Amount   : ₹" + savedPayment.getTotalAmount() + "\n" +
	            "Paid Amount    : ₹" + savedPayment.getPaidBookingAmount() + "\n" +
	            "Pending Amount : ₹" + savedPayment.getPendingAmount() + "\n" +
	            "Payment Method : " + savedPayment.getPaymentMethod() + "\n" +
	            "Payment Status : " + savedPayment.getPaymentStatus() + "\n" +
	            "Payment Date   : " + savedPayment.getPaymentAt() + "\n\n" +
	            "Please review this payment in the admin panel and take necessary action.\n\n" +
	            "Regards,\n" +
	            "Auto Portal System";

	    ser.PaymentSuccesFromAdmin(adminBody);

	
	    String orderId;
	    try {
	        orderId = razorpayService.createOrder(dto.getPaidBookingAmount());
	        System.out.println("ORDER ID FROM RAZORPAY = " + orderId);
	    } catch (RazorpayException e) {
	        throw new RuntimeException("Razorpay order creation failed", e);
	    }
		
		
	 savedPayment.setRazorpayOrderId(orderId);
	 r.save(savedPayment);
	 
	    return new PaymentOrderResponseDTO(
	            savedPayment.getPaymentId(),
	            orderId,
	            dto.getPaidBookingAmount()
	    );
	}
	   
	


	@Override
	public List<Payment> viewpayment() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Payment updatestatus(int id, String status) {
		// TODO Auto-generated method stub
		
		Payment s3 = r.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
		
		
		s3.setPaymentStatus(status);
		
//		String razorpayOrderid = Razorpa
		
		return r.save(s3);
				
		
	}




	@Override
	public Payment remenderppayment(int id) {
		
		Payment  p =r.findById(id).orElseThrow(()-> new RuntimeException("Record Not Found"));
		
		
		String payNowLink =
			    "https://rococo-lollipop-58fe1b.netlify.app/pay?paymentId="
			    + p.getPaymentId();


		String body = "Dear " + p.getR().getUsername() + ",\n\n"
		        + "This is a gentle reminder regarding your pending payment for the car booking.\n\n"
		        + "Car Details:\n"
		        + "• Brand: " + p.getCar().getBrand() + "\n"
		        + "• Model: " + p.getCar().getModel() + "\n"
		        + "• Type: " + p.getCar().getCarType() + "\n\n"
		        + "Payment Summary:\n"
		        + "• Total Amount: ₹" + p.getTotalAmount() + "\n"
		        + "• Paid Amount: ₹" + p.getPaidBookingAmount() + "\n"
		        + "• Pending Amount: ₹" + p.getPendingAmount() + "\n\n"
		        + "To complete your payment, please click the button below:\n\n"
		        + "👉 PAY NOW: " + payNowLink + "\n\n"
		        + "This will redirect you to a secure payment page where you can pay the pending amount.\n\n"
		        + "If you have already made the payment, please ignore this message.\n\n"
		        + "Thank you for choosing us.\n\n"
		        + "Regards,\n"
		        + "Car Booking Team";

		
		
		a.RequestPayment(p.getR().getEmail(), body);

		
		return p;
		
	}




	@Override
	public Payment updateamount(int id, double amount) {

	    Payment p = r.findById(id)
	            .orElseThrow(() -> new RuntimeException("Record Not Found"));

	    double prevPaid = p.getPaidBookingAmount();
	    double prevPending = p.getPendingAmount();

	    double newPaid = prevPaid + amount;
	    double newPending = prevPending - amount;

	    p.setPaidBookingAmount(newPaid);
	    p.setPendingAmount(newPending);

	    // Optional: auto resolve
	    if (newPending <= 0) {
	        p.setPendingAmount(0);
	        p.setPaymentStatus("RESOLVED");
	    }

	    return r.save(p);
	}




	@Override
	public Payment fetchSinglePaymentData(int id) {
		// TODO Auto-generated method stub
		return r.findById(id).orElseThrow();
	}

	
	
	
	
	
	
	
	
	
	
	
}
