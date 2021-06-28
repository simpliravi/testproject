package com.flyaway.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;  


@Entity
@Table (name="f_bookings")
public class Booking {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "flight_id")
	private long flightId;
	
	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "booking_date")
	private Date bookingDate;
	
	@Column(name = "flight_date")
	private Date flightDate;

	@ManyToOne
	@JoinColumn(name="flight_id", insertable = false, updatable = false)
	private Flight flightRow;

	@ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private User userRow;

	
	public long getID() {return this.ID; }
	public long getUserId() {return this.userId; }
	public long getFlightId() {return this.flightId; }
	public BigDecimal getPrice() {return this.price; }
	public Date getBookingDate() {return this.bookingDate; }
	public Date getFlightDate() {return this.flightDate; }
	public Flight  getFlightRow() { return this.flightRow;}
	public User getUserRow() { return this.userRow;}
	
	public void setID(long id) { this.ID = id;}
	public void setUserId(long id) { this.userId = id;}
	public void setFlightId(long id) { this.flightId= id;}
	public void setPrice(BigDecimal id) { this.price = id;}
	public void setBookingDate(Date value) { this.bookingDate = value;}
	public void setFlightDate(Date value) { this.flightDate = value;}
	public void setFlightRow(Flight flight) { this.flightRow = flight;} 
	public void setUserRow(User user) { this.userRow = user;}
}
