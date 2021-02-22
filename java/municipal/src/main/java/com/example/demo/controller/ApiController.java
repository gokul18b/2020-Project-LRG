package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ApiDao;

import com.example.demo.response.GetCitizenResponse;
import com.example.demo.response.GetComplaintResponse;
import com.example.demo.service.ApiService;


@RestController
@RequestMapping(value = { "/api" })
public class ApiController {

	@Autowired
	ApiService service;
	
	@Autowired
	ApiDao dao;

	@GetMapping("/login/{username}/{password}")
	public Boolean login(@PathVariable String username,@PathVariable String password) {
		return service.login(username,password);
	}
	
	
	@PostMapping("/citizen_register/{firstname}/{lastname}/{mobile}/{address}/{city}/{state}/{pincode}/{username}/{password}")
	public String member_register(@PathVariable String firstname,
			@PathVariable String lastname,
			@PathVariable String  mobile,
			@PathVariable String address,
			@PathVariable String city,
			@PathVariable String state,
			@PathVariable String  pincode,
			@PathVariable String username,
			@PathVariable String password) {
		dao.citizen_register(firstname,lastname,mobile,address,city,state,pincode,username,password);
		return "Citizen Saved Sucessfully";
	}
	
	
	@GetMapping("/get_citizen")
	public ResponseEntity<List<GetCitizenResponse>> get_material() {
		return ResponseEntity.ok().body(service.get_citizen());
	}
	
	@PostMapping("/add_complaint/{citizen_id}/{mobile}/{address}/{reason}")
	public String add_complaint(@PathVariable Integer citizen_id,
			@PathVariable String  mobile,
			@PathVariable String address,
			@PathVariable String reason
		) {
		dao.add_complaint(citizen_id,mobile,address,reason);
		return "Complaint Saved Sucessfully";
	}
	@PostMapping("/complaint_action/{complaint_id}/{status}")
	public String add_complaint(@PathVariable Integer complaint_id,@PathVariable String status 
		) {
		dao.complaint_action(complaint_id,status);
		return "Complaint Saved Updated";
	}
	
	@GetMapping("/get_complaints")
	public ResponseEntity<List<GetComplaintResponse>> get_complaints() {
		return ResponseEntity.ok().body(service.get_complaints());
	}
	@GetMapping("/get_complaints/{id}")
	public ResponseEntity<List<GetComplaintResponse>> get_complaints1(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.get_complaints(id));
	}
	
	
	
}
