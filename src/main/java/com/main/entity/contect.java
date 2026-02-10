package com.main.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class contect {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String email;
	String message;
	String subject;
	
	String status = "pending";
	
	@Column(columnDefinition = "TEXT")
	String resolve ="";
	
	 public String getResolve() {
		return resolve;
	}

	public void setResolve(String resolve) {
		this.resolve = resolve;
	}

	 @CreationTimestamp
	 @Column(updatable = false)
	 Timestamp  contect_at;

	 public int getId() {
		 return id;
	 }

	 public void setId(int id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public String getEmail() {
		 return email;
	 }

	 public void setEmail(String email) {
		 this.email = email;
	 }

	 public String getMessage() {
		 return message;
	 }

	 public void setMessage(String message) {
		 this.message = message;
	 }

	 public String getSubject() {
		 return subject;
	 }

	 public void setSubject(String subject) {
		 this.subject = subject;
	 }

	 public String getStatus() {
		 return status;
	 }

	 public void setStatus(String status) {
		 this.status = status;
	 }

	 public Timestamp getContect_at() {
		 return contect_at;
	 }

	 public void setContect_at(Timestamp contect_at) {
		 this.contect_at = contect_at;
	 }
	 

	
	
	
    	
	
}
