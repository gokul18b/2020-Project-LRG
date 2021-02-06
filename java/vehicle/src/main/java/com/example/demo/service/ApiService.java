package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.ApiDao;
import com.example.demo.response.BillDetailsResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.PendingServiceResponse;
import com.example.demo.response.SearchServiceResponse;
import com.example.demo.response.UserBillResponse;
import com.example.demo.response.ViewBillResponse;
import com.example.demo.response.ViewBranchResponse;
import com.example.demo.response.ViewService;
import com.example.demo.response.ViewUserResponse;

@Service
@Transactional
public class ApiService {

	@Autowired
	ApiDao dao;
	
	public List<LoginResponse> login(String username, String password) {
		List<Object[]> result = dao.login(username,password);
		List<LoginResponse> response = new ArrayList<LoginResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			LoginResponse obj = new LoginResponse();
			
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setMobile((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setAddress((String)row[4]);
			obj.setFLAG((Integer)row[5]);
			
			response.add(obj);
			
		}
		return response;
	}
	
	

	public List<ViewBranchResponse> viewBranch() {
		List<Object[]> result = dao.viewBranch();
		List<ViewBranchResponse> response = new ArrayList<ViewBranchResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			ViewBranchResponse obj = new ViewBranchResponse();
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setLandmark((String)row[2]);
			obj.setPincode((Integer)row[3]);
			obj.setAddress((String)row[4]);
			response.add(obj);
			
		}
		return response;
	}



	public void addBranch(String name, String landmark, String pincode, String address) {
		dao.addBranch(name,landmark,pincode,address);
		
	}



	public void deleteBranch(String id) {
		// TODO Auto-generated method stub
		dao.deleteBranch(id);
	}



	public void addUser(String name,
			 String lastname,
			 String mobile,
			 String email,
			 String age,
			 String gender,
			 String password,
			 String address) {
		// TODO Auto-generated method stub
		dao.addUser(name,lastname,mobile,email,age,gender,password,address);
		
	}


	public List<ViewUserResponse> viewUser() {
		List<Object[]> result = dao.viewUser();
		List<ViewUserResponse> response = new ArrayList<ViewUserResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			ViewUserResponse obj = new ViewUserResponse();
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setMobile((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setAddress((String)row[4]);
			obj.setFLAG((Integer)row[5]);
			response.add(obj);
			
		}
		return response;
	}



	public void deleteUser(Integer id) {
		dao.deleteUser(id);
		
	}



	public List<SearchServiceResponse> searchService(String mobilenumber) {
		// TODO Auto-generated method stub
		List<Object[]> result = dao.searchService(mobilenumber);
		List<SearchServiceResponse> response = new ArrayList<SearchServiceResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			SearchServiceResponse obj = new SearchServiceResponse();
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setMobile((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setAddress((String)row[4]);
			obj.setFLAG((Integer)row[5]);
			response.add(obj);
			
		}
		return response;
	}



	public String addService(String mobile, String name, String branch, String vehicle, String serno, String problem,
			String address, Integer uid) {
		dao.addService(mobile,name,branch,vehicle,serno,problem,address,uid);
		return null;
	}


	public List<ViewService> viewService(Integer branch) {
		// TODO Auto-generated method stub
		List<Object[]> result = dao.viewService(branch);
		List<ViewService> response = new ArrayList<ViewService>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			ViewService obj = new ViewService();
			obj.setId((Integer)row[0]);
			obj.setMobile((String)row[1]);
			obj.setName((String)row[2]);
			obj.setBranch((String)row[3]);
			obj.setVehicle((String)row[4]);
			obj.setSerno((String)row[5]);
			obj.setProblem((String)row[6]);
			obj.setAddress((String)row[7]);
			obj.setStatus((Integer)row[8]);
			obj.setUid((Integer)row[9]);
			obj.setDate((String)row[10]);
			response.add(obj);
			
		}
		return response;
	}



	public void deleteService(Integer id) {
		dao.deleteService(id);
		
	}


	public List<BillDetailsResponse> billDetails(Integer id) {
		// TODO Auto-generated method stub
		List<Object[]> result = dao.billDetails(id);
		List<BillDetailsResponse> response = new ArrayList<BillDetailsResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			BillDetailsResponse obj = new BillDetailsResponse();
			obj.setBranchid((Integer)row[0]);
			obj.setBranch((String)row[1]);
			obj.setMobile((String)row[2]);
			obj.setName((String)row[3]);
			obj.setVehicle((String)row[4]);
			obj.setCarno((String)row[5]);
			obj.setProblem((String)row[6]);
			obj.setAddress((String)row[7]);
			
			response.add(obj);
			
		}
		return response;
	}



	public void addBill(String sid, String amount, String branchid) {
		
		dao.addBill(sid,amount,branchid);
	}


	public List<ViewBillResponse> viewBill(Integer branch, String date)  {
		// TODO Auto-generated method stub
		List<Object[]> result = dao.viewBill(branch,date);
		List<ViewBillResponse> response = new ArrayList<ViewBillResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			ViewBillResponse obj = new ViewBillResponse();
			obj.setName((String)row[0]);
			obj.setAmount((Integer)row[1]);
			obj.setMobile((String)row[2]);
			
			
			response.add(obj);
			
		}
		return response;
	}



	public List<LoginResponse> userdetails(Integer id) {
		List<Object[]> result = dao.userdetails(id);
		List<LoginResponse> response = new ArrayList<LoginResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			LoginResponse obj = new LoginResponse();
			
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setMobile((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setAddress((String)row[4]);
			obj.setFLAG((Integer)row[5]);
			
			response.add(obj);
			
		}
		return response;
	}




	public List<PendingServiceResponse> pendingService(Integer id) {
		List<Object[]> result = dao.pendingService(id);
		List<PendingServiceResponse> response = new ArrayList<PendingServiceResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			PendingServiceResponse obj = new PendingServiceResponse();
			
			obj.setId((Integer)row[0]);
			obj.setDate((String)row[1]);
			obj.setBranchname((String)row[2]);
			obj.setSerno((String)row[3]);
			obj.setProblem((String)row[4]);
			
			
			response.add(obj);
			
		}
		return response;
	}
	
	public List<PendingServiceResponse> deliveredService(Integer id) {
		List<Object[]> result = dao.deliveredService(id);
		List<PendingServiceResponse> response = new ArrayList<PendingServiceResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			PendingServiceResponse obj = new PendingServiceResponse();
			
			obj.setId((Integer)row[0]);
			obj.setDate((String)row[1]);
			obj.setBranchname((String)row[2]);
			obj.setSerno((String)row[3]);
			obj.setProblem((String)row[4]);
			
			
			response.add(obj);
			
		}
		return response;
	}



	
	public List<UserBillResponse> userBill(Integer branch, Integer id) {
		List<Object[]> result = dao.userBill(branch,id);
		List<UserBillResponse> response = new ArrayList<UserBillResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			UserBillResponse obj = new UserBillResponse();
			
			obj.setBranchid((String)row[0]);
			obj.setBranch((String)row[1]);
			obj.setDate((String)row[2]);
			obj.setProblem((String)row[3]);
			obj.setSerno((String)row[4]);
			obj.setAmount((Integer)row[5]);
			
			
			response.add(obj);
			
		}
		return response;
	}


	public List<LoginResponse> getProfile(Integer id) {
		List<Object[]> result = dao.getProfile(id);
		List<LoginResponse> response = new ArrayList<LoginResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			LoginResponse obj = new LoginResponse();
			
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setMobile((String)row[2]);
			obj.setPassword((String)row[3]);
			obj.setAddress((String)row[4]);
			obj.setFLAG((Integer)row[5]);
			
			response.add(obj);
			
		}
		return response;
	}
	
}
