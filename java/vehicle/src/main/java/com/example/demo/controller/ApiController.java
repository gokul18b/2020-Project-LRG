package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.BillDetailsResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.PendingServiceResponse;
import com.example.demo.response.SearchServiceResponse;
import com.example.demo.response.UserBillResponse;
import com.example.demo.response.ViewBillResponse;
import com.example.demo.response.ViewBranchResponse;
import com.example.demo.response.ViewService;
import com.example.demo.response.ViewUserResponse;
import com.example.demo.service.ApiService;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping(value = { "/api" })
public class ApiController {

	@Autowired
	ApiService service;

	@PostMapping("/login/{username}/{password}")
	public ResponseEntity<List<LoginResponse>>  login(@PathVariable String username,@PathVariable String password) {
		return ResponseEntity.ok().body(service.login(username,password));
	}
	
	@GetMapping("/viewBranch")
	public ResponseEntity<List<ViewBranchResponse>>  viewBranch() {
		return ResponseEntity.ok().body(service.viewBranch());
	}
	
	@PostMapping("/addBranch/{name}/{landmark}/{pincode}/{address}")
	public String  addBranch(@PathVariable String name,@PathVariable String landmark,@PathVariable String pincode,@PathVariable String address) {
		service.addBranch(name,landmark,pincode,address);
		return "Branch sucessfully Created";
	}
	
	@PostMapping("/deleteBranch/{id}")
	public String  deleteBranch(@PathVariable String id) {
		service.deleteBranch(id);
		return "Branch sucessfully Removed";
	}
	
	
	@PostMapping("/addUser/{name}/{lastname}/{mobile}/{email}/{age}/{gender}/{password}/{address}")
	public String  addUser(@PathVariable String name,
			@PathVariable String lastname,
			@PathVariable String mobile,
			@PathVariable String email,
			@PathVariable String age,
			@PathVariable String gender,
			@PathVariable String password,
			@PathVariable String address) {
		service.addUser(name,lastname,mobile,email,age,gender,password,address);
		return "User sucessfully Created";
	}
	
	
	@GetMapping("/viewUser")
	public ResponseEntity<List<ViewUserResponse>>  viewUser() {
		return ResponseEntity.ok().body(service.viewUser());
	}
	
	
	@PostMapping("/deleteUser/{id}")
	public String  addUser(@PathVariable Integer id) {
		service.deleteUser(id);
		return "User sucessfully Deleted";
	}
	
	@PostMapping("/searchService/{mobilenumber}")
	public ResponseEntity<List<SearchServiceResponse>>  searchService(@PathVariable String mobilenumber) {
		return ResponseEntity.ok().body(service.searchService(mobilenumber));
	}
	
	@PostMapping("/addService/{mobile}/{name}/{branch}/{vehicle}/{serno}/{problem}/{address}/{uid}")
	public String  addService(@PathVariable String mobile,
			@PathVariable String name,
			@PathVariable String branch,
			@PathVariable String vehicle,
			@PathVariable String serno,
			@PathVariable String problem,
			@PathVariable String address,
			@PathVariable Integer uid) {
		return service.addService(mobile,name,branch,vehicle,serno,problem,address,uid);
	}
	
	@PostMapping("/viewService/{branch}")
	public ResponseEntity<List<ViewService>>  viewService(@PathVariable Integer branch) {
		return ResponseEntity.ok().body(service.viewService(branch));
	}
	
	
	@PostMapping("/deleteService/{id}")
	public String  deleteService(@PathVariable Integer id) {
		service.deleteService(id);
		return "Service sucessfully Deleted";
	}
	
	@PostMapping("/billDetails/{id}")
	public ResponseEntity<List<BillDetailsResponse>>  billDetails(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.billDetails(id));
	}
	
	@PostMapping("/addBill/{sid}/{amount}/{branchid}")
	public String  addBill(@PathVariable String sid,@PathVariable String amount,@PathVariable String branchid) {
		service.addBill(sid,amount,branchid);
		return "Bill sucessfully Added";
	}
	
	@PostMapping("/viewBill/{branch}/{date}")
	public ResponseEntity<List<ViewBillResponse>>  viewBill(@PathVariable Integer branch,@PathVariable String  date) {
		return ResponseEntity.ok().body(service.viewBill(branch,date));
	}
	
	@PostMapping("/userdetails/{id}")
	public ResponseEntity<List<LoginResponse>>  userdetails(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.userdetails(id));
	}
	
	@PostMapping("/pendingService/{id}")
	public ResponseEntity<List<PendingServiceResponse>>  pendingService(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.pendingService(id));
	}
	
	@PostMapping("/deliveredService/{id}")
	public ResponseEntity<List<PendingServiceResponse>>  deliveredService(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.deliveredService(id));
	}
	@PostMapping("/userBill/{branch}/{id}")
	public ResponseEntity<List<UserBillResponse>>  userBill(@PathVariable Integer branch,@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.userBill(branch,id));
	}
	@PostMapping("/getProfile/{id}")
	public ResponseEntity<List<LoginResponse>>  getProfile(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.getProfile(id));
	}
	
	
}
