package com.main.service.Admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.main.entity.CarImage;

public interface New_car_img_interface {

	public List<CarImage>insertimages(int car_id,List<MultipartFile>photo);
}
