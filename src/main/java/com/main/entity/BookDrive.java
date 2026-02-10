package com.main.entity;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jdk.jfr.Timespan;

@Entity
public class BookDrive {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	Cars car;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	Ragister r;
	
	
	private String date;
	
	private String city;
		
	private String status;
	
	 @CreationTimestamp
	 @Column(updatable = false)
	 private Timestamp created_at;

	 public int getBook_id() {
		 return book_id;
	 }

	 public void setBook_id(int book_id) {
		 this.book_id = book_id;
	 }




	 public Cars getCar() {
		return car;
	}

	 public void setCar(Cars car) {
		 this.car = car;
	 }

	 public Ragister getR() {
		 return r;
	 }

	 public void setR(Ragister r) {
		 this.r = r;
	 }

	 public String getDate() {
		 return date;
	 }

	 public void setDate(String date) {
		 this.date = date;
	 }

	 public String getCity() {
		 return city;
	 }

	 public void setCity(String city) {
		 this.city = city;
	 }

	 public String getStatus() {
		 return status;
	 }

	 public void setStatus(String status) {
		 this.status = status;
	 }

	 public Timestamp getCreated_at() {
		 return created_at;
	 }

	 public void setCreated_at(Timestamp created_at) {
		 this.created_at = created_at;
	 }
	 
	 
	 

	
	
	
	
}
