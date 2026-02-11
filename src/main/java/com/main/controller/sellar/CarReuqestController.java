package com.main.controller.sellar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.entity.sellar.CarRequestImage;
import com.main.entity.sellar.Carrequest;

import com.main.service.sellar.CarRequestInerface;

@RestController
@RequestMapping("/sellar")
@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")

public class CarReuqestController {

	
	@Autowired
	CarRequestInerface s1;
	
	
	
	
	@PostMapping(
		    value = "/request/add/{user_id}",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
		)
		public ResponseEntity<Carrequest> insertdeta(
		        @PathVariable int user_id,
		        @RequestPart("car") String carJson,
		        @RequestPart(value = "photo", required = false) MultipartFile photo
		) throws Exception {

		    ObjectMapper mapper = new ObjectMapper();
		    Carrequest c = mapper.readValue(carJson, Carrequest.class);

		    Carrequest saved = s1.insertrequest(user_id, c, photo);
		    return ResponseEntity.ok(saved);
		}

	
	@GetMapping("/request/view")
	public ResponseEntity<List<Carrequest>>viewrequest()
	{
		List<Carrequest>v = s1.viewall();
		
		return new ResponseEntity(v , HttpStatus.OK);
	}
	


	
	@GetMapping("/request/get/{id}")
	public ResponseEntity<Carrequest> singledeta(@PathVariable int id)
	{
		Carrequest s3 = s1.singledeta(id);
		
		return new ResponseEntity(s3 , HttpStatus.OK);	
	}
	
	
	
	
	@PutMapping("/request/update/model/{id}")
	public ResponseEntity<Carrequest>updatemodel(@PathVariable int id , @RequestPart("car") Carrequest s ,  @RequestPart(value = "photo", required = false) MultipartFile photo)
	{
		
	   Carrequest s5= s1.update(id, s , photo);
	   
	   return new ResponseEntity(s5 , HttpStatus.OK);
		
	}
	
	
	
}
