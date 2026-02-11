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
import com.main.service.getcarshow;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")
public class carcontroller {

    @Autowired
    getcarshow s;

    @GetMapping("/cars/brand")
    public ResponseEntity<List<Cars>> getAllCarByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(s.GetcarByBrand(brand));
    }

    @GetMapping("/cars/type")
    public ResponseEntity<List<Cars>> getCarByType(@RequestParam String type) {
        return ResponseEntity.ok(s.GetcarByType(type));
    }

    @GetMapping("/cars/brand/type")
    public ResponseEntity<List<Cars>> getCarByBrandAndType(
            @RequestParam String brand,
            @RequestParam String type
    ) {
        return ResponseEntity.ok(s.getBrandAndTypeCar(brand, type));
    }

    @GetMapping("/cars/price-label")
    public ResponseEntity<List<Cars>> getCarsByPriceLabel(@RequestParam String pricelabel) {
        return ResponseEntity.ok(s.getpricelabeling(pricelabel));
    }

    @GetMapping("/car/filter")
    public ResponseEntity<List<Cars>> getAllFilters(
            @RequestParam String brand,
            @RequestParam String type,
            @RequestParam String pricelabel
    ) {
        return ResponseEntity.ok(s.getallfilter(brand, type, pricelabel));
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Cars> getCarDetails(@PathVariable int id) {
        return ResponseEntity.ok(s.getCarWithImages(id));
    }
    
    @GetMapping("/cars/featured")
    public List<Cars> featuredCars() {
        return s.getfeaturedcar();
    }

    
    
}
