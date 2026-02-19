package com.main.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Cars;

public interface getcarshow {

	
	List<Cars>GetcarByBrand(String brand);
	
	List<Cars>GetcarByType(String type);
	
	List<Cars>getBrandAndTypeCar(String brand , String type);
	
	List<Cars>ListBypricerange(int min , int max);
	
	List<Cars> getCarsByAllFilters(String brand, String type, int min, int max);
	
	Cars getCarWithImages(int id);
	 int converttonumber(String value);
	 List<Cars>getpricelabeling(String price);
	 public List<Cars>getallfilter(String brand , String type , String pricelabel);
	 
	 List<Cars>getfeaturedcar();
	
	 public Cars insertcard( Cars c, MultipartFile photo);
	 
	 
	 public void  Deletecarbyid(int id);
	 
	 public List<Cars>viewcars();
}
