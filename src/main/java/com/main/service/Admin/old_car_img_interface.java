package com.main.service.Admin;

import java.util.List;

import com.main.entity.Old_car;
import com.main.entity.old_CarImage;

public interface old_car_img_interface {

	
	public Old_car insertOldCarFromRequest(int requestCarId, Old_car car);
	
	public List<old_CarImage>fetchsingleid(int id);
	
	
	
}
