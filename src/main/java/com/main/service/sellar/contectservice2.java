package com.main.service.sellar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.contect;
import com.main.entity.sellar.Sellarcontect;
import com.main.repo.sellar.Sellarcontects;
import com.main.service.contectinterface;
import com.main.service.email.sellerEmail;

@Service
public class contectservice2 implements  sellarcon
{
	
	@Autowired
	Sellarcontects r;

	
	@Autowired
	sellerEmail n;
	
	@Override
	public Sellarcontect insertcontect(Sellarcontect n) {
		// TODO Auto-generated method stub
		return r.save(n);
	}

	@Override
	public List<Sellarcontect> viewsellarcontect() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Sellarcontect fetchsingledeta(int id) {
		// TODO Auto-generated method stub
		return r.findById(id).orElseThrow();
	}

	@Override
	public Sellarcontect updatestatuscontect(int id, String status , String message) {
		// TODO Auto-generated method stub
		
		Sellarcontect p = r.findById(id).orElseThrow(() -> new RuntimeException("Sellar Not Found"));
		
	    p.setStatus(status);
	    p.setMessage(message);
	
	    
	    String body =
		        "Hello user ,\n\n" +
		        "Thank you for contacting us.\n\n" +
		        "Your concern has been reviewed by our team and resolved with the following response:\n\n" +
		        message + "\n\n" +
		        "If you need any further help, feel free to reach out to us anytime.\n\n" +
		        "Regards,\n" +
		        "Support Team";
	    
	    
	   n.carrequestmessagefromsellar(p.getEmail(), body);
	    
	    
        return r.save(p);
	}

	
	
	
}
