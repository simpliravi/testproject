package com.flyaway.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;  


@Entity
@Table (name="f_airlines")
public class Airline {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "name")
	private String name;
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="airline_id")
	private List<Flight> flights;

	
	public long getID() {return this.ID; }
	public String getName() { return this.name;}
	
	public void setID(long id) { this.ID = id;}
	public void setName(String value) { this.name= value;}
}
