package com.example.demo.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.request.AddCourierRequest;
import com.example.demo.request.AddCustomerRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SearchRequest;
import com.example.demo.request.UpdateCourierRequest;

@Repository
public class ApiDao {
	@Autowired
	SessionFactory sf;

	
	public List<Object[]> login(String mobile, String password) {
		Session session = sf.getCurrentSession();
		String sql = "select id,name,mobile,password,address,FLAG from user where mobile='"+mobile+"' and password='"+password+"'";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}
	public List<Object[]> viewBranch() {
		Session session = sf.getCurrentSession();
		String sql = "select id,name,landmark,pincode,address from branch order by id desc";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public void addBranch(String name, String landmark, String pincode, String address) {
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `vehicleservice`.`branch` (`id`, `name`, `landmark`, `pincode`, `address`) VALUES (NULL, '"+name+"', '"+landmark+"', '"+pincode+"', '"+address+"');";
		NativeQuery a = session.createSQLQuery(sql);
		a.executeUpdate();	
		
	}

	public void deleteBranch(String id) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "delete from branch where id="+id;
		NativeQuery a = session.createSQLQuery(sql);
		a.executeUpdate();	
	}
	public void addUser(String name,
			 String lastname,
			 String mobile,
			 String email,
			 String age,
			 String gender,
			 String password,
			 String address) {
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `user` (`id`, `name`, `lastname`, `mobile`, `email`, `age`, `gender`, `password`, `address`, `FLAG`) VALUES (NULL, '"+name+"', '"+lastname+"', '"+mobile+"', '"+email+"', '"+age+"', '"+gender+"', '"+password+"', '"+address+"', 0);";
		NativeQuery a = session.createSQLQuery(sql);
		a.executeUpdate();	
		
	}
	public List<Object[]> viewUser() {
		Session session = sf.getCurrentSession();
		String sql = "select id,name,mobile,password,address,FLAG from user where FLAG!=1 order by id desc";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "delete from user where id="+id;
		NativeQuery a = session.createSQLQuery(sql);
		a.executeUpdate();	
	}

	public List<Object[]> searchService(String mobilenumber) {
		Session session = sf.getCurrentSession();
		String sql = "select id,name,mobile,password,address,FLAG from user where mobile='"+mobilenumber+"' order by id desc";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public void addService(String mobile, String name, String branch, String vehicle, String serno, String problem,
			String address, Integer uid) {
		Session session = sf.getCurrentSession();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		formatter.format(date);
		
		
		String sql = "INSERT INTO `vehicleservice`.`service` (`id`,`uid`, `mobile`, `name`, `branch`, `vehicle`, `serno`, `problem`, `address`,`date`,`status`) VALUES (NULL,'"+uid+"', '"+mobile+"', '"+name+"', '"+branch+"', '"+vehicle+"', '"+serno+"', '"+problem+"', '"+address+"', '"+formatter.format(date)+"',0);";
		NativeQuery a = session.createSQLQuery(sql);
		a.executeUpdate();	
		
	}
	
	
	public String  format(String n){
		if(Integer.valueOf(n)<10){
			return "0"+n;
		}else{
			return n;
		}
	}

	public List<Object[]> viewService(Integer branch) {
		Session session = sf.getCurrentSession();
		String sql = "select id,mobile, name, branch, vehicle, serno, problem, address, status, uid, date from service where branch="+branch+" and status!=1 order by id desc";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public void deleteService(Integer id) {
		Session session = sf.getCurrentSession();
		String sql = "delete from service where id="+id;
		NativeQuery a = session.createSQLQuery(sql);
		a.executeUpdate();	
		
	}


	public List<Object[]> billDetails(Integer id) {
		Session session = sf.getCurrentSession();
		String sql = "select branch.id as branchid,branch.name as branch,service.mobile as mobile,service.name as name,service.vehicle as vehicle,service.serno as carno,service.problem as problem,service.address as address from service left join branch on (branch.id=service.branch) where  service.status!=1 and service.id="+id;
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
		
	}

	public void addBill(String sid, String amount, String branchid) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		formatter.format(date);
		
		Session session = sf.getCurrentSession();
		String sql = "UPDATE `vehicleservice`.`service` SET `status` = '1' WHERE `service`.`id` = "+sid;
		NativeQuery a = session.createSQLQuery(sql);
		a.executeUpdate();	
		
		String sql1 = "INSERT INTO `vehicleservice`.`bill` (`id`,`branchid`, `servideid`, `amount`, `date`) VALUES (NULL, '"+branchid+"','"+sid+"', '"+amount+"', '"+formatter.format(date)+"');";
		NativeQuery nq = session.createSQLQuery(sql1);
		nq.executeUpdate();	
		
		
		
	}

	public List<Object[]> viewBill(Integer branch, String date) {
		Session session = sf.getCurrentSession();
		String sql = "select s.name,b.amount,s.mobile from bill as b left join service as s on (b.servideid=s.id)  where b.branchid="+branch+" and b.date='"+date+"'";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public List<Object[]> userdetails(Integer id) {
		
		Session session = sf.getCurrentSession();
		String sql = "select id,name,mobile,password,address,FLAG from user where id="+id;
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public List<Object[]> pendingService(Integer id) {
		Session session = sf.getCurrentSession();
		String sql = "select s.id as id,s.date as date,b.name as branchname,s.serno as serno,s.problem as problem from service as s left join branch as b on (b.id=s.branch) where s.status!=1 and  s.uid="+id;
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}


	public List<Object[]> deliveredService(Integer id) {
		Session session = sf.getCurrentSession();
		String sql = "select s.id as id,s.date as date,b.name as branchname,s.serno as serno,s.problem as problem from service as s left join branch as b on (b.id=s.branch) where s.status=1 and  s.uid="+id;
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}
	public List<Object[]> userBill(Integer branch, Integer id) {
		Session session = sf.getCurrentSession();
		String sql = "select s.branch as branchid,bra.name as branch,b.date as date,s.problem as problem,s.serno as serno,b.amount as amount from bill as b left join service as s on(s.id=b.servideid) left join branch as bra on (s.branch=bra.id) left join user as u on(u.name=s.name)  where u.id="+id+" and s.branch="+branch;
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}








	public List<Object[]> getProfile(Integer id) {
		Session session = sf.getCurrentSession();
		String sql = "select id,name,mobile,password,address,FLAG from user where id="+id;
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}
	

}
