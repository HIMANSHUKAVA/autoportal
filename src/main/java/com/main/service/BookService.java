package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.BookDrive;
import com.main.entity.Cars;
import com.main.entity.Ragister;
import com.main.repo.BookDriveRepo;
import com.main.repo.carrepository;
import com.main.repo.ragisterrepo;

@Service
public class BookService  implements BookServiceInterface{

	
	@Autowired
	BookDriveRepo b;
	
	@Autowired
	ragisterrepo r;
	
	@Autowired
	carrepository c;

	
	@Autowired
	EmailService s4;
	@Override
	public BookDrive AddBook(int user_id, int car_id , BookDrive bs) {
		// TODO Auto-generated method stub
		
//		find the user detail
		Ragister user = r.findById(user_id).orElseThrow();
		Cars car = c.findById(car_id).orElseThrow();
		

		BookDrive s = new BookDrive();
		s.setR(user);
		s.setCar(car);
		s.setStatus("Pending");
        s.setDate(bs.getDate());
        s.setCity(bs.getCity());
        
        BookDrive saved = b.save(s);
        
        
try
{
        String subject = "Test Drive Booked 🚗";

        String body =
            "Hello " + user.getUsername() + ",\n\n" +
            "Your test drive is booked successfully.\n\n" +
            "Car: " + car.getBrand() + " " + car.getModel() + "\n" +
            "City: " + bs.getCity() + "\n" +
            "Date: " + bs.getDate() + "\n\n" +
            "Status: BOOKED\n\n" +
            "Thank you for Auto Portal";

          s4.sendbooking(user.getEmail(), subject, body);
  		

  		
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

  		return  saved;
		
		
	}

	@Override
	public List<BookDrive> viewBook() {
		// TODO Auto-generated method stub
		return b.findAll();
		
	}

	@Override
	public void DeletBooking(int id) {
		// TODO Auto-generated method stub
		BookDrive n = b.findById(id).orElseThrow();
		
		
		String body =
		        "Hello " + n.getR().getUsername() + ",\n\n"
		      + "We regret to inform you that your booking request has been REJECTED after review.\n\n"
		      + "📄 Booking Details:\n"
		      + "Booking ID     : " + n.getBook_id() + "\n"
		      + "Status         : REJECTED\n\n"
		      + "🔍 Reason:\n"
		      + "The booking request did not meet our verification or eligibility criteria at this time.\n\n"
		      + "You may submit a new booking request with updated or corrected details.\n\n"
		      + "If you believe this decision is incorrect or need assistance, feel free to contact our support team.\n\n"
		      + "Thank you for choosing AUTO PORTAL.\n\n"
		      + "Regards,\n"
		      + "AUTO PORTAL Team";

		
		
		b.deleteById(id);
	}

	@Override
	public BookDrive updateStatus(int id, String status) {
		
		BookDrive bs =  b.findById(id).orElseThrow();
		
		bs.setStatus(status);
		
		String email =  bs.getR().getEmail();
		
		String BookBody =
			    "Hello " + bs.getR().getUsername() + ",\n\n"
			  + "We would like to inform you that the Book Drive status Has Been Approved \n"
			  + "on AUTO PORTAL has been updated.\n\n"
			  + "🚗 Car Details:\n"
			  + "Brand          : " + bs.getCar().getBrand() + "\n"
			  + "Model          : " + bs.getCar().getModel()+ "\n"
			  + "Fuel Type      : " + bs.getCar().getFuel() + "\n"
			  + "Expected Price : ₹" + bs.getCar().getPrice() + "\n\n"
			  + "Thank you for choosing AUTO PORTAL.\n\n"
			  + "Regards,\nAUTO PORTAL Team";

		s4.BookStatusMessageFromuser(BookBody, email);
		
		return b.save(bs);
	}

	
	
	
	
	
	
}
