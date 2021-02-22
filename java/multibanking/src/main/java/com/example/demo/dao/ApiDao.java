package com.example.demo.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApiDao {
	@Autowired
	SessionFactory sf;

	
	public void bank_register(String bankname,String address,String ifsccode,String landline) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `bank` (`id`, `bankname`, `address`, `ifsccode`, `landline`)"
				+ " VALUES (NULL, '"+bankname+"', '"+address+"', '"+ifsccode+"', '"+landline+"');";
		session.createSQLQuery(sql).executeUpdate();
	}
	
	public List<Object[]> get_bank() {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "Select * from bank";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public void add_manager(Integer bankid,String mobile,String landline,String username,String password) {
		// TODO Auto-generated method stub
			Session session = sf.getCurrentSession();
			String sql = "INSERT INTO `manager` (`id`, `bank_id`, `mobile`, `landline`, `username`, `password`) "
					+ "VALUES (NULL, "+bankid+", '"+mobile+"', '"+landline+"', '"+username+"', '"+password+"');";
			session.createSQLQuery(sql).executeUpdate();
	}
	
	


	public Boolean login(String username, String password) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "select * from admin where username='"+username+"' and password='"+password+"'";;
		NativeQuery nq = session.createNativeQuery(sql);
		if (nq.list().size() != 0) {
			return true;
		} else {
			return false;
		}
	}
	public Boolean manager_login(String username, String password) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "select * from manager where username='"+username+"' and password='"+password+"'";;
		NativeQuery nq = session.createNativeQuery(sql);
		if (nq.list().size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public void add_customer(String fname, String lname, String mobile, String aadhar, String idproof, String age,
			String gender, String address1, String address2, String city, String state, String pincode) {
		// TODO Auto-generated method stub
		
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `customer` (`id`, `fname`, `lname`, `mobile`, `aadhar`, `idproof`, `age`, `gender`, `address1`, `address2`, `city`, `state`, `pincode`) "
				+ "VALUES (NULL, '"+fname+"', '"+lname+"', '"+mobile+"', '"+aadhar+"', '"+idproof+"', '"+age+"', "
						+ "'"+gender+"', '"+address1+"', '"+address2+"', '"+city+"', '"+state+"', '"+pincode+"');";
		session.createSQLQuery(sql).executeUpdate();
	}

	public void add_customer(Integer customer_id, String bank_id, String account_type) {
		// TODO Auto-generated method stub
		
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `account` (`id`, `customer_id`, `bank_id`, `account_type`) VALUES "
				+ "(NULL, '"+customer_id+"', '"+bank_id+"', '"+account_type+"');";
		session.createSQLQuery(sql).executeUpdate();
	}

	public List<Object[]> get_customer_bank() {
		Session session = sf.getCurrentSession();
		String sql = "Select * from account where id=";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

}
