package com.main.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Old_car;
import com.main.entity.Old_car_payment;
import com.main.entity.PaymentOrderResponseDTO;
import com.main.entity.PaymentRequestDTO;
import com.main.entity.Ragister;
import com.main.repo.Old_car_payment_repo;
import com.main.repo.old_car_repo;
import com.main.repo.ragisterrepo;
import com.main.service.Admin.AdminEmail;
import com.razorpay.RazorpayException;

@Service
public class old_Car_payment_service  implements old_car_payment_interface{

    

	@Autowired
	Old_car_payment_repo r;
	
	@Autowired
	EmailService ser;
	
	@Autowired 
	ragisterrepo s;
	
	@Autowired
	old_car_repo s1;
	
	@Autowired
	RazorpayService s5;
	
	
	
	@Autowired
	AdminEmail a;

    
	
	@Override
	public PaymentOrderResponseDTO insertpayment(int user_id, int old_car_id, PaymentRequestDTO dto) {
		// TODO Auto-generated method stub
		
		Ragister r1 = s.findById(user_id)
	            .orElseThrow(() -> new RuntimeException("Register user not found"));
		
		
		Old_car c1 = s1.findById(old_car_id).orElseThrow(() -> new RuntimeException("Car not found"));
		System.out.println("TOTAL AMOUNT: " + dto.getTotalAmount());
		System.out.println("PAID BOOKING: " + dto.getPaidBookingAmount());

		if (dto.getPaidBookingAmount() <= 0 ||
		        dto.getPaidBookingAmount() > dto.getTotalAmount()) {
		        throw new RuntimeException("Invalid booking amount");
		    }

		
		
         double pendingAmount =
		            dto.getTotalAmount() - dto.getPaidBookingAmount();
		 
		 Old_car_payment p1 = new Old_car_payment();
			
		 

		 String orderId;
		    try {
		        orderId = s5.createOrder(dto.getPaidBookingAmount());
		        System.out.println("ORDER ID FROM RAZORPAY = " + orderId);
		    } catch (RazorpayException e) {
		        throw new RuntimeException("Razorpay order creation failed", e);
		    }
		
		p1.setTotalAmount(dto.getTotalAmount());
		p1.setCar(c1);
		p1.setR(r1);
		p1.setTransection_id(UUID.randomUUID().toString());
		p1.setPaymentMehtod(dto.getPaymentMethod());
		p1.setPaidBookingAmount(dto.getPaidBookingAmount());
		p1.setPendingAmount(pendingAmount);
		p1.setRazorpayOrderId(orderId);
		p1.setStatus("pending");
		
		Old_car_payment savedPayment = r.save(p1);
		
		
		
		
		 String body =
		            "Hello " + r1.getUsername() + ",\n\n" +
		            "Your old car  payment request has been created successfully.\n\n" +
		            "📄 Payment Details:\n" +
		            "---------------------------\n" +
		            "Transaction ID : " + savedPayment.getTransection_id() + "\n" +
		            "Total Amount   : ₹" + savedPayment.getTotalAmount() + "\n" +
		            "Paid Amount    : ₹" + savedPayment.getPaidBookingAmount() + "\n" +
		            "Pending Amount : ₹" + savedPayment.getPendingAmount() + "\n" +
		            "Payment Method : " + savedPayment.getPaymentMehtod() + "\n" +
		            "Payment Date   : " + savedPayment.getCreate_at() + "\n" +
		            "Status         : PENDING\n\n" +
		            "Your booking will be confirmed once payment is completed.\n\n" +
		            "Thank you for trusting Auto Portal 🚘\n\n" +
		            "Regards,\n" +
		            "Auto Portal Team";
		 
		 
		 String subject = "Payment Of Old Car";
		 
		 ser.PaymentSuccessFromUser(r1.getEmail(), subject, body);
		 
		 String adminBody =
		            "Hello Admin,\n\n" +
		            "A Old Car  payment has been received on Auto Portal.\n\n" +
		            "📄 Payment Details:\n" +
		            "---------------------------\n" +
		            "Transaction ID : " + savedPayment.getTransection_id()+ "\n" +
		            "User Name      : " + r1.getUsername() + "\n" +
		            "User Email     : " + r1.getEmail() + "\n" +
		            "Car ID         : " + c1.getId() + "\n" +
		            "Total Amount   : ₹" + savedPayment.getTotalAmount() + "\n" +
		            "Paid Amount    : ₹" + savedPayment.getPaidBookingAmount() + "\n" +
		            "Pending Amount : ₹" + savedPayment.getPendingAmount() + "\n" +
		            "Payment Method : " + savedPayment.getPaymentMehtod() + "\n" +
		            "Payment Status : " + savedPayment.getStatus() + "\n" +
		            "Payment Date   : " + savedPayment.getCreate_at() + "\n\n" +
		            "Please review this payment in the admin panel and take necessary action.\n\n" +
		            "Regards,\n" +
		            "Auto Portal System";

		 
		 ser.PaymentSuccesFromAdmin(adminBody);

		
		 

	
		
		
				
	 return new PaymentOrderResponseDTO(
	            savedPayment.getPaymentId(),
	            orderId,
	            dto.getPaidBookingAmount()
	    );
		
	}

	@Override
	public List<Old_car_payment> viewall() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Old_car_payment updatestatus(int id , String status) {
		// TODO Auto-generated method stub
		
		Old_car_payment s2 =  r.findById(id).orElseThrow(()-> new RuntimeException("User Nor Found"));
		
		s2.setStatus(status);
		
		
		return r.save(s2);
		
		
	}

	@Override
	public Old_car_payment oldcarpaymentlink(int id) {
		// TODO Auto-generated method stub
		Old_car_payment p = r.findById(id).orElseThrow(()-> new RuntimeException("Payment Record Nor Found"));
		
		
		String payNowLink =
			    "https://autoportlfrontend.vercel.app/oldpay?paymentId="
			    + p.getPaymentId()
			    + "&userId="
			    + p.getR().getId();

		
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
		
		a.oldcarpaymenteremainder(p.getR().getEmail(), body);
		
		return p;
	}

	@Override
	public Old_car_payment fetchsinglepaymentbyoldcar(int id) {

		return r.findById(id).orElseThrow(()-> new RuntimeException("payment deta not found"));
		
	}

	@Override
	public Old_car_payment fetchdetausinglink(int userid, int paymentid) {
		// TODO Auto-generated method stub
		Ragister s1 = s.findById(userid).orElseThrow(()-> new RuntimeException("user not found")); 
		Old_car_payment p = r.findById(paymentid).orElseThrow(()-> new RuntimeException("Payment Record Nor Found"));
		
		 if (p.getR() == null || p.getR().getId() != s1.getId()) {
		        throw new RuntimeException("Unauthorized Access: This payment does not belong to this user");
		    }
		
		return p;
	}

	@Override
	public Old_car_payment old_doubleAMount(int id, Double Amount) {
		// TODO Auto-generated method stub
		Old_car_payment p = r.findById(id).orElseThrow(()-> new RuntimeException("Payment Record Nor Found"));
		
		double dbpendingAmount = p.getPendingAmount();
		double dbBookedamount = p.getPaidBookingAmount();
		
		
		double newbookedamount = dbBookedamount + Amount;
		
		double newpendingamount = dbpendingAmount - Amount;

		
		
		p.setPendingAmount(newpendingamount);
		p.setPaidBookingAmount(newbookedamount);
		
		
		if (newpendingamount <= 0) {
	        p.setPendingAmount(0);
	        p.setStatus("RESOLVED");
	    }
		
		return r.save(p);
	}
	
	@Override
	public Old_car_payment save(Old_car_payment p) {
	    return r.save(p);
	}

	
	
	
	
	
	

}
