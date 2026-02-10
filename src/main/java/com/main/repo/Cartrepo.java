package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Addtocart;

public interface Cartrepo  extends JpaRepository<Addtocart, Integer>{

	
	
}
