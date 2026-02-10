package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.contect;
import com.main.repo.contectrepo;

@Service
public class contectservice implements contectinterface {

	
	@Autowired
	contectrepo r;
	
	@Autowired
	EmailService s;
	
	@Override
	public contect insertdeta(contect c) {
		// TODO Auto-generated method stub
		
		
        contect c1 =  r.save(c);
		
		
		try
		{
			String body =  "Hi " + c.getName() + ",\n\n" +
		            "Thank you for contacting us.\n" +
		            "We have received your message and will contact you soon.\n\n" +
		            "Regards,\n Autoportal";
			String toemail = c.getEmail();
			String subject = c.getSubject();
			s.contectmesagefromuser(toemail, subject, body);
			
			
			String body2 =  "Name: " + c.getName() + "\n" +
		            "Email: " + c.getEmail() + "\n" +
		            "Subject: " + c.getSubject() + "\n" +
		            "Message: " + c.getMessage();
			
			String subject2 = "New contect Message";
			
			s.contectmessagefromadmin(subject2, body2);
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c1;
		
	}

	@Override
	public List<contect> viewcontect() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public void deletecontect(int id) {
		// TODO Auto-generated method stub
		
		r.deleteById(id);
	}

	@Override
	public contect updaecpntect(int id, String status , String message) {
		// TODO Auto-generated method stub
		
		contect existing =  r.findById(id).orElseThrow(()-> new RuntimeException("user not found"));

		existing.setStatus(status);
		existing.setResolve(message);
		
		
		String body =
		        "Hello user ,\n\n" +
		        "Thank you for contacting us.\n\n" +
		        "Your concern has been reviewed by our team and resolved with the following response:\n\n" +
		        message + "\n\n" +
		        "If you need any further help, feel free to reach out to us anytime.\n\n" +
		        "Regards,\n" +
		        "Support Team";


		s.contectMessageFromuser(body, existing.getEmail());
		
		
		return r.save(existing);
		
	}

	
}
