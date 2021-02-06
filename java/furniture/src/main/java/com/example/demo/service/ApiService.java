package com.example.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ApiDao;
import com.example.demo.response.GetBillingResponse;
import com.example.demo.response.GetCustomerResponse;
import com.example.demo.response.GetProductResponse;
import com.example.demo.response.GetStockResponse;

@Service
@Transactional
public class ApiService {

	@Autowired
	ApiDao dao;

	

	public void add_customer(String name, String mobile,String alternate, String address, String gender ) {
		// TODO Auto-generated method stub
		dao.add_customer(name,mobile,alternate,address,gender);
		
	}

	

	public List<GetCustomerResponse> get_customer() {
		// TODO Auto-generated method stub
		List<Object[]> result =dao.get_customer();
		
		List<GetCustomerResponse> response = new ArrayList<GetCustomerResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetCustomerResponse obj = new GetCustomerResponse();
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setMobile((String)row[2]);
			obj.setAddress((String)row[4]);
			obj.setGender((String)row[5]);
			obj.setAlternate((String)row[3]);
			
			
			response.add(obj);
			
		}
		return response;
		
	}

	public void add_product(String company, String model, Integer price) {
		// TODO Auto-generated method stub
		dao.add_product(company,model,price);
	}

	public List<GetProductResponse> get_product() {
		// TODO Auto-generated method stub
		
		List<Object[]> result =dao.get_product();
		
		List<GetProductResponse> response = new ArrayList<GetProductResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetProductResponse obj = new GetProductResponse();
			obj.setId((Integer)row[0]);
			obj.setCompany((String)row[1]);
			obj.setModel((String)row[2]);
			obj.setPrice((Integer)row[3]);
			
			
			
			response.add(obj);
			
		}
		return response;
	}

	public void add_purchase(Integer product_id, Integer quantity, String details) {
		// TODO Auto-generated method stub
		dao.add_purchase(product_id,quantity,details);
	}

	public void add_sales(Integer customer_id, Integer product_id, Integer quantity) {
		// TODO Auto-generated method stub
		dao.add_sales(customer_id,product_id,quantity);
	}

	public List<GetStockResponse> get_stock() {
		List<Object[]> result =dao.get_stock();
		
		List<GetStockResponse> response = new ArrayList<GetStockResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetStockResponse obj = new GetStockResponse();
			obj.setCompany_name((String)row[0]);
			obj.setQuantity((BigDecimal)row[2]);
			obj.setDetails((String)row[1]);
			
			response.add(obj);
			
		}
		return response;
	}

	public Integer get_mobile(String mobile) {
		// TODO Auto-generated method stub
		return dao.get_mobile(mobile);
	}

	

	public List<GetBillingResponse> get_billing() {
		List<Object[]> result =dao.get_billing();
		
		List<GetBillingResponse> response = new ArrayList<GetBillingResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetBillingResponse obj = new GetBillingResponse();
			obj.setCustomer_name((String)row[0]);
			obj.setMobile((String)row[1]);
			obj.setCompany((String)row[2]);
			obj.setModel((String)row[3]);
			obj.setQuantity((Integer)row[4]);
			obj.setPrice((Integer)row[5]);
			
			response.add(obj);
			
		}
		return response;
	}

	public Boolean login(String username, String password) {
		
		return dao.login(username,password);
	}
	
}
