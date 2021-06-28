package com.flyaway.dao;

import java.util.ArrayList;
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

import com.flyaway.entity.Flight;
 

@Repository
@Component
public class FlightDAO {

	@Autowired
    private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	public Flight getFlightById(long id) {
		String strId = String.valueOf(id);
		List<Flight> list = this.sessionFactory.getCurrentSession().createQuery("from Flight where id=" + strId).list();
		if (list.size() > 0)
			return (Flight) list.get(0);
		else
			return null;
		
	}
	@SuppressWarnings("unchecked")
	public List<Flight> getFlightsBySourceAndDest(long source, long destination) {
		List<Flight> list = new ArrayList<Flight>();
		String sql = " from Flight ";
		String and = " where ";
		if (source > 0 ) {
			sql += " where source=:source ";
			and = " and ";
		}
		if (destination > 0) {
			sql += and + " destination = :destination";
			and = " and ";
		}
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		if (source > 0)
				query.setParameter("source", source);
		if (destination > 0)
				query.setParameter("destination", destination);
		list = query.list();
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public void updateFlight(Flight flight) {
		String sql = "";
		if (flight.getID() == 0) {
			this.sessionFactory.getCurrentSession().save(flight);
		} else if (flight.getID() > 0) {
			sql = "update Flight set source=:source, destination=:destination, airline_id=:airline " + 
						", departure=:departure, arrival=:arrival, price=:price, duration=:duration where ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("source", flight.getSource());
			query.setParameter("destination", flight.getDestination());
			query.setParameter("airline", flight.getAirline());
			query.setParameter("departure", flight.getDeparture());
			query.setParameter("arrival", flight.getArrival());
			query.setParameter("duration", flight.getDuration());
			query.setParameter("price", flight.getPrice());
			query.setParameter("id", flight.getID());
			
			query.executeUpdate();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Flight> getAllFlights() {
		List<Flight> list = this.sessionFactory.getCurrentSession().createQuery("from Flight  order by ID desc").list();

		return list;
	}


	
	@SuppressWarnings("unchecked")
	@Transactional
	public void deleteFlight(long id) {
	
		String sql = "delete from Flight where ID=:id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}
}
