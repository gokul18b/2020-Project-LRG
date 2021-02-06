package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.AddCourierRequest;
import com.example.demo.request.AddCustomerRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SearchRequest;
import com.example.demo.request.UpdateCourierRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.SearchResponse;
import com.example.demo.response.ViewCustomerResponse;
import com.example.demo.response.viewBillResponse;
import com.example.demo.service.ApiService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = { "/api" })
public class ApiController {

	@Autowired
	ApiService service;

	@GetMapping("/viewCustomer")
	public ResponseEntity<List<ViewCustomerResponse>> viewCustomer() {
		return ResponseEntity.ok().body(service.viewCustomer());
	}

	@GetMapping(value="/login/{username}/{password}")
	public ResponseEntity<List<LoginResponse>> login(@PathVariable String username,@PathVariable String password){
		LoginRequest body = new LoginRequest();
		body.setUsername(username);
		body.setPassword(password);
		return ResponseEntity.ok().body(service.login(body));
	}
	
	

	@PostMapping("/addCustomer/{firstname}/{lastname}/{mobile}/{email}/{age}/{gender}/{address}")
	public ResponseEntity<String> addCustomer(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname,@PathVariable("mobile") String mobile,
			@PathVariable("age") Integer age,@PathVariable("email") String email,@PathVariable("gender") String gender,
			@PathVariable("address") String address) {
		AddCustomerRequest body = new AddCustomerRequest();
		body.setFirstname(firstname);
		body.setLastname(lastname);
		body.setMobile(mobile);
		body.setEmail(email);
		body.setAge(age);
		body.setGender(gender);
		body.setAddress(address);
		
		return ResponseEntity.ok().body(service.addCustomer(body));
	}

	@PostMapping("/addCourier/{name}/{mobile}/{sendlocation}/{fulladdress}/{amount}/{weight}/{discount}")
	public ResponseEntity<String> addCourier(@PathVariable("name") String name,@PathVariable("mobile") String mobile,@PathVariable("sendlocation") String sendlocation,@PathVariable("fulladdress") String fulladdress,@PathVariable("amount") Integer amount,@PathVariable("weight") String weight,@PathVariable("discount") String discount) {
		AddCourierRequest body = new AddCourierRequest();
		body.setName(name);
		body.setMobile(mobile);
		body.setSendlocation(sendlocation);
		body.setFulladdress(fulladdress);
		body.setAmount(amount);
		body.setWeight(weight);
		body.setDiscount(discount);
		return ResponseEntity.ok().body(service.addCourier(body));
	}
	
	@GetMapping("/viewCourier")
	public ResponseEntity<List<SearchResponse>> addCourier() {
		return ResponseEntity.ok().body(service.viewCourier());
	}

	@GetMapping("/viewBill")
	public ResponseEntity<List<viewBillResponse>> viewBill() {
		return ResponseEntity.ok().body(service.viewBill());
	}

	
	@PostMapping("/updateCourier/{courierno}/{currentlocation}")
	public ResponseEntity<String> updateCourier(@PathVariable("courierno") String courierno,@PathVariable("currentlocation") String currentlocation) {
		UpdateCourierRequest body = new UpdateCourierRequest();
		body.setCourierno(courierno);
		body.setCurrentlocation(currentlocation);
		return ResponseEntity.ok().body(service.updateCourier(body));
	}

	@PostMapping("/search/{courierno}")
	public ResponseEntity<List<SearchResponse>> search(@PathVariable("courierno") String courierno) {
		SearchRequest body = new SearchRequest();
		body.setCourierno(courierno);
		return ResponseEntity.ok().body(service.search(body));
	}

}
