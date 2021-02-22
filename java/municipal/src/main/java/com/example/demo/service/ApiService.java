package com.example.demo.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ApiDao;

import com.example.demo.response.GetCitizenResponse;
import com.example.demo.response.GetComplaintResponse;
@Service
@Transactional
public class ApiService {

	@Autowired
	ApiDao dao;


	public List<GetCitizenResponse> get_citizen() {
		// TODO Auto-generated method stub
		List<Object[]> result =dao.get_citizen();
		
		List<GetCitizenResponse> response = new ArrayList<GetCitizenResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetCitizenResponse obj = new GetCitizenResponse();
			
			obj.setId((Integer)row[0]);
			obj.setFirstname((String)row[1]);
			obj.setLastname((String)row[2]);
			obj.setMobile((String)row[3]);
			obj.setAddress((String)row[4]);
			obj.setCity((String)row[5]);
			obj.setState((String)row[6]);
			obj.setPincode((String)row[7]);
			obj.setUsername((String)row[8]);
			obj.setPassword((String)row[9]);	
			
			response.add(obj);			
		}
		return response;
	}
	public List<GetComplaintResponse> get_complaints() {
		// TODO Auto-generated method stub
		List<Object[]> result =dao.get_complaints();
		
		List<GetComplaintResponse> response = new ArrayList<GetComplaintResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetComplaintResponse obj = new GetComplaintResponse();
			
			obj.setId((Integer)row[0]);
			obj.setCitizen_id((Integer)row[1]);
			obj.setMobile((String)row[2]);
			obj.setAddress((String)row[3]);
			obj.setReason((String)row[4]);
			obj.setStatus((String)row[5]);
				
			
			response.add(obj);			
		}
		return response;
	}
	public List<GetComplaintResponse> get_complaints(Integer id) {
		// TODO Auto-generated method stub
		List<Object[]> result =dao.get_complaints(id);
		
		List<GetComplaintResponse> response = new ArrayList<GetComplaintResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetComplaintResponse obj = new GetComplaintResponse();
			
			obj.setId((Integer)row[0]);
			obj.setCitizen_id((Integer)row[1]);
			obj.setMobile((String)row[2]);
			obj.setAddress((String)row[3]);
			obj.setReason((String)row[4]);
			obj.setStatus((String)row[5]);
				
			
			response.add(obj);			
		}
		return response;
	}


	public Boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.login(username,password);
	}

	
	
}
