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




	public void member_register(String firstname, String lastname, String mobile, String email, Integer age,
			String gender, String height, String weight, String address) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `member` (`id`, `firstname`, `lastname`, `mobile`, `email`, `age`, `gender`, `height`, `weight`, `address`) VALUES"
				+ " (NULL, '"+firstname+"', '"+lastname+"', '"+mobile+"', '"+email+"', '"+age+"', '"+gender+"', '"+height+"', '"+weight+"', '"+address+"');";
		session.createSQLQuery(sql).executeUpdate();
	}



	public void add_material(String material_name, Integer quantity, Integer amount) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `material` (`id`, `material_name`, `quantity`, `amount`) "
				+ "VALUES (NULL, '"+material_name+"', '"+quantity+"', '"+amount+"');";
		session.createSQLQuery(sql).executeUpdate();
	}



	public List<Object[]> get_material() {
		Session session = sf.getCurrentSession();
		String sql = "Select * from material";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}



	public void add_service(String material_name, String servicename, Integer amount, String issue) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `service` (`id`, `material_name`, `provider`, `amount`, `issue`, `date`) VALUES "
				+ "(NULL, '"+material_name+"', '"+servicename+"', '"+amount+"', '"+issue+"', CURRENT_TIMESTAMP);";
		session.createSQLQuery(sql).executeUpdate();
	}



	public List<Object[]> get_service() {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "Select * from service";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}



	public void add_health(String title, String description) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "INSERT INTO `health` (`id`, `title`, `description`, `date`) VALUES (NULL, '"+title+"', '"+description+"', CURRENT_TIMESTAMP);";
		session.createSQLQuery(sql).executeUpdate();
	}



	public List<Object[]> get_health() {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "Select * from health";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}



	public List<Object[]> get_members() {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "Select * from member";
		NativeQuery nq = session.createNativeQuery(sql);
		return nq.list();
	}



	public String punch_in(Integer id) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "Select * from punch where punch_out is null  and member_id="+id;
		NativeQuery nq = session.createNativeQuery(sql);
		if(nq.list().size()==0) {
			session.createSQLQuery("INSERT INTO `punch` (`id`, `member_id`, `punch_in`, `punch_out`, `date`) VALUES (NULL, '"+id+"', CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP);").executeUpdate();
			return "Punch IN Sucessfully saved";
		}else {
			return "You are already put punch ";	
		}
		
		
	}
	
	public String punch_out(Integer id) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "Select * from punch where punch_in is not null and punch_out is null  and member_id="+id;
		NativeQuery nq = session.createNativeQuery(sql);
		List<Object[]> res = nq.list();
		if(res.size()!=0) {
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
			
			Integer punchid= (Integer)res.get(0)[0];
			session.createSQLQuery("UPDATE `punch` SET "
					+ "`punch_out` = '"+df.format(dateobj)+"' WHERE `punch`.`id` = "+punchid+";").executeUpdate();
			return "Punch OUT Sucessfully saved";
		}else {
			return "You are not put punch";	
		}
	}



	public List<Object[]> get_punch_details(String mobile) {
		// TODO Auto-generated method stub
		Session session = sf.getCurrentSession();
		String sql = "SELECT punch.punch_in,punch.punch_out,punch.date FROM `punch` punch JOIN member mem ON(mem.id=punch.member_id)\r\n" + 
				"where mem.mobile='"+mobile+"'";
		
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
