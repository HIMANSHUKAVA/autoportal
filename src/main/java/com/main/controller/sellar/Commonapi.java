package com.main.controller.sellar;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.main.entity.Ragister;
import com.main.entity.sellar.Sellarcontect;
import com.main.repo.sellar.Sellarcontects;
import com.main.service.ragisterinterface;
import com.main.service.sellar.sellarcon;

@RestController
@RequestMapping("/sellar")
//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")

public class Commonapi {

	
	@Autowired
	ragisterinterface s;
	
	
	@Autowired
	sellarcon k;
	
	
	@PostMapping("/contect/s")
	public ResponseEntity<Sellarcontect>insertsellar(@RequestBody Sellarcontect l)
	{
		Sellarcontect o = k.insertcontect(l);
		
		return ResponseEntity.ok(o);
	}
	
	
	
	
	
	@PutMapping(
		    value = "/editprofile/{id}",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
		)
	public ResponseEntity<?>update(@PathVariable int id ,
			
			
			
			@RequestPart("profile") Ragister s1,
			@RequestParam String old_password ,
			@RequestPart(name = "photo" , required = false) MultipartFile photo
			)
	{
		
		
		try
		{
		Ragister s2 =  s.updatuser(id, s1, old_password , photo);	
		
		
		return new ResponseEntity(s2 , HttpStatus.OK);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message" , e.getMessage()));
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Ragister>singledeta(@PathVariable int id)
	{
		Ragister s3 = s.fatchsingleuser(id);
		
		return new ResponseEntity(s3 , HttpStatus.OK);
	}
	
	
	@PutMapping("/editpassword/{id}")
	public ResponseEntity<?>updatepassword(@PathVariable int id , @RequestParam String currentpassword, @RequestParam String newpassword)
	{
		try
		{
		
			Ragister rs = s.updatepassword(id, currentpassword, newpassword);
			
			return new ResponseEntity(rs,HttpStatus.OK);
		
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message" , e.getMessage()));
		}
		
		
	}
	
}
