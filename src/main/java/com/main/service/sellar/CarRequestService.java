package com.main.service.sellar;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Ragister;
import com.main.entity.sellar.CarRequestImage;
import com.main.entity.sellar.Carrequest;
import com.main.repo.ragisterrepo;
import com.main.repo.sellar.carRequestrepo;
import com.main.service.email.sellerEmail;
@Service
public class CarRequestService implements CarRequestInerface {

	@Autowired
	carRequestrepo r;
	
	@Autowired
	ragisterrepo rs1;
	
	@Autowired
	sellerEmail s1;

	
	
	@Override
	public Carrequest insertrequest(int user_id , Carrequest rs ,  MultipartFile photo) {
		// TODO Auto-generated method stub
         Ragister s = rs1.findById(user_id).orElseThrow();
         
         Carrequest a  = new Carrequest();
         a.setColour(rs.getColour());
         a.setBrand(rs.getBrand());
         a.setModel(rs.getModel());
         a.setCondition(rs.getCondition());
         a.setFuel(rs.getFuel());
         a.setKm_driven(rs.getKm_driven());
         a.setPriceLabel(rs.getPriceLabel());
         a.setPriceMin(rs.getPriceMin());
         a.setPriceMax(rs.getPriceMax());
         a.setPrice(rs.getPrice());
         a.setRs(s);
         a.setStatus("PENDING");
         a.setTransmission(rs.getTransmission());
         a.setYear(rs.getYear());
         a.setType(rs.getType());
         
         
         if(photo !=null && !photo.isEmpty())
         {
        	 try
        	 {
     			String filename =  System.currentTimeMillis()  + "-" +  photo.getOriginalFilename();
    			String uploadDir = System.getProperty("user.dir") + "/images/";
    			File dir = new File(uploadDir);
    			if (!dir.exists()) {
    			    dir.mkdirs();
    			}

    			photo.transferTo(new File(uploadDir + filename));
    		   s.setPhoto(filename);
    		   }
        	 catch(Exception e)
        	 {
        		 throw new RuntimeException("Image upload failed", e);
        	 }
         }
         
         else {
        	 s.setPhoto("mahindra-xuv.avif");
 	    }


         
         
         
         String body =
        	        "Hello " + s.getUsername() + ",\n\n"
        	      + "Your car selling request has been successfully submitted on AUTO PORTAL.\n\n"
        	      + "🚗 Car Details:\n"
        	      + "Car Name      : " + rs.getBrand() + "\n"
        	      + "Model         : " + rs.getModel() + "\n"
        	      + "Fuel Type     : " + rs.getFuel() + "\n"
        	      + "Price         : ₹" + rs.getPrice() + "\n\n"
        	      + "📌 Current Status: PENDING\n\n"
        	      + "Our admin team is reviewing your request. "
        	      + "Once approved, your car will be visible on the platform.\n\n"
        	      + "Thank you for choosing AUTO PORTAL.\n\n"
        	      + "Regards,\n"
        	      + "AUTO PORTAL Team";

		
         String body1 =
        	        "Hello Admin,\n\n"
        	      + "A new car selling request has been submitted by a seller and requires your approval.\n\n"
        	      + "🚗 Car Details:\n"
        	      + "Car Name      : " +rs.getBrand() + "\n"
        	      + "Model         : " + rs.getModel() + "\n"
        	      + "Fuel Type     : " + rs.getFuel() + "\n"
        	      + "Expected Price: ₹" + rs.getPrice() + "\n\n"
        	      + "👤 Seller ID  : " + s.getId() + "\n\n"
        	      + "📌 Current Status: PENDING\n\n"
        	      + "Please review this request in the admin panel and take necessary action.\n\n"
        	      + "Regards,\n"
        	      + "AUTO PORTAL System";

         
         Carrequest o = r.save(a);
         
         s1.carrequestmessagefromsellar(s.getEmail(), body);
         s1.carrequestmessagefromadmin(body1);
         
         
         return o;
		
		
		
	}
	

	@Override
	public List<Carrequest> viewall() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Carrequest updatestatus(int id, String status) {
		// TODO Auto-generated method stub
		
		Carrequest s =  r.findById(id).orElseThrow(()-> new RuntimeException("Request Not Found"));
		
		s.setStatus(status);
		
		return r.save(s);
		
		
	}

	@Override
	public void deleterequest(int id) {
		// TODO Auto-generated method stub
		
		r.deleteById(id);
	}

	@Override
	public Carrequest singledeta(int id) {
		// TODO Auto-generated method stub
		return r.findCarWithImages(id);
	}

	@Override
	public Carrequest update(int id, Carrequest s , MultipartFile photo) {

	    Carrequest s2 = r.findById(id)
	        .orElseThrow(() -> new RuntimeException("Request Not Found"));

	    if (s.getBrand() != null)
	        s2.setBrand(s.getBrand());

	    if (s.getModel() != null)
	        s2.setModel(s.getModel());

	    if (s.getCarcondition() != null)
	        s2.setCarcondition(s.getCarcondition());

	    if (s.getColour() != null)
	        s2.setColour(s.getColour());
        
	    if(photo != null && !photo.isEmpty())
	    {
	    	try
	    	{
	    	String filename =  photo.getOriginalFilename();
	    	String uploadDir = System.getProperty("user.dir") + "/images/";
			File dir = new File(uploadDir);
			if (!dir.exists()) {
			    dir.mkdirs();
			}

			photo.transferTo(new File(uploadDir + filename));
		   s.setPhoto(filename);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    }
	    
	    if (s.getTransmission() != null)
	        s2.setTransmission(s.getTransmission());

	    if (s.getFuel() != null)
	        s2.setFuel(s.getFuel());

	    if (s.getKm_driven() != 0)
	        s2.setKm_driven(s.getKm_driven());

	    if (s.getPrice() != 0)
	        s2.setPrice(s.getPrice());

	    return r.save(s2);
	}





	
	
	
}
