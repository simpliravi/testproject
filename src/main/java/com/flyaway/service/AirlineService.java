package com.flyaway.service;


import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.flyaway.dao.AirlineDAO;
import com.flyaway.entity.Airline;

@Component
public class AirlineService {

	 @Autowired
	 private AirlineDAO airlineDAO;
	 
	 @Transactional
	 public Airline getAirlineById(long id) {
		 return airlineDAO.getAirlineById(id);
	 }
	 
	 @Transactional
	 public Airline getAirlineByName(String name) {
		 return airlineDAO.getAirlineByName(name);
	 }
	 
	 @Transactional
	 public void updateAirline(Airline airline) {
		 airlineDAO.updateAirline(airline);
	 }
	 
	 @Transactional
	 public List<Airline> getAllAirlines() {
		 return airlineDAO.getAllAirlines();
	 }

	 @Transactional
	 public void deleteAirline(long id) {
		 airlineDAO.deleteAirline(id);
	 }
	 
	 @Transactional
	 public String getAsDropDown(long id) {
		 StringBuilder sb = new StringBuilder("");
		 List<Airline> list = airlineDAO.getAllAirlines();
		 for(Airline airline: list) {
			 if (airline.getID() == id)
				 sb.append("<option value=" + String.valueOf(airline.getID()) + " selected>" + airline.getName() + "</option>");
			 else
				 sb.append("<option value=" + String.valueOf(airline.getID()) + ">" + airline.getName() + "</option>");
				 
		 }
		 return sb.toString();
		}
		
}
