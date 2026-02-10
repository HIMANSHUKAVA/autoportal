package com.main.service.sellar;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.main.entity.sellar.CarRequestImage;
import com.main.entity.sellar.Carrequest;

public interface CarRequestInerface {

	public Carrequest insertrequest(int user_id , Carrequest s  , MultipartFile photo);
	
	public List<Carrequest>viewall();
	
	public Carrequest updatestatus(int id  , String status);
	
	public void deleterequest(int id);
	
	public Carrequest singledeta(int id);
	
	public Carrequest update(int id , Carrequest s , MultipartFile photo);
}
