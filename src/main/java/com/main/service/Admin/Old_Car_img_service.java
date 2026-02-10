package com.main.service.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Old_car;
import com.main.entity.old_CarImage;
import com.main.entity.sellar.CarRequestImage;
import com.main.entity.sellar.Carrequest;
import com.main.repo.old_car_img_repo;
import com.main.repo.old_car_repo;
import com.main.repo.sellar.CarImageRepo;
import com.main.repo.sellar.carRequestrepo;

import jakarta.transaction.Transactional;

@Service
public class Old_Car_img_service  implements old_car_img_interface{

	@Autowired
	old_car_img_repo r;
	
	@Autowired
	CarImageRepo repo;
	@Autowired
	old_car_repo l;
	
	@Autowired
	carRequestrepo o;
	
	@Override
	@Transactional
	public Old_car insertOldCarFromRequest(int requestCarId, Old_car car) {

	    
	    Carrequest t = o.findById(requestCarId).orElseThrow();
	    
	    t.setStatus("APPROVED");
	    o.save(t);

	    
	 // 1️ save old car
	    Old_car savedCar = l.save(car);
	    
	    // 2️fetch request images
	    List<CarRequestImage> requestImages =
	        repo.findByRequestid_Sellarcarid(requestCarId);

	    // 3️ copy URLs
	    for (CarRequestImage img : requestImages) {

	        old_CarImage oldImg = new old_CarImage();
	        oldImg.setCar(savedCar);   // FK
	        oldImg.setUrl(img.getPhotos()); // sirf URL

	        r.save(oldImg);
	    }

	    return savedCar;
	}



	@Override
	public List<old_CarImage> fetchsingleid(int id) {
		// TODO Auto-generated method stub
		
		List<old_CarImage>s2 =  r.findByCar_Id(id);
		return s2;
	}

}
