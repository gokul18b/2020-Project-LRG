package com.example.demo.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ApiDao;
import com.example.demo.request.AddCourierRequest;
import com.example.demo.request.AddCustomerRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SearchRequest;
import com.example.demo.request.UpdateCourierRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.SearchResponse;
import com.example.demo.response.ViewCustomerResponse;
import com.example.demo.response.viewBillResponse;

@Service
@Transactional
public class ApiService {

	@Autowired
	ApiDao dao;
	
	public List<ViewCustomerResponse> viewCustomer() {
		List<Object[]> result = dao.viewCustomer();
		List<ViewCustomerResponse> response = new ArrayList<ViewCustomerResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] obj = result.get(i);
			ViewCustomerResponse row = new ViewCustomerResponse();
			row.setFirstname((String)obj[0]);
			row.setLastname((String)obj[1]);
			row.setMobile((String)obj[2]);
			row.setEmail((String)obj[3]);
			row.setAge((Integer)obj[4]);
			row.setGender((String)obj[5]);
			row.setAddress((String)obj[6]);
			response.add(row);
		}
		
		return response;
	}

	public List<LoginResponse> login(LoginRequest body) {
		List<BigInteger> result = dao.login(body);
		List<LoginResponse> response = new ArrayList<LoginResponse>();
		for(int i=0;i<result.size();i++) {
			 BigInteger obj = result.get(0);
			LoginResponse row = new LoginResponse();
			row.setCount((BigInteger)obj);
			response.add(row);
		}
		return response;
	}

	public String  addCustomer(AddCustomerRequest body) {
		dao.addCustomer(body);
		return "Customer Sucessfully Created";
	}

	public String addCourier(AddCourierRequest body) {
		// TODO Auto-generated method stub
		dao.addCourier(body);
		return "Courier Sucessfully Created";
	}

	public String updateCourier(UpdateCourierRequest body) {
		// TODO Auto-generated method stub
		dao.updateCourier(body);
		return "Courier Sucessfully Updated";
	}

	public List<SearchResponse> search(SearchRequest body) {
		List<Object[]> result = dao.search(body);
		List<SearchResponse> response = new ArrayList<SearchResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] obj = result.get(i);
			SearchResponse row = new SearchResponse();
			row.setId((Integer) obj[0]);
			row.setMobile((String)obj[1]);
			row.setName((String)obj[2]);
			row.setSendlocation((String)obj[3]);
			row.setCurrentlocation((String)obj[4]);
			row.setAddress((String)obj[5]);
			response.add(row);
		}
		
		return response;
		
	}

	public List<SearchResponse> viewCourier() {
		// TODO Auto-generated method stub
		List<Object[]> result = dao.viewCourier();
		List<SearchResponse> response = new ArrayList<SearchResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] obj = result.get(i);
			SearchResponse row = new SearchResponse();
			row.setId((Integer) obj[0]);
			row.setMobile((String)obj[1]);
			row.setName((String)obj[2]);
			row.setSendlocation((String)obj[3]);
			row.setCurrentlocation((String)obj[4]);
			row.setAddress((String)obj[5]);
			response.add(row);
		}
		
		return response;
	}

	public List<viewBillResponse> viewBill() {
		List<Object[]> result = dao.viewBill();
		List<viewBillResponse> response = new ArrayList<viewBillResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] obj = result.get(i);
			viewBillResponse row = new viewBillResponse();
			row.setName((String) obj[0]);
			row.setAmount((Integer)obj[1]);
			row.setDate((Timestamp)obj[2]);
			row.setDiscount((String)obj[3]);
			row.setWeight((String)obj[4]);
			
			
			response.add(row);
		}
		
		return response;
	}

}
