package com.main.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.entity.Ragister;

public interface ragisterrepo  extends JpaRepository<Ragister, Integer>
{

	
	Optional<Ragister>findByEmailAndPassword(String email , String Password);
	
    Optional<Ragister>findByEmail(String email);
    
    @Query("SELECT r FROM Ragister r WHERE r.email = :email")
    Optional<Ragister> findByEmail1(@Param("email") String email);
    
    
    
    
    
    
    
}
