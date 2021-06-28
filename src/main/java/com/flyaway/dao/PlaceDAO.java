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

import com.flyaway.entity.Place;
 

@Repository
@Component
public class PlaceDAO {

	@Autowired
    private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	public Place getPlaceById(long id) {
		String strId = String.valueOf(id);
		List<Place> list = this.sessionFactory.getCurrentSession().createQuery("from Place where id=" + strId).list();
		if (list.size() > 0)
			return (Place) list.get(0);
		else
			return null;
		
	}
	@SuppressWarnings("unchecked")
	public Place getPlaceByName(String name) {
		List<Place> list = this.sessionFactory.getCurrentSession().createQuery("from Place where name='" + name + "'").list();
		if (list.size() > 0)
			return (Place) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public void updatePlace(Place place) {
		String sql = "";
		if (place.getID() == 0) {
			this.sessionFactory.getCurrentSession().save(place);
		} else if (place.getID() > 0) {
			sql = "update Place set name=:name where ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("name", place.getName());
			if (place.getID() > 0)
				query.setParameter("id", place.getID());
			
			query.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Place> getAllPlaces() {
		List<Place> list = this.sessionFactory.getCurrentSession().createQuery("from Place  order by name").list();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public void deletePlace(long id) {
		
		String sql = "delete from Place  where ID=:id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	
}
