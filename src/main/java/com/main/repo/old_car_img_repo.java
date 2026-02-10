package com.main.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.CarImage;
import com.main.entity.Old_car;
import com.main.entity.old_CarImage;

public interface old_car_img_repo extends JpaRepository<old_CarImage, Integer> {

	

	List<old_CarImage> findByCar_Id(int id);
}
