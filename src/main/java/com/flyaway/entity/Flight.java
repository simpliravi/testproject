package com.flyaway.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;  


@Entity
@Table (name="f_flights")
public class Flight {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "source")
	private long source;
	
	@Column(name = "destination")
	private long destination;

	@Column(name = "airline_id")
	private long airline_id;
	
	@Column(name = "departure")
	private String departure;

	@Column(name = "arrival")
	private String arrival;
	
	@Column(name = "time_taken")
	private String duration;
	
	@Column(name = "price")
	private BigDecimal price;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="flight_id")
	private List<Booking> bookings;
	
	@ManyToOne
	@JoinColumn(name="airline_id", insertable = false, updatable = false)
	private Airline airline;
	
	@ManyToOne
	@JoinColumn(name="source", insertable = false, updatable = false)
	private Place  sourceRow;

	@ManyToOne
	@JoinColumn(name="destination", insertable = false, updatable = false)
	private Place  destinationRow;
	
	public long getID() {return this.ID; }
	public long getSource() {return this.source; }
	public long getDestination() {return this.destination; }
	public long getAirline() {return this.airline_id; }
	public String getDeparture() {return this.departure; }
	public String getArrival() {return this.arrival; }
	public String getDuration() {return this.duration; }
	public BigDecimal getPrice() {return this.price; }
	public Airline getAirlineRow() { return this.airline;}
	public Place getSourceRow() { return this.sourceRow;}
	public Place getDestinationRow() { return this.destinationRow;}
	
	public void setID(long id) { this.ID = id;}
	public void setSource(long id) { this.source = id;}
	public void setDestination(long id) { this.destination = id;}
	public void setAirline(long id) { this.airline_id = id;}
	public void setDeparture(String value) { this.departure = value;}
	public void setArrival(String value) { this.arrival = value;}
	public void setDuration(String value) { this.duration = value;}
	public void setPrice(BigDecimal value) { this.price= value;}
	public void setAirlineRow(Airline airline) { this.airline = airline;}
	public void setSourceRow(Place source) { this.sourceRow = source;}
	public void setDestinationRow(Place destination) { this.destinationRow = destination;}
}