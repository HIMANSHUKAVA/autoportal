package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Cars;
import com.main.entity.Old_car;
import com.main.service.old_car_interface;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")
public class old_Car_controller {

	@Autowired
	old_car_interface r;
	

	
	@GetMapping("/oldcar/brand")
	public ResponseEntity<List<Old_car>>findbrand(@RequestParam String brand)
	{
		List<Old_car>s = r.GetcarByBrand(brand);
		
		return new ResponseEntity(s , HttpStatus.OK);
	}
	
	
	@GetMapping("/oldcar/type")
	public ResponseEntity<List<Old_car>>findtype(@RequestParam String type)
	{
		List<Old_car>s1 = r.GetcarByType(type);
		
		return new ResponseEntity(s1 , HttpStatus.OK);
		
	}
	
	@GetMapping("/oldcar/brand/type")
	public ResponseEntity<List<Old_car>>findbrandandtype(@RequestParam String brand , @RequestParam String type)
	{
		
		 return ResponseEntity.ok(r.getBrandAndTypeCar(brand, type));
	}
	
	@GetMapping("/oldcar/pricelabel")
	public ResponseEntity<List<Old_car>>findbypricelabel(@RequestParam String price)
	{
		  return ResponseEntity.ok(r.getpricelabeling(price));
	}
	
	
	 @GetMapping("/oldcar/filter")
     public ResponseEntity<List<Old_car>> getAllFilters(	
	            @RequestParam String brand,
	            @RequestParam String type,
	            @RequestParam String pricelabel
	    ) {
	        return ResponseEntity.ok(r.getallfilter(brand, type, pricelabel));
	    }
	
	 
	 @GetMapping("/oldcar/{id}")
	 public ResponseEntity<Old_car>getimage(@PathVariable int id)
	 {
		 return new ResponseEntity(r.getCarWithImages(id) , HttpStatus.OK);
	 }
	 
	
	
	    @GetMapping("/oldcars/featured")
	    public List<Old_car> featuredCars() {
	        return r.GetFeaturedcars();
	    }
	
	
	
}
