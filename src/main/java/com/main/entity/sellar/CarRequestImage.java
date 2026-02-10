package com.main.entity.sellar;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CarRequestImage {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int imgid;
	
	@Column(columnDefinition = "TEXT")
	String photos;
	
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name = "sellarcarid")
	 Carrequest requestid;


	public int getImgid() {
		return imgid;
	}


	public void setImgid(int imgid) {
		this.imgid = imgid;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	public Carrequest getRequestid() {
		return requestid;
	}


	public void setRequestid(Carrequest requestid) {
		this.requestid = requestid;
	}


	
	
	
	
	
	
	
	
}
