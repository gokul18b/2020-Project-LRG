package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.GetHealthResponse;
import com.example.demo.response.GetMaterialResponse;
import com.example.demo.response.GetMembersResponse;
import com.example.demo.response.GetPunchDetailsResponse;
import com.example.demo.response.GetServiceResponse;
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
	
	
	@PostMapping("/member_register/{firstname}/{lastname}/{mobile}/{email}/{age}/{gender}/{height}/{weight}/{address}")
	public String member_register(@PathVariable String firstname,
			@PathVariable String lastname,
			@PathVariable String  mobile,
			@PathVariable String email,
			@PathVariable Integer age,
			@PathVariable String gender,
			@PathVariable String  height,
			@PathVariable String weight,
			@PathVariable String address) {
		service.member_register(firstname,lastname,mobile,email,age,gender,height,weight,address);
		return "Member Saved Sucessfully";
	}
	
	@PostMapping("/add_material/{material_name}/{quantity}/{amount}")
	public String add_material(@PathVariable String material_name,
			@PathVariable Integer quantity,
			@PathVariable Integer  amount) {
		service.add_material(material_name,quantity,amount);
		return "Material Saved Sucessfully";
	}
	@GetMapping("/get_material")
	public ResponseEntity<List<GetMaterialResponse>> get_material() {
		
		return ResponseEntity.ok().body(service.get_material());
	}
	
	@PostMapping("/add_service/{material_name}/{servicename}/{amount}/{issue}")
	public String add_service(@PathVariable String material_name,@PathVariable String servicename,
			@PathVariable Integer amount,
			@PathVariable String  issue) {
		service.add_service(material_name,servicename,amount,issue);
		return "Service Saved Sucessfully";
	}
	
	@GetMapping("/get_service")
	public ResponseEntity<List<GetServiceResponse>> get_service() {
		
		return ResponseEntity.ok().body(service.get_service());
	}
	@PostMapping("/add_health/{title}/{description}")
	public String add_health(@PathVariable String title,
			@PathVariable String description) {
		service.add_health(title,description);
		return "Health Saved Sucessfully";
	}
	
	@GetMapping("/get_health")
	public ResponseEntity<List<GetHealthResponse>> get_health() {
		
		return ResponseEntity.ok().body(service.get_health());
	}
	
	@GetMapping("/get_members")
	public ResponseEntity<List<GetMembersResponse>> get_members() {
		
		return ResponseEntity.ok().body(service.get_members());
	}
	
	@PostMapping("/punch_in/{id}")
	public String punch_in(@PathVariable Integer id) {
		return service.punch_in(id);
	}
	@PostMapping("/punch_out/{id}")
	public String punch_out(@PathVariable Integer id) {
		return service.punch_out(id);
	}
	
	@GetMapping("/get_punch_details/{mobile}")
	public ResponseEntity<List<GetPunchDetailsResponse>> get_punch_details(@PathVariable String mobile) {
		
		return ResponseEntity.ok().body(service.get_punch_details(mobile));
	}
	
}
