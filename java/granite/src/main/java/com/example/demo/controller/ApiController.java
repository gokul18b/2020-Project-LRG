package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.GetBillingResponse;
import com.example.demo.response.GetCustomerResponse;
import com.example.demo.response.GetEmployeeResponse;
import com.example.demo.response.GetProductResponse;
import com.example.demo.response.GetStockResponse;
import com.example.demo.service.ApiService;


@RestController
@RequestMapping(value = { "/api" })
public class ApiController {

	@Autowired
	ApiService service;

	@GetMapping("/login/{username}/{password}")
	public Boolean login(@PathVariable String username,@PathVariable String password) {
		return service.login(username,password);
	}
	
	
	@PostMapping("/add_employee/{name}/{mobile}/{address}/{gender}/{salary}/{age}")
	public String add_employee(@PathVariable String name,
			@PathVariable String mobile,
			@PathVariable String  address,
			@PathVariable String gender,
			@PathVariable Integer salary,
			@PathVariable Integer age) {
		service.add_employee(name,mobile,address,gender,salary,age);
		return "Employee Saved Sucessfully";
	}
	
	@GetMapping("/get_employee")
	public ResponseEntity<List<GetEmployeeResponse>> get_employee() {
		return ResponseEntity.ok().body(service.get_employee());
	}
	
	@PostMapping("/add_customer/{name}/{mobile}/{address}/{gender}/{email}")
	public String add_customer(@PathVariable String name,
			@PathVariable String mobile,
			@PathVariable String  address,
			@PathVariable String gender,
			@PathVariable String email) {
		service.add_customer(name,mobile,address,gender,email);
		return "Customer Saved Sucessfully";
	}
	
	@GetMapping("/get_customer")
	public ResponseEntity<List<GetCustomerResponse>> get_customer() {
		return ResponseEntity.ok().body(service.get_customer());
	}
	
	@PostMapping("/add_product/{company}/{product}/{weight}/{price}")
	public String add_product(@PathVariable String company,
			@PathVariable String product,
			@PathVariable String weight,
			@PathVariable Integer  price) {
		service.add_product(company,product,weight,price);
		return "Product Saved Sucessfully";
	}
	
	@GetMapping("/get_product")
	public ResponseEntity<List<GetProductResponse>> get_product() {
		return ResponseEntity.ok().body(service.get_product());
	}
	
	@PostMapping("/add_purchase/{product_id}/{quantity}/{price}")
	public String add_purchase(@PathVariable Integer product_id,
			@PathVariable Integer  quantity,@PathVariable Integer  price) {
		service.add_purchase(product_id,quantity,price);
		return "Purchase Saved Sucessfully";
	}
	
	@PostMapping("/add_sales/{customer_id}/{product_id}/{quantity}/{price}")
	public String add_sales(@PathVariable Integer customer_id,@PathVariable Integer product_id,
			@PathVariable Integer  quantity
			,@PathVariable Integer  price) {
		service.add_sales(customer_id,product_id,quantity,price);
		return "Sales Saved Sucessfully";
	}
	
	@GetMapping("/get_customer/{mobile}")
	public ResponseEntity<GetCustomerResponse> get_mobile(@PathVariable String mobile) {
		return ResponseEntity.ok().body(service.get_mobile(mobile));
	}
	
	@GetMapping("/get_stock")
	public ResponseEntity<List<GetStockResponse>> get_stock() {
		return ResponseEntity.ok().body(service.get_stock());
	}
	@GetMapping("/get_billing")
	public ResponseEntity<List<GetBillingResponse>> get_billing() {
		return ResponseEntity.ok().body(service.get_billing());
	}
	
}
