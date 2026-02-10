package com.main.entity.sellar;



import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.main.entity.Ragister;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Carrequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int sellarcarid;
	double price;
	String brand;
	String model;
	String fuel;
	String colour;
	@Column(name = "car_condition")
	String carcondition;
	String transmission;
	int km_driven;
    @Column(columnDefinition = "TEXT")
    private String photo;
	String status;
	
	@CreationTimestamp
	@Column(updatable = false)
	Timestamp requestAt;
	
	
	String priceLabel;
	private int priceMax;
	private int PriceMin;
	
	private int year;
	private String type;
	
	
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(String priceLabel) {
		this.priceLabel = priceLabel;
	}

	public int getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}

	public int getPriceMin() {
		return PriceMin;
	}

	public void setPriceMin(int priceMin) {
		PriceMin = priceMin;
	}

	public List<CarRequestImage> getImages() {
		return images;
	}

	public void setImages(List<CarRequestImage> images) {
		this.images = images;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	Ragister rs;

	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "requestid")
	@JsonManagedReference
	List<CarRequestImage>images;
	

	
	
	
	


	public String getCarcondition() {
		return carcondition;
	}

	public void setCarcondition(String carcondition) {
		this.carcondition = carcondition;
	}



	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getCondition() {
		return carcondition;
	}

	public void setCondition(String condition) {
		this.carcondition = condition;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRequestAt() {
		return requestAt;
	}

	public void setRequestAt(Timestamp requestAt) {
		this.requestAt = requestAt;
	}

	public int getSellarcarid() {
		return sellarcarid;
	}

	public void setSellarcarid(int sellarcarid) {
		this.sellarcarid = sellarcarid;
	}

	public Ragister getRs() {
		return rs;
	}

	public void setRs(Ragister rs) {
		this.rs = rs;
	}
	
	
	
	
	
	
}
