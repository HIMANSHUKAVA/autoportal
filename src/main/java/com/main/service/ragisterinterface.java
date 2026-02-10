package com.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Ragister;

public interface ragisterinterface 
{

	
	 Ragister insertdeta(Ragister s);
	 
	 List<Ragister>viewall();
	 
	 public void deleatuser(int id);
	 
	 public Ragister fatchsingleuser(int id);
	 
	 public Ragister updatuser(int id , Ragister s ,String old_password , MultipartFile photo);
	 
	 
	 public Ragister login(String email , String password);
	 
	 public String findbyemail(String email , String password);
	 
	 public Optional<Ragister> findByEmail1(String email);

	 public Ragister updatepassword(int id , String currentpassword , String newpassword);
	 
	 
	 
}
