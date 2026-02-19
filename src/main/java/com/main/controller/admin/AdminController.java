package com.main.controller.admin;

import java.awt.MediaTracker;
//import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Admin;
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
import com.main.entity.BookDrive;
import com.main.entity.CarImage;
import com.main.entity.Cars;
import com.main.entity.Old_car;
import com.main.entity.Old_car_payment;
import com.main.entity.Payment;
import com.main.entity.Ragister;
import com.main.entity.contect;
import com.main.entity.admin.Admins;
import com.main.entity.sellar.Carrequest;
import com.main.entity.sellar.Sellarcontect;
import com.main.service.BookServiceInterface;
import com.main.service.PaymentInterface;
import com.main.service.contectinterface;
import com.main.service.getcarshow;
import com.main.service.old_car_interface;
import com.main.service.old_car_payment_interface;
import com.main.service.ragisterinterface;
import com.main.service.Admin.AdminInterface;
import com.main.service.Admin.New_car_img_interface;
import com.main.service.Admin.old_car_img_interface;
import com.main.service.sellar.CarRequestInerface;
import com.main.service.sellar.sellarcon;

//@CrossOrigin(origins = "https://rococo-lollipop-58fe1b.netlify.app")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ragisterinterface r;
    
    @Autowired
	BookServiceInterface s;
    
    @Autowired
	contectinterface c;

	@Autowired
	PaymentInterface s1;
	
	
	@Autowired
	old_car_payment_interface r1;
	
	@Autowired
	CarRequestInerface sq;
	
	@Autowired
	getcarshow a;
	
	@Autowired
	New_car_img_interface o;
	
	
	@Autowired
	CarRequestInerface s2;
	
	
	@Autowired
	old_car_interface i;
	
	
	@Autowired
	old_car_img_interface p;
	
	@Autowired
	AdminInterface op;
	
	@Autowired
	BookServiceInterface s8;
	
	
	@Autowired
	sellarcon k;
	
	@Autowired
	AdminInterface si;
	
	
	@Autowired
	old_car_payment_interface old;
	
	
	
	@DeleteMapping("/delete/newcar/{id}")
	public void Deletebyid(@PathVariable int id)
	{
		a.Deletecarbyid(id);
	}
	
	@GetMapping("/fetch/oldcar/payment")
	public  ResponseEntity <List<Old_car_payment>>fetchdetaoldcar()
	{
		List<Old_car_payment> op =  old.viewall();
		
		
		return ResponseEntity.ok(op);
	}
	
	
	@PostMapping("/oldcarremainder/{id}")
    public ResponseEntity<Old_car_payment>remainderpaymentbyoldcar(@PathVariable int id)
    {
    	Old_car_payment n = old.oldcarpaymentlink(id);
    	
    	
    	return ResponseEntity.ok(n);
    }
    
    
	
	
	
	
	
	@PutMapping(value = "/update/admin/prfofile/{id}", 
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?>updateadminprofile(@PathVariable int id ,
			@RequestPart("profile") Admins s1 ,
			@RequestParam String old_password ,
			 @RequestPart(name = "photo" , required = false) MultipartFile photo ){
		
		
		try
		{
			Admins s2 = si.updateadmin(id, s1, photo, old_password);
			return new ResponseEntity(s2 , HttpStatus.OK);
		}
		catch(Exception e) {
//			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message" , e.getMessage()));
		}
		
		
		
	}
	

	@GetMapping("/fetch/single/admins/{id}")
	public ResponseEntity<Admins>fetchdeta(@PathVariable int id)
	{
		Admins p =  si.singledeta(id);
		
		return  ResponseEntity.ok(p);
	}
	
	
	@GetMapping("/sellar/contects")
	public ResponseEntity<Sellarcontect>viewcontectsbysellar()
	{
		List<Sellarcontect>y = k.viewsellarcontect();
		
		
		return new ResponseEntity(y , HttpStatus.OK);
	}
	
	@PutMapping("/sellarcontect/status/{id}")
	public ResponseEntity<Sellarcontect>updatestatusbysellar(@PathVariable int id , @RequestParam String status , @RequestParam String message ){
		
		Sellarcontect u = k.updatestatuscontect(id, status, message);
		
		return new ResponseEntity(u , HttpStatus.OK);
	}
	
	
	
	
	
	@PostMapping(
		    value = "/add/newcar",
		    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
		)
		public ResponseEntity<Cars> insertcar(
		    @RequestPart("cars") String carsJson,
		    @RequestPart(value = "photo", required = false) MultipartFile photo
		) throws Exception {

		    ObjectMapper mapper = new ObjectMapper();
		    Cars c = mapper.readValue(carsJson, Cars.class);

		    Cars saved = a.insertcard(c, photo);
		    return ResponseEntity.ok(saved);
		}

	
	@GetMapping("/view/anewcar")
	public ResponseEntity<List<Cars>>viewnewcars(){
		List<Cars>view = a.viewcars();
		
		
		return new ResponseEntity(view , HttpStatus.OK);
	}
	
	
    
	

	
	
    // VIEW ALL USERS
    @GetMapping("/view-all-user")
    public ResponseEntity<List<Ragister>> viewalls() {
        List<Ragister> s1 = r.viewall();
        return new ResponseEntity<>(s1, HttpStatus.OK);
    }
    
    
    
    @PostMapping("/remainder/payment/{id}")
    public ResponseEntity<Payment>reminderpayment(@PathVariable int id)
    {
    	Payment n = s1.remenderppayment(id);
    	
    	return new ResponseEntity(n , HttpStatus.OK);
    }
    
    


    // DELETE USER
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleatuser(@PathVariable("id") int id) {
        r.deleatuser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    //  FETCH SINGLE USER
    @GetMapping("/fetch-singleuser/{id}")
    public ResponseEntity<Ragister> singledeta(@PathVariable("id") int id) {
        Ragister s1 = r.fatchsingleuser(id);
        return new ResponseEntity<>(s1, HttpStatus.OK);
    }


    
    @GetMapping("/booking/view")
	public ResponseEntity<List<BookDrive>>viewbooking()
	{
		List<BookDrive>b = s.viewBook();
		
		return new ResponseEntity<>(b , HttpStatus.OK);
	}
	
	@DeleteMapping("/booking/{id}")
    public void deletbooking(@PathVariable int id)
    {
		s.DeletBooking(id);
    }
	
	
	@GetMapping("/contect/view")
	public ResponseEntity<List<contect>>viewall()
	{
		List<contect>s = c.viewcontect();
		
		return new ResponseEntity<>(s , HttpStatus.OK);
	}
	
	@DeleteMapping("/contect/delete/{id}")
	public void deletedeta(@PathVariable int id)
	{
		c.deletecontect(id);
	}
	
	
	@PutMapping("/contect/update/{id}")
	public ResponseEntity<contect>updatecontect(@PathVariable int id , @RequestParam String status , @RequestParam String message)
	{
		contect s = c.updaecpntect(id, status , message);
		
		return new ResponseEntity<>(s , HttpStatus.OK);
	}
	
	@GetMapping("/car/payment/view")
	public ResponseEntity<List<Payment>>viewPayment()
	{
		List<Payment>s3 =  s1.viewpayment();
		
		return new ResponseEntity(s3 , HttpStatus.OK);
	}
	
	
	@PutMapping("/car/payment/update/status/{id}")
	public ResponseEntity<Payment>UpdatePaymentStatus(@PathVariable int id , @RequestParam String status)
	{
		return new ResponseEntity(s1.updatestatus(id, status) , HttpStatus.OK);
	}

	@GetMapping("/oldcar/payment/view")
	public ResponseEntity<List<Old_car_payment>>viewoldPayment()
	{
		
		List<Old_car_payment>s3  =  r1.viewall();
		
		return new ResponseEntity(s3 , HttpStatus.OK);
	}
	
	@PutMapping("/oldcar/payment/update/status")
	public ResponseEntity<Payment>UpdateoldPaymentStatus(@PathVariable int id , @RequestParam String status)
	{
		return new ResponseEntity(r1.updatestatus(id, status) , HttpStatus.OK);
	}
	
	
	
	@GetMapping("/request/view")
	public ResponseEntity<List<Carrequest>>viewrequest()
	{
		List<Carrequest>v = sq.viewall();
		
		return new ResponseEntity(v , HttpStatus.OK);
	}
	
	@PutMapping("/request/update")
	public ResponseEntity<Carrequest>updatestatus(@PathVariable int id , @RequestParam String status) {
		
        Carrequest p = sq.updatestatus(id, status);
		
		return new ResponseEntity(p , HttpStatus.OK);


	
	}
	
	@GetMapping("/viewadmin")
	public ResponseEntity<List<Admins>>view()
	{
		List<Admins>h =  op.viewadmin();
		
		return new ResponseEntity(h , HttpStatus.OK);
	}
	
	
	@PutMapping("/request/reject")
	public void deleterequest(@PathVariable int id)
	{
		sq.deleterequest(id);
	}
	
	@PostMapping(  value = "/new/carimage/add/{car_id}" , 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE
			)
	public ResponseEntity<CarImage>insertdeta(@PathVariable int car_id ,  @RequestParam("photos") List<MultipartFile>photos)
	{
		
		
		List<CarImage>h1  =  o.insertimages(car_id, photos);
		
		return new ResponseEntity(h1 , HttpStatus.OK);
	}
	
	
	@GetMapping("/view/r")
	public ResponseEntity<List<Carrequest>>viewallreq()
	{
	
		List<Carrequest>v = s2.viewall();
		return new ResponseEntity(v , HttpStatus.OK);
	}
	
	
	@PutMapping("/request/update/{id}")
	public ResponseEntity<Carrequest>updatedeta(@PathVariable int id , @RequestParam String status)
	{
		Carrequest s3 = s2.updatestatus(id, status);
		
		return new ResponseEntity(s2, HttpStatus.OK);	
	}
	
	@DeleteMapping("/request/reject/{id}")
	public void deleterequests(@PathVariable int id)
	{
		s2.deleterequest(id);
	}
	
	@PostMapping("/request/add/oldcar")
	public ResponseEntity<Old_car> insertoldcar(
	        @RequestBody Old_car car
	) {
	    Old_car saved = i.insertoldcar(car);
	    return ResponseEntity.ok(saved);
	}
	
	@PutMapping("/update/oldcar/status/{id}")
	public ResponseEntity<Old_car_payment> updateoldcarstatus(@PathVariable int id  , @RequestParam String status)
	{
		Old_car_payment out = old.updatestatus(id, status);
		
		
		return new ResponseEntity(out , HttpStatus.OK);
	}
	

	@PostMapping("/request/view/singleimages/{requestCarId}")
	public ResponseEntity<?> approve(@PathVariable int requestCarId)
	{
	    Old_car u = p.insertOldCarFromRequest(requestCarId);
	    return ResponseEntity.ok(u);
	}

	
	@PutMapping("/request/drivestatus/{id}")
	public ResponseEntity<BookDrive>updateBookdrive(@PathVariable int id , @RequestParam String status)
	{
		
		BookDrive n = s8.updateStatus(id, status);
		
		return new ResponseEntity(n , HttpStatus.OK);
	}
	
	
}
