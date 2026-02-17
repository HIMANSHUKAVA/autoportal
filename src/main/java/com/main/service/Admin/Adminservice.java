package com.main.service.Admin;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.admin.Admins;
import com.main.mail.Admin;
import com.main.repo.Admin.Adminrepo;

@Service
public class Adminservice  implements AdminInterface
{

	@Autowired
	Adminrepo r;

	@Override
	public Admins insertadmin(Admins a) {
		// TODO Auto-generated method stub
		return r.save(a);
	}

	@Override
	public List<Admins> viewadmin() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Admins logincheck(String email, String password) {

	    Optional<Admins> a = r.findEmailadmin(email);

	    if(a.isPresent()) {
	        Admins s = a.get();

	        if(s.getPassword().equals(password)) {
	            return s;
	        }
	    }

	    return null;
	}

	@Override
	public void Deleteadmin(int id) {
		// TODO Auto-generated method stub
		
		r.deleteById(id);
	}

	@Override
	public Admins updateadmin(int id, Admins a, MultipartFile file, String oldpassword) {
		
		Admins s =  r.findById(id).orElseThrow();
		
		
		
		String newpassword = a.getPassword();
		String oldpasswordfromdb = s.getPassword();
		
		
		if(newpassword !=null && !newpassword.isEmpty())
		{
		        if(oldpassword == null ||  oldpassword.isEmpty() || !oldpassword.equals(oldpasswordfromdb))
		        {
		        	throw new RuntimeException("Password Do Not Matched");
		        }
		        s.setPassword(newpassword);
		}
		
		s.setEmail(a.getEmail());
		
		
	
		if(file != null && !file.isEmpty())
		{
			try
			{
				
			
			String filename =  System.currentTimeMillis()  + "-" + file.getOriginalFilename();
			 file.transferTo(new File("/Users/kavahimanshu/images/" + filename));
			 
			 s.setPhoto(filename);
			}
			catch(Exception e)
			{
				throw new RuntimeException("Photo not uploaded");
			}
			 
		}

		
		if(a.getRole() != null)
		{
			s.setRole(a.getRole());
		}
		
		
		if(a.getUsername() != null)
		{
			s.setUsername(a.getUsername());
			}
		return r.save(s);
		
		
		
		
	}

	@Override
	public Admins singledeta(int id) {
		// TODO Auto-generated method stub
		
		Admins o =  r.findById(id).orElseThrow(()-> new RuntimeException("Admin Not Found"));
		return o;
	}


	
	
	
	
	
}
