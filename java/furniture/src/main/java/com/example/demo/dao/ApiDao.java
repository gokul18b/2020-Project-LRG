package com.example.demo.dao;

import java.math.BigInteger;
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

	

	public void add_customer(String name, String mobile, String alternate,String address, String gender) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `customer` (`id`, `name`, `mobile`,`alternate`, `address`, `gender`) VALUES (NULL, '"
				+ name + "', '" + mobile + "', '" + alternate + "', '" + address + "', '" + gender + "');";
		session.createSQLQuery(sql).executeUpdate();
	}

	
	public List<Object[]> get_customer() {
		Session session = sf.getCurrentSession();
		String sql = "Select * from customer";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public void add_product(String company, String model, Integer price) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `product` (`id`, `company`, `model`, `price`) VALUES (NULL, '" + company + "', '"
				+ model + "', '" + price + "');";
		session.createSQLQuery(sql).executeUpdate();
	}

	public List<Object[]> get_product() {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "Select * from product";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public void add_purchase(Integer product_id, Integer quantity, String details) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `purchase` (`id`, `product_id`, `quantity`, `details`, `date`) VALUES (NULL, '" + product_id
				+ "', '" + quantity + "', '" + details + "', current_timestamp());";
		session.createSQLQuery(sql).executeUpdate();
	}

	public void add_sales(Integer customer_id, Integer product_id, Integer quantity) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `sales` (`id`, `customer_id`, `product_id`, `quantity`) VALUES (NULL, '" + customer_id
				+ "', '" + product_id + "', '" + quantity + "');";
		session.createSQLQuery(sql).executeUpdate();
	}

	public List<Object[]> get_stock() {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "select p.company,p.model,COALESCE(sum(pqty),0) - COALESCE(sum(sqty),0) qty from product p \r\n"
				+ "LEFT JOIN (select product_id,COALESCE(SUM(quantity),0) pqty from purchase GROUP by product_id) as a on a.product_id = p.id\r\n"
				+ "LEFT JOIN (select product_id,COALESCE(SUM(quantity),0) sqty from sales GROUP by product_id) as b on b.product_id = p.id\r\n"
				+ "GROUP BY p.company,p.model";
		System.out.println(sql);
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}

	public Integer get_mobile(String mobile) {
		Session session = sf.getCurrentSession();
		String sql = "Select id,name from customer where mobile='" + mobile + "'";
		NativeQuery nq = session.createNativeQuery(sql);
		List<Object[]> list = nq.getResultList();
		if (list.size() != 0) {
			return (Integer) list.get(0)[0];
		} else {
			return null;
		}

	}

	public List<Object[]> get_billing() {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "select customer.name,customer.mobile,product.company,product.model,sales.quantity,product.price from sales LEFT JOIN customer on(customer.id=sales.customer_id) LEFT JOIN product on(product.id=sales.product_id) ";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
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

}
