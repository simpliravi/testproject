package com.flyaway.service;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
import com.flyaway.dao.FlightDAO;
import com.flyaway.entity.Flight;

@Component
public class FlightService {

	 @Autowired
	 private FlightDAO flightDAO;
	 
	 @Transactional
	 public Flight getFlightById(long id) {
		 return flightDAO.getFlightById(id);
	 }
	 
	 @Transactional
	 public List<Flight> getFlightsBySourceAndDest(long source, long destination) {
		 return flightDAO.getFlightsBySourceAndDest(source, destination);
	 }
	 
	 @Transactional
	 public void updateFlight(Flight flight) {
		 flightDAO.updateFlight(flight);
	 }
	 
	 @Transactional
	 public List<Flight> getAllFlights() {
		 return flightDAO.getAllFlights();
	 }
	 
	 public void deleteFlight(long id) {
		 flightDAO.deleteFlight(id);
	 }
	 
}
