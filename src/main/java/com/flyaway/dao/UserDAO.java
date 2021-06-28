package com.flyaway.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flyaway.entity.Admin;
import com.flyaway.entity.User; 

@Repository
@Component
public class UserDAO {

	@Autowired
    private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	public User authenticate(String emailId, String pwd) {
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where email=:emailid and pwd=:pwd")
				.setParameter("emailid", emailId)
				.setParameter("pwd", pwd)
				.list();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public User getUserById(long id) {
		String strId = String.valueOf(id);
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where id=" + strId).list();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	@SuppressWarnings("unchecked")
	public User getUserByEmailId(String emailId) {
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User where email='" + emailId + "'").list();
		if (list.size() > 0)
			return (User) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public void updateUser(User user) {
		String sql = "";
		if (user.getID() == 0) {
			user.setDateSignup(Calendar.getInstance().getTime());
			this.sessionFactory.getCurrentSession().save(user);
		} else if (user.getID() > 0) {
			sql = "update User set name=:name, city=:city, address=:address, pwd=:pwd where " +
					" ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("name", user.getName());
			query.setParameter("city", user.getCity());
			query.setParameter("address", user.getAddress());
			query.setParameter("pwd", user.getPwd());
			if (user.getID() > 0)
				query.setParameter("id", user.getID());
			
			query.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> list = this.sessionFactory.getCurrentSession().createQuery("from User order by date_signup").list();

		return list;
	}
	
}
