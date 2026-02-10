package com.main.service.Admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.CarImage;
import com.main.entity.Cars;
import com.main.repo.New_car_image;
import com.main.repo.carrepository;

@Service
public class New_car_image_service  implements New_car_img_interface{

	@Autowired
	carrepository r;
	
   @Autowired
   New_car_image i;
	
	@Override
	public List<CarImage> insertimages(int car_id, List<MultipartFile> photo) {

		
		Cars n =  r.findById(car_id).orElseThrow();
		
		
		List<CarImage> s1 = new ArrayList<CarImage>();
		
		for(MultipartFile file : photo)
		{
			CarImage k1 = new CarImage();
			k1.setUrl(file.getOriginalFilename());
			k1.setCar(n);
			
			s1.add(i.save(k1));
		}
		
		
		return s1;
	}

	
	
	
	
}
