package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.GetProfileResponse;
import com.example.demo.response.ValidateVotingResponse;
import com.example.demo.response.ViewCandidatesResponse;
import com.example.demo.response.ViewVoterResponse;
import com.example.demo.response.VoteReportResponse;
import com.example.demo.service.ApiService;


@RestController
@RequestMapping(value = { "/api" })
public class ApiController {

	@Autowired
	ApiService service;

	
	@PostMapping("/getprofile/{id}")
	public ResponseEntity<List<GetProfileResponse>> getprofile(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.getprofile(id));
	}
	
	@PostMapping("/addVoter/{firstname}/{lastname}/{mobile}/{email}/{address}")
	public String addVoter(@PathVariable String firstname,@PathVariable String lastname,@PathVariable String mobile,@PathVariable String email,@PathVariable String address) {
		service.addVoter(firstname,lastname,mobile,email,address);
		return "Voter sucessfully Created";
	}
	
	@PostMapping("/deleteVoter/{id}")
	public String deleteVoter(@PathVariable Integer id) {
		service.deleteVoter(id);
		return "Voter sucessfully deleted";
	}
	
	@PostMapping("/viewVoter")
	public ResponseEntity<List<ViewVoterResponse>>  viewVoter() {
		return ResponseEntity.ok().body(service.viewVoter());
	}
	
	
	@PostMapping("/addCandidate/{firstname}/{lastname}/{mobile}/{email}/{address}/{post}")
	public String addCandidate(@PathVariable String firstname,@PathVariable String lastname,@PathVariable String mobile,@PathVariable String email,@PathVariable String address,@PathVariable String post) {
		service.addCandidate(firstname,lastname,mobile,email,address,post);
		return "Candidate sucessfully Created";
	}
	
	@PostMapping("/deleteCandidates/{id}")
	public String deleteCandidates(@PathVariable Integer id) {
		service.deleteCandidates(id);
		return "Candidate sucessfully deleted";
	}

	@PostMapping("/viewCandidates")
	public ResponseEntity<List<ViewCandidatesResponse>>  viewCandidates() {
		return ResponseEntity.ok().body(service.viewCandidates());
	}
	
	@PostMapping("/login/{username}/{password}")
	public ResponseEntity<List<GetProfileResponse>>  login(@PathVariable String username,@PathVariable String password) {
		return ResponseEntity.ok().body(service.login(username,password));
	}
	
	@PostMapping("/pullvote/{canid}/{votid}/{postid}")
	public String  pullvote(@PathVariable String canid,@PathVariable String votid,@PathVariable String postid) {
		service.pullvote(canid,votid,postid);
		return "Vote sucessfully saved";
	}
	
	@PostMapping("/validateVoting/{id}")
	public ResponseEntity<List<ValidateVotingResponse>>  validateVoting(@PathVariable String id) {
		return ResponseEntity.ok().body(service.validateVoting(id));
	}
	
	@GetMapping("/votreport")
	public ResponseEntity<List<VoteReportResponse>>  votreport() {
		return ResponseEntity.ok().body(service.votreport());
	}
	
	
	
}
