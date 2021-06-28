package com.flyaway.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flyaway.entity.Booking; 

@Repository
@Component
public class BookingDAO {

	@Autowired
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Booking getBookingById(long id) {
		String strId = String.valueOf(id);
		List<Booking> list = this.sessionFactory.getCurrentSession().createQuery("from Booking where id=" + strId).list();
		if (list.size() > 0)
			return (Booking) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Booking> getAllBookings() {
		List<Booking> list = this.sessionFactory.getCurrentSession().createQuery("from Booking order by ID desc").list();
		return list;
	}	
		
	@SuppressWarnings("unchecked")
	public List<Booking> getAllBookingsByUserId(long userId) {
		String strId = String.valueOf(userId);
		List<Booking> list = this.sessionFactory.getCurrentSession().createQuery("from Booking where user_id=" + strId + " order by ID desc").list();
		return list;
	}	
	
	@SuppressWarnings("unchecked")
	public long  updateBooking(Booking booking) {
		String sql = "";
		long newId = 0;
		if (booking.getID() == 0) {
			this.sessionFactory.getCurrentSession().save(booking);
			newId = booking.getID();
		} else {
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("user_id", booking.getUserId());
			query.setParameter("flight_id", booking.getFlightId());
			query.setParameter("price", booking.getPrice());
			query.setParameter("booking_date", booking.getBookingDate());
			query.setParameter("flight_date", booking.getFlightDate());
			query.executeUpdate();
		}
		return newId;
	}
	

	
}
