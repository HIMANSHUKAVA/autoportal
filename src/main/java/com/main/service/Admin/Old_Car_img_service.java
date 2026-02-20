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
	public Old_car insertOldCarFromRequest(int requestCarId) {

	    Carrequest request = o.findById(requestCarId)
	            .orElseThrow(() -> new RuntimeException("Request not found"));

	    request.setStatus("APPROVED");

	    Old_car oldCar = new Old_car();

	    oldCar.setBrand(request.getBrand());
	    oldCar.setModel(request.getModel());
	    oldCar.setFuel(request.getFuel());
	    oldCar.setTransmission(request.getTransmission());
	    oldCar.setColor(request.getColour());
	    oldCar.setKm_driven(request.getKm_driven());
	    oldCar.setYear(request.getYear());
	    oldCar.setType(request.getType());
	    oldCar.setCarcondition(request.getCarcondition());
	    List<CarRequestImage> images = repo.findByRequestid_Sellarcarid(requestCarId);

	    if(!images.isEmpty()){
	        oldCar.setImage_url(images.get(0).getPhotos());
	    }
	    else {
	        oldCar.setImage_url("mahindra-xuv.avif"); //  default path
	    }


	 
	   
	    oldCar.setPrice((int) request.getPrice());
	    oldCar.setPriceMin(request.getPriceMin());
	    oldCar.setPriceMax(request.getPriceMax());
	    oldCar.setPriceLabel(request.getPriceLabel());

	    oldCar.setDescription("Approved car");
	    oldCar.setCarType("OLD");

	    Old_car savedCar = l.save(oldCar);

	    System.out.println("Saved Car ID: " + savedCar.getId());
	    System.out.println("PHOTO FROM REQUEST: " + request.getPhoto());


	    return savedCar;
	}




	@Override
	public List<old_CarImage> fetchsingleid(int id) {
		// TODO Auto-generated method stub
		
		List<old_CarImage>s2 =  r.findByCar_Id(id);
		return s2;
	}

}
