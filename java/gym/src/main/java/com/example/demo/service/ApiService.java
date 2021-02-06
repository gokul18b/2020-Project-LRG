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
import com.example.demo.response.GetHealthResponse;
import com.example.demo.response.GetMaterialResponse;
import com.example.demo.response.GetMembersResponse;
import com.example.demo.response.GetPunchDetailsResponse;
import com.example.demo.response.GetServiceResponse;

@Service
@Transactional
public class ApiService {

	@Autowired
	ApiDao dao;

	


	public void member_register(String firstname, String lastname, String mobile, String email, Integer age,
			String gender, String height, String weight, String address) {
		// TODO Auto-generated method stub
		dao.member_register(firstname,lastname,mobile,email,age,gender,height,weight,address);
	}



	public void add_material(String material_name, Integer quantity, Integer amount) {
		// TODO Auto-generated method stub
		dao.add_material(material_name,quantity,amount);
	}



	public List<GetMaterialResponse> get_material() {
		List<Object[]> result =dao.get_material();
		
		List<GetMaterialResponse> response = new ArrayList<GetMaterialResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetMaterialResponse obj = new GetMaterialResponse();
			
			obj.setMaterial_name((String)row[1]);
			obj.setQuantity((Integer)row[2]);
			obj.setAmount((Integer)row[3]);
			
			
			response.add(obj);
			
		}
		return response;
	}



	public void add_service(String material_name, String servicename, Integer amount, String issue) {
		// TODO Auto-generated method stub
		dao.add_service(material_name,servicename,amount,issue);
	}



	public List<GetServiceResponse> get_service() {
		// TODO Auto-generated method stub
List<Object[]> result =dao.get_service();
		
		List<GetServiceResponse> response = new ArrayList<GetServiceResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetServiceResponse obj = new GetServiceResponse();
			
			obj.setMaterial_name((String)row[1]);
			obj.setProvider((String)row[2]);
			obj.setAmount((Integer)row[3]);
			obj.setIssue((String)row[4]);
			
			response.add(obj);
			
		}
		return response;
	}



	public void add_health(String title, String description) {
		// TODO Auto-generated method stub
		dao.add_health(title,description);
	}



	public List<GetHealthResponse> get_health() {
		// TODO Auto-generated method stub
		List<Object[]> result =dao.get_health();
		
		List<GetHealthResponse> response = new ArrayList<GetHealthResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetHealthResponse obj = new GetHealthResponse();
			
			obj.setTitle((String)row[1]);
			obj.setDescription((String)row[2]);
			obj.setDate((Timestamp)row[3]);
			
			
			response.add(obj);
			
		}
		return response;
	}



	public List<GetMembersResponse> get_members() {
		// TODO Auto-generated method stub
		List<Object[]> result =dao.get_members();
		
		List<GetMembersResponse> response = new ArrayList<GetMembersResponse>();
		for(int i=0;i<result.size();i++) {
			Object[] row = result.get(i);
			GetMembersResponse obj = new GetMembersResponse();
			
			obj.setId((Integer)row[0]);
			obj.setName((String)row[1]);
			obj.setLastname((String)row[2]);
			obj.setMobile((String)row[3]);
			obj.setEmail((String)row[4]);
			obj.setAge((Integer)row[5]);
			obj.setGender((String)row[6]);
			obj.setHeight((String)row[7]);
			obj.setWeight((String)row[8]);
			obj.setAddress((String)row[9]);
			
			
			
			response.add(obj);
			
		}
		return response;
	}



	public String punch_in(Integer id) {
		// TODO Auto-generated method stub
		return dao.punch_in(id);
	}



	public String punch_out(Integer id) {
		// TODO Auto-generated method stub
		return dao.punch_out(id);
	}



	public List<GetPunchDetailsResponse> get_punch_details(String mobile) {
		// TODO Auto-generated method stub
				List<Object[]> result =dao.get_punch_details(mobile);
				
				List<GetPunchDetailsResponse> response = new ArrayList<GetPunchDetailsResponse>();
				for(int i=0;i<result.size();i++) {
					Object[] row = result.get(i);
					GetPunchDetailsResponse obj = new GetPunchDetailsResponse();
					
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//					Date dateobj = new Date((String)row[0]);
//					System.out.println(df.format(dateobj));
					obj.setIn((Timestamp)row[0]);
					obj.setOut((Timestamp)row[1]);
					obj.setDate((Timestamp)row[2]);
					
					Timestamp a = (Timestamp)row[0];
					Timestamp b = (Timestamp)row[1];
					
					//SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					//Date date1 = format.parse(a);
					//Date date2 = format.parse(time2);
					long difference = b.getTime() - a.getTime(); 
					
					
					
					long diff = b.getTime() - a.getTime();

					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);

					System.out.print(diffDays + " days, ");
					System.out.print(diffHours + " hours, ");
					System.out.print(diffMinutes + " minutes, ");
					System.out.print(diffSeconds + " seconds.");
					
					obj.setTotal(diffHours+"H:"+diffMinutes+"M:"+diffSeconds+"S");
					response.add(obj);
					
				}
				return response;
	}



	public Boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.login(username,password);
	}

	
	
}
