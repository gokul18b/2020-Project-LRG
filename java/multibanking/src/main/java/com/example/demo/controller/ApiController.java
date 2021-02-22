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
import com.example.demo.response.GetBankResponse;
import com.example.demo.response.GetCitizenResponse;
import com.example.demo.response.GetComplaintResponse;
import com.example.demo.response.GetCustomerBank;
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
	@GetMapping("/manager_login/{username}/{password}")
	public Boolean manager_login(@PathVariable String username,@PathVariable String password) {
		return dao.manager_login(username,password);
	}
	
	
	
	@PostMapping("/add_bank/{bankname}/{address}/{ifsccode}/{landline}")
	public String bank_register(@PathVariable String bankname,
			@PathVariable String address,
			@PathVariable String  ifsccode,
			@PathVariable String landline) {
		dao.bank_register(bankname,address,ifsccode,landline);
		return "Bank Saved Sucessfully";
	}
	
	@PostMapping("/add_manager/{bankid}/{mobile}/{landline}/{username}/{password}")
	public String add_manager(@PathVariable Integer bankid,
			@PathVariable String mobile,
			@PathVariable String  landline,
			@PathVariable String username,
			@PathVariable String password) {
		dao.add_manager(bankid,mobile,landline,username,password);
		return "Manager Added Sucessfully";
	}
	
	@GetMapping("/get_bank")
	public ResponseEntity<List<GetBankResponse>> get_bank() {
		return ResponseEntity.ok().body(service.get_bank());
	}
	
	@PostMapping("/add_customer/{fname}/{lname}/{mobile}/{aadhar}/{idproof}/{age}/{gender}/{address1}/{address2}/{city}/{state}/{pincode}")
	public String add_manager(@PathVariable String  fname,
			@PathVariable String lname,
			@PathVariable String  mobile,
			@PathVariable String aadhar,
			@PathVariable String idproof,
			@PathVariable String age,
			@PathVariable String  gender,
			@PathVariable String address1,
			@PathVariable String address2,
			@PathVariable String city,
			@PathVariable String  state,
			@PathVariable String pincode) {
		dao.add_customer(fname,lname,mobile,aadhar,idproof,age,gender,address1,address2,city,state,pincode);
		return "Customer Added Sucessfully";
	}
	
	@PostMapping("/add_bank_account/{customer_id}/{bank_id}/{account type}")
	public String add_bank_account(@PathVariable Integer  customer_id,
			@PathVariable String bank_id,
			@PathVariable String  account_type) {
		dao.add_customer(customer_id,bank_id,account_type);
		return "Bank Request send Sucessfully";
	}

	
	@GetMapping("/get_customer_bank/{customer_id}")
	public ResponseEntity<List<GetCustomerBank>> get_customer_bank(@PathVariable Integer customer_id) {
		return ResponseEntity.ok().body(service.get_customer_bank(customer_id));
	}
	
	
}
