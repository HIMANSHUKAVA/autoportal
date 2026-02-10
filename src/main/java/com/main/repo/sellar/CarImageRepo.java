package com.main.repo.sellar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.sellar.CarRequestImage;

public interface CarImageRepo extends JpaRepository<CarRequestImage, Integer> {

	
	
	 List<CarRequestImage>findByRequestid_Sellarcarid(int car_id);
	
	
}
