package com.main.service.Admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.main.entity.admin.Admins;



public interface AdminInterface {

	Admins insertadmin(Admins a);
	List<Admins>viewadmin();
	Admins updateadmin(int id , Admins a , MultipartFile file , String oldpassword);
	Admins logincheck(String email  , String password);
	void Deleteadmin(int id);
	
	public Admins singledeta(int id);
	
	
	
}
