package com.main.service.sellar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.sellar.CarRequestImage;
import com.main.entity.sellar.Carrequest;
import com.main.mail.Admin;
import com.main.repo.sellar.CarImageRepo;
import com.main.repo.sellar.carRequestrepo;

@Service
public class RequestImage  implements CarRquestImgInterface {

	
	@Autowired
	CarImageRepo r;

	@Autowired 
	carRequestrepo m;
	
	@Autowired
	Admin a;
	
	
	@Override
	public CarRequestImage insertdeta(int car_id, List<MultipartFile>photos) {
		// TODO Auto-generated method stub
		
		Carrequest s1 = m.findById(car_id).orElseThrow();
		
		CarRequestImage savedimage = null;
		
		for(MultipartFile file :  photos)
		{
			 CarRequestImage img = new CarRequestImage();
			 img.setPhotos(file.getOriginalFilename());
			 img.setRequestid(s1);
			 
			 savedimage = r.save(img);
		}
		
		return savedimage;
		
	}

	@Override
	public List<CarRequestImage> viewall() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public void deleteimage(int id) {
		// TODO Auto-generated method stub
		r.deleteById(id);
	}

	@Override
	public CarRequestImage update(int id, String photos) {

	    CarRequestImage s1 = r.findById(id).orElseThrow();
	    s1.setPhotos(photos);
	    return r.save(s1);
	}

	@Override
	public List<CarRequestImage> fetchsingleimage(int car_id) {
		
		m.findById(car_id).orElseThrow();
		
		List<CarRequestImage>images = r.findByRequestid_Sellarcarid(car_id);
		
		for(CarRequestImage img : images)
		{
			img.setPhotos(a.getImagebaseurl() + img.getPhotos());
		}
		
		return images;
	}


	
	

	
	
}
