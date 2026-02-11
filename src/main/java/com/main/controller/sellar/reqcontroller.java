package com.main.controller.sellar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.sellar.CarRequestImage;
import com.main.repo.sellar.carRequestrepo;
import com.main.service.sellar.CarRequestInerface;
import com.main.service.sellar.CarRquestImgInterface;

@RestController
@RequestMapping("/sellar")
//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")

public class reqcontroller {

	
	@Autowired
	CarRquestImgInterface i;
	
	@PostMapping("/request/image/add/{req_id}")
	public ResponseEntity<CarRequestImage>insertimage(@PathVariable int req_id , @RequestParam("photos") List<MultipartFile>photos)
	{
		
		CarRequestImage k = i.insertdeta(req_id, photos);
		
		return new ResponseEntity(k,HttpStatus.OK);
	}
	
	@GetMapping("/request/image/view")
	public ResponseEntity<List<CarRequestImage>>view()
	{
		List<CarRequestImage>k = i.viewall();
		
		return new ResponseEntity(k, HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/request/img/delete/{id}")
	public void deletedeta(@PathVariable int id) {
		i.deleteimage(id);
	}
	
	@PutMapping("/request/img/update/{id}")
	public ResponseEntity<CarRequestImage>ipdatedeta(@PathVariable int id , @RequestParam String photos)
	{
		CarRequestImage m =  i.update(id, photos);
		
		return new ResponseEntity(m , HttpStatus.OK);
	}
	
	
	@GetMapping("/request/fetchsingle/{car_id}")
	public ResponseEntity<List<CarRequestImage>>singledeta(@PathVariable int car_id)
	{
		List<CarRequestImage> s6 = i.fetchsingleimage(car_id);
		
		return new ResponseEntity(s6 , HttpStatus.OK);
	}
	
	
	
	
	
}
