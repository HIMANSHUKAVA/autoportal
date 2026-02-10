package com.main.repo.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.entity.admin.Admins;
import com.main.mail.Admin;

public interface Adminrepo  extends JpaRepository<Admins, Integer>{

	
	@Query("select r from Admins r where r.email = :email")
	 Optional<Admins>findEmailadmin(@Param("email") String email);

	
}
