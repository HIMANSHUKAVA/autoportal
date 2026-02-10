package com.main.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Old_car;

public interface old_car_interface {

	
	
	List<Old_car>GetcarByBrand(String brand);
	
	List<Old_car>GetcarByType(String type);
	
	List<Old_car>getBrandAndTypeCar(String brand , String type);
	
	List<Old_car>ListBypricerange(int min , int max);
	
	List<Old_car> getOld_carByAllFilters(String brand, String type, int min, int max);
	
	Old_car getCarWithImages(int id);
	 int converttonumber(String value);
	 List<Old_car>getpricelabeling(String price);
	 public List<Old_car>getallfilter(String brand , String type , String pricelabel);
	 
	 List<Old_car>GetFeaturedcars();
	 
	 
	 public Old_car insertoldcar(Old_car c);
}
