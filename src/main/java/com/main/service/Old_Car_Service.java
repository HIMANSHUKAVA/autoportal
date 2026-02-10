package com.main.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Cars;
import com.main.entity.Old_car;
import com.main.repo.old_car_repo;

@Service
public class Old_Car_Service implements old_car_interface {

	@Autowired
	old_car_repo r;
	
	@Override
	public List<Old_car> GetcarByBrand(String brand) {
		// TODO Auto-generated method stub
		return r.findByBrand(brand);
	}

	@Override
	public List<Old_car> GetcarByType(String type) {
		// TODO Auto-generated method stub
		return r.findByType(type);
	}

	@Override
	public List<Old_car> getBrandAndTypeCar(String brand, String type) {
		// TODO Auto-generated method stub
		return r.findByBrandAndType(brand, type);
	}

	@Override
	public List<Old_car> ListBypricerange(int min, int max) {
		// TODO Auto-generated method stub
		return r.findByPriceGreaterThanEqualAndPriceLessThanEqual(min, max);
	}

	@Override
	public List<Old_car> getOld_carByAllFilters(String brand, String type, int min, int max) {
		// TODO Auto-generated method stub
		return r.findByBrandAndTypeAndPriceGreaterThanEqualAndPriceLessThanEqual(brand, type, min, max);
	}

	@Override
	public Old_car getCarWithImages(int id) {
		// TODO Auto-generated method stub
		return r.findCarWithImages(id);
	}

	 @Override
	    public int converttonumber(String value) {

	        if (value.contains("Lakh")) {
	            value = value.replace("Lakh", "").trim();
	            return (int) (Double.parseDouble(value) * 100000);
	        }

	        else if (value.contains("Cr")) {
	            value = value.replace("Cr", "").trim();
	            return (int) (Double.parseDouble(value) * 10000000); // 1 Cr = 1 Crore = 1 * 10000000
	        }

	        return (int) Double.parseDouble(value);
	    }


	    // PRICE FILTER ONLY
	    @Override
	    public List<Old_car> getpricelabeling(String price) {

	        price = price.replace("₹", "").trim();

	        int min, max;

	        if (price.startsWith("Above")) {
	            String val = price.replace("Above", "").trim();
	            min = converttonumber(val);
	            max = Integer.MAX_VALUE;
	            return r.findByPriceGreaterThanEqualAndPriceLessThanEqual(min, max);
	        }

	        // Normal range "4-8 Lakh" or "70 Lakh-1 Cr"
	        String[] part = price.split("-");

	        min = converttonumber(part[0].trim());
	        max = converttonumber(part[1].trim());

            return r.findByPriceGreaterThanEqualAndPriceLessThanEqual(min, max);
	    }


	@Override
	public List<Old_car> getallfilter(String brand, String type, String pricelabel) {
		  pricelabel = pricelabel.replace("₹", "").trim();

	        int min = 0, max = 0;

	        // FORMAT 1: "4-8 Lakh" / "70 Lakh-1 Cr"
	        if (pricelabel.contains("-") && !pricelabel.startsWith("Above")) {

	            String[] part = pricelabel.split("-");

	            min = converttonumber(part[0].trim());
	            max = converttonumber(part[1].trim());
	        }

	        // FORMAT 2: "80 Lakh 1.3 Cr"
	        else if (pricelabel.contains(" ")) {

	            String[] part = pricelabel.split(" ");

	            min = converttonumber(part[0].trim());
	            max = converttonumber(part[1].trim());
	        }

	        // FORMAT 3: "Above 1 Cr"
	        else if (pricelabel.startsWith("Above")) {

	            String val = pricelabel.replace("Above", "").trim();

	            min = converttonumber(val);
	            max = Integer.MAX_VALUE;
	        }

	        return r.findByBrandAndTypeAndPriceGreaterThanEqualAndPriceLessThanEqual(brand, type, min, max);
	        
	}

	@Override
	public List<Old_car> GetFeaturedcars() {
		// TODO Auto-generated method stub
		return r.findByFeaturedTrue();
	}

	@Override
	public Old_car insertoldcar(Old_car c) {
		// TODO Auto-generated method stub
		
			
		return r.save(c);
		
}
	

	
}
