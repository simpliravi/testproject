package com.flyaway.service;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
import com.flyaway.dao.BookingDAO;
import com.flyaway.entity.Booking;

@Component
public class BookingService {

	 @Autowired
	 private BookingDAO bookingDAO;
	 
	 @Transactional
	public Booking getBookingById(long id) {
		 return bookingDAO.getBookingById(id);
	 }
	 
	 @Transactional
	 public List<Booking> getAllBookings() {
		 return bookingDAO.getAllBookings();
	 }
	 
	 @Transactional
	 public List<Booking> getAllBookingsByUserId(long userId) {
		 return bookingDAO.getAllBookingsByUserId(userId);
	 }	
	 
	 @Transactional
	 public long  updateBooking(Booking booking) {
		 return bookingDAO.updateBooking(booking);
	 }
		
}
