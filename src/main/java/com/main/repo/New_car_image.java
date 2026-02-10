package com.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.CarImage;

public interface New_car_image extends JpaRepository<CarImage, Integer> {

	
	List<CarImage>findByCar_id(int car_id);
	
	
	
}
