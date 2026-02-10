package com.main.repo.sellar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.entity.sellar.Carrequest;

public interface carRequestrepo  extends JpaRepository<Carrequest,Integer>{

	
	@Query("""
			   SELECT c 
			   FROM Carrequest c
			   LEFT JOIN FETCH c.images
			   WHERE c.sellarcarid = :id
			""")
	Carrequest findCarWithImages(int id);
	
}
