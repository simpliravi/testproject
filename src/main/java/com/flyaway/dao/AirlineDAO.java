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

import com.flyaway.entity.Airline;
 

@Repository
@Component
public class AirlineDAO {

	@Autowired
    private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	public Airline getAirlineById(long id) {
		String strId = String.valueOf(id);
		List<Airline> list = this.sessionFactory.getCurrentSession().createQuery("from Airline where id=" + strId).list();
		if (list.size() > 0)
			return (Airline) list.get(0);
		else
			return null;
		
	}
	@SuppressWarnings("unchecked")
	public Airline getAirlineByName(String name) {
		List<Airline> list = this.sessionFactory.getCurrentSession().createQuery("from Airline where name='" + name + "'").list();
		if (list.size() > 0)
			return (Airline) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public void updateAirline(Airline airline) {
		String sql = "";
		if (airline.getID() == 0) {
			this.sessionFactory.getCurrentSession().save(airline);
		} else if (airline.getID() > 0) {
			sql = "update Airline set name=:name where ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("name", airline.getName());
			if (airline.getID() > 0)
				query.setParameter("id", airline.getID());
			
			query.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Airline> getAllAirlines() {
		List<Airline> list = this.sessionFactory.getCurrentSession().createQuery("from Airline  order by name").list();

		return list;
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public void deleteAirline(long id) {

		String sql = "delete from Airline  where ID=:id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		 
	}
}
