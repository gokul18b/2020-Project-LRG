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
import com.example.demo.response.GetBankResponse;
import com.example.demo.response.GetCitizenResponse;
import com.example.demo.response.GetComplaintResponse;
import com.example.demo.response.GetCustomerBank;
@Service
@Transactional
public class ApiService {

	@Autowired
	ApiDao dao;

	public List<GetBankResponse> get_bank() {
		// TODO Auto-generated method stub
		List<Object[]> result =dao.get_bank();
		
		List<GetBankResponse> response = new ArrayList<GetBankResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetBankResponse obj = new GetBankResponse();
			
			obj.setId((Integer)row[0]);
			obj.setBankname((String)row[1]);
			obj.setAddress((String)row[2]);
			obj.setIfsccode((String)row[3]);
			obj.setLandline((String)row[4]);
			
			response.add(obj);			
		}
		return response;
	}
	
	public Boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.login(username,password);
	}

	public List<GetCustomerBank> get_customer_bank(Integer customer_id) {
		// TODO Auto-generated method stub
				List<Object[]> result =dao.get_customer_bank();
				
				List<GetBankResponse> response = new ArrayList<GetBankResponse>();
				for(int i=0;i<result.size();i++) {
					Object[] row = result.get(i);
					GetBankResponse obj = new GetBankResponse();
					
					obj.setId((Integer)row[0]);
					obj.setBankname((String)row[1]);
					obj.setAddress((String)row[2]);
					obj.setIfsccode((String)row[3]);
					obj.setLandline((String)row[4]);
					
					response.add(obj);			
				}
				return response;
	}

	
	
}
