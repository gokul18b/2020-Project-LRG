package com.example.demo.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ApiDao;
import com.example.demo.response.GetProfileResponse;
import com.example.demo.response.ValidateVotingResponse;
import com.example.demo.response.ViewCandidatesResponse;
import com.example.demo.response.ViewVoterResponse;
import com.example.demo.response.VoteReportResponse;

@Service
@Transactional
public class ApiService {

	@Autowired
	ApiDao dao;
	
	public List<GetProfileResponse> getprofile(Integer id) {
		List<Object[]> result = dao.getprofile(id);
		List<GetProfileResponse> response = new ArrayList<GetProfileResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetProfileResponse obj = new GetProfileResponse();
			obj.setUserid((Integer)row[0]);
			obj.setUsertype((Integer)row[1]);
			obj.setEmail((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setFirstname((String)row[4]);
			obj.setLastname((String)row[5]);
			obj.setMobilenumber((String)row[6]);
			obj.setAddress((String)row[7]);
			
			response.add(obj);
			
		}
		return response;
	}

	public void addVoter(String firstname, String lastname, String mobile, String email, String address) {
		dao.addVoter(firstname,lastname,mobile,email,address);
		
	}

	public void deleteVoter(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteVoter(id);
	}

	public List<ViewVoterResponse> viewVoter() {
		List<Object[]> result = dao.viewVoter();
		
		List<ViewVoterResponse> response = new ArrayList<ViewVoterResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			ViewVoterResponse obj = new ViewVoterResponse();
			obj.setUserid((Integer)row[0]);
			obj.setUsertype((Integer)row[1]);
			obj.setEmail((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setFirstname((String)row[4]);
			obj.setLastname((String)row[5]);
			obj.setMobilenumber((String)row[6]);
			obj.setAddress((String)row[7]);
			
			response.add(obj);
			
		}
		return response;
	}

	public void addCandidate(String firstname, String lastname, String mobile, String email, String address,
			String post) {
		dao.addCandidate(firstname,lastname,mobile,email,address,post);
		
	}

	public void deleteCandidates(Integer id) {
		dao.deleteCandidates(id);
		
	}


	public List<ViewCandidatesResponse> viewCandidates() {
		List<Object[]> result = dao.viewCandidates();
		
		List<ViewCandidatesResponse> response = new ArrayList<ViewCandidatesResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			ViewCandidatesResponse obj = new ViewCandidatesResponse();
			
			obj.setUserid((Integer)row[0]);
			obj.setPost((Integer)row[1]);
			obj.setFirstname((String)row[2]);
			obj.setLastname((String)row[3]);
			obj.setMobile((String)row[4]);
			obj.setEmail((String)row[6]);
			obj.setAddress((String)row[5]);
			
			response.add(obj);
			
		}
		return response;
	}

	public List<GetProfileResponse> login(String username, String password) {
		List<Object[]> result = dao.login(username,password);
		List<GetProfileResponse> response = new ArrayList<GetProfileResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetProfileResponse obj = new GetProfileResponse();
			obj.setUserid((Integer)row[0]);
			obj.setUsertype((Integer)row[1]);
			obj.setEmail((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setFirstname((String)row[4]);
			obj.setLastname((String)row[5]);
			obj.setMobilenumber((String)row[6]);
			obj.setAddress((String)row[7]);
			
			response.add(obj);
			
		}
		return response;
	}

	public void pullvote(String canid, String votid, String postid) {
		dao.pullvote(canid,votid,postid);
	}

	public List<ValidateVotingResponse>  validateVoting(String id) {
		List<Object[]> result = dao.validateVoting(id);
		List<ValidateVotingResponse> response = new ArrayList<ValidateVotingResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			ValidateVotingResponse obj = new ValidateVotingResponse();
			obj.setId((Integer)row[0]);
			obj.setCanid((Integer)row[1]);
			obj.setVotid((Integer)row[2]);
			obj.setPostid((Integer)row[3]);	
			
			response.add(obj);
			
		}
		return response;
	}

	
	public List<VoteReportResponse>  votreport() {
		List<Object[]> result = dao.votreport();
		List<VoteReportResponse> response = new ArrayList<VoteReportResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			VoteReportResponse obj = new VoteReportResponse();
			obj.setUserid((Integer)row[0]);
			obj.setFirstname((String)row[1]);
			obj.setCount((BigInteger)row[2]);
			obj.setPostid((Integer)row[3]);	
			
			response.add(obj);
			
		}
		return response;
	}

}
