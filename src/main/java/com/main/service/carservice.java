package com.main.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Cars;
import com.main.repo.carrepository;

@Service
public class carservice implements getcarshow {

    @Autowired
    carrepository r;

    // Convert value (4, 8, 1.3 etc.) to rupees based on Lakh or Cr
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
    public List<Cars> getpricelabeling(String price) {

        price = price.replace("₹", "").trim();

        int min, max;

        if (price.startsWith("Above")) {
            String val = price.replace("Above", "").trim();
            min = converttonumber(val);
            max = Integer.MAX_VALUE;
            return r.findByPriceMinGreaterThanEqualAndPriceMaxLessThanEqual(min, max);
        }

        // Normal range "4-8 Lakh" or "70 Lakh-1 Cr"
        String[] part = price.split("-");

        min = converttonumber(part[0].trim());
        max = converttonumber(part[1].trim());

        return r.findByPriceMinGreaterThanEqualAndPriceMaxLessThanEqual(min, max);
    }



    // BRAND ONLY
    @Override
    public List<Cars> GetcarByBrand(String brand) {
        return r.findByBrand(brand);
    }

    // TYPE ONLY
    @Override
    public List<Cars> GetcarByType(String type) {
        return r.findByType(type);
    }

    // BRAND + TYPE
    @Override
    public List<Cars> getBrandAndTypeCar(String brand, String type) {
        return r.findByBrandAndType(brand, type);
    }

    // PRICE RANGE ONLY
    @Override
    public List<Cars> ListBypricerange(int min, int max) {
        return r.findByPriceMinGreaterThanEqualAndPriceMaxLessThanEqual(min, max);
    }

    // ALL FILTERS
    @Override
    public List<Cars> getCarsByAllFilters(String brand, String type, int min, int max) {
        return r.findByBrandAndTypeAndPriceMinGreaterThanEqualAndPriceMaxLessThanEqual(brand, type, min, max);
    }

    // FULL DETAILS + IMAGES
    @Override
    public Cars getCarWithImages(int id) {
        return r.findCarWithImages(id);
    }


    // FINAL FILTER (brand + type + priceLabel)
    @Override
    public List<Cars> getallfilter(String brand, String type, String pricelabel) {

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

        return r.findByBrandAndTypeAndPriceMinGreaterThanEqualAndPriceMaxLessThanEqual(
                brand, type, min, max
        );
    }


	@Override
	public List<Cars> getfeaturedcar() {
		// TODO Auto-generated method stub
		return r.findByFeaturedTrue();
	}


	@Override
	public Cars insertcard(Cars c, MultipartFile photo) {
		
		Cars s = new Cars();
		s.setBrand(c.getBrand());
		s.setType(c.getType());
		s.setColor(c.getColor());
		s.setDescription(c.getDescription());
		s.setFuel(c.getFuel());
		s.setPriceMax(c.getPriceMax());
		s.setPriceMin(c.getPriceMin());
		s.setPriceLabel(c.getPriceLabel());
		s.setTransmission(c.getTransmission());
		s.setPrice(c.getPrice());
		s.setModel(c.getModel());
		s.setCarType(c.getCarType());
		
		
		if(photo !=null && !photo.isEmpty())
		{
			try
			{
			String filename =  System.currentTimeMillis()  + "-" +  photo.getOriginalFilename();
		    photo.transferTo(new File("/Users/kavahimanshu/images/" + filename));
		    s.setImage_url("http://localhost:3000/images/" + filename);
			}
			catch(Exception e)
		    {

				 throw new RuntimeException("Image upload failed", e);

		    }
		}
		
		
		return r.save(s);
	}
}
 