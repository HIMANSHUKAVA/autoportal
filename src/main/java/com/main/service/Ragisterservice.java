package com.main.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Ragister;
import com.main.repo.ragisterrepo;

@Service
public class Ragisterservice  implements ragisterinterface {

	
	@Autowired
	ragisterrepo r;

	@Override
	public Ragister insertdeta(Ragister s) {
		// TODO Auto-generated method stub
		return r.save(s);
	}

	@Override
	public List<Ragister> viewall() {
		// TODO Auto-generated method stub
		return  r.findAll();
	}

	@Override
	public void deleatuser(int id) {
		// TODO Auto-generated method stub
		
		r.deleteById(id);
	}

	@Override
	public Ragister fatchsingleuser(int id) {
		// TODO Auto-generated method stub
		return r.findById(id).orElseThrow();
	}

	@Override
	public Ragister updatuser(int id, Ragister s , String old_password , MultipartFile photo) {
		
		Ragister r1 = r.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
		
	    String newpassword = s.getPassword();
	    String old_passwordfromdb = r1.getPassword();
	    
	    
	    
	    if(s.getPassword() != null  && !s.getPassword().isEmpty())
	    {
	    	 if (old_password == null || old_password.isEmpty() || !old_password.equals(old_passwordfromdb))
	 	    {
	 	    	throw new RuntimeException("Password Do Not Matched");
	 	    }
	    	 
	    	 r1.setPassword(s.getPassword());
	    }
	    
	    
	    
	    
	   

        r1.setEmail(s.getEmail());
	    
        
        if(photo !=null && !photo.isEmpty())
        {
        	try
        	{
        	String filename =  System.currentTimeMillis() + "-" + photo.getOriginalFilename();
        	
        	 photo.transferTo(new File("/Users/kavahimanshu/images/" + filename));
        	 
        	 r1.setPhoto(filename);
        	}
        	catch (Exception e) {
				// TODO: handle exception
        		
        		throw new RuntimeException("Image Not uploaded");
			}
        	
        	
        }
	    
	    
	    r1.setUsername(s.getUsername());
	    
	   
	    
	    if (s.getRole() != null) {
	        r1.setRole(s.getRole());
	    }

	    return r.save(r1);
	  
		
		
	}
	
	
	@Override
	public Ragister login(String email , String password)
	{
		
		Optional<Ragister>finduser =  r.findByEmailAndPassword(email, password);
		
		if(finduser.isPresent())
		{
			return finduser.get();
			
		}
		else
		{
			return null;
		}
	}

	@Override
	public String findbyemail(String email, String password) {
		// TODO Auto-generated method stub
		Optional<Ragister>finduser =  r.findByEmail(email);
		
		
		if(finduser.isPresent())
		{
			Ragister s =  finduser.get();
			
			s.setPassword(password);
			
			r.save(s);
			
			
			return "Password Updated Succesfully";
		}
		else
		{
			return "User Not Found Please Entered Valid Email Address";
		}
	}

	@Override
    public Optional<Ragister> findByEmail1(String email) {
        return r.findByEmail1(email);
    }


	@Override
	public Ragister updatepassword(int id, String currentpassword, String newpassword) {
		// TODO Auto-generated method stub
		
		
		Ragister rs = r.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
		
		
		String dbpassword = rs.getPassword();
		
		if(!currentpassword.equals(dbpassword))
		{
			throw new RuntimeException("Current Password Is Wrong");
		}
		if(currentpassword == null ||  currentpassword.isEmpty())
		{
			throw new RuntimeException("Current Password Can Not Be Empty");
		}
		if(newpassword == null || newpassword.isEmpty())
		{
			throw new RuntimeException("New Password Cannot Be Empty");
		}
		rs.setPassword(newpassword);
		
		
		
		
		return r.save(rs);
	}


	
	




	
	
	
}
