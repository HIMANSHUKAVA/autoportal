	package com.main.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Old_car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    private String brand;
    private String model;
    private String type;
    
    private int price;

  
    private int year;
    private String fuel;
    private String transmission;
    private int km_driven;
    private String color;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String carcondition;
    
    // Thumbnail image
    @Column(columnDefinition = "TEXT")
    private String image_url;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp created_at;

    // Relation with CarImage
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    @JsonManagedReference
    private List<old_CarImage> images;
    
	@Column(name = "price_max")
    private int priceMax;

    @Column(name = "price_label")
    private String priceLabel;
    
//    private String cartype="old";
    

    
    @Column(name = "car_type")
    private String carType;
    




	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}

	@Column(name = "price_min")
    private int priceMin;
    
    
    @Column(nullable = false)
    private boolean featured = false;
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public int getKm_driven() {
		return km_driven;
	}

	public void setKm_driven(int km_driven) {
		this.km_driven = km_driven;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public List<old_CarImage> getImages() {
		return images;
	}

	public void setImages(List<old_CarImage> images) {
		this.images = images;
	}

	public int getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}

	public String getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(String priceLabel) {
		this.priceLabel = priceLabel;
	}

	public int getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}

	public String getCarcondition() {
		return carcondition;
	}

	public void setCarcondition(String carcondition) {
		this.carcondition = carcondition;
	}
    
    
    
    
}
