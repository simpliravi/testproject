package com.flyaway.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;  


@Entity
@Table(name= "f_user")   
public class User { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "date_signup")
	private Date dateSignup;  
	
	@Column(name = "email")
	private String email;

	@Column(name = "pwd")
	private String pwd;

	@OneToMany
	@JoinColumn(name ="user_id")
	private List<Booking> bookings;
	
	public long getID() {return this.ID; }
	public String getEmail() { return this.email;}
	public String getName() { return this.name;}
	public String getCity() { return this.city;}
	public String getAddress() { return this.address;}
	public String getPwd() { return this.pwd;}
	public Date getDateSignup() { return this.dateSignup;}
	
	public void setID(long id) { this.ID = id;}
	public void setEmail(String value) { this.email = value;}
	public void setName(String value) { this.name = value;}
	public void setCity(String value) { this.city = value;}
	public void setAddress(String value) { this.address= value;}
	public void setPwd(String value) { this.pwd= value;}
	public void setDateSignup(Date date) { this.dateSignup= date;}   
}
