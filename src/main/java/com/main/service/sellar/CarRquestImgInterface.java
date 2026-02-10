package com.main.service.sellar;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.main.entity.sellar.CarRequestImage;
import com.main.entity.sellar.Carrequest;

public interface CarRquestImgInterface {

	
	public CarRequestImage insertdeta(int car_id , List<MultipartFile> photos);
	
	public List<CarRequestImage>viewall();
	
	public void deleteimage(int id);
	
	public CarRequestImage update(int id , String photos);
	
	public List<CarRequestImage>fetchsingleimage(int car_id);
	
	
}
