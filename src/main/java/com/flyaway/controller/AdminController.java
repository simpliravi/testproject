package com.flyaway.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.flyaway.entity.*;
import com.flyaway.service.*;

@Controller
public class AdminController {
	
	@Autowired
    private AdminService adminService; 
	
	@Autowired
	private PlaceService placeService; 

	@Autowired
	private AirlineService airlineService; 

	@Autowired
	private FlightService flightService; 

	@Autowired
	private BookingService bookingService; 
	
	@Autowired
	private UserService userService; 
	
	
	  @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	    public String login(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  map.addAttribute("pageTitle", "ADMIN LOGIN");
	        return "admin/login"; 
	    }
	  
	  @RequestMapping(value = "/adminloginaction", method = RequestMethod.POST)
	    public String loginAction(ModelMap map, javax.servlet.http.HttpServletRequest request, 
	    		 @RequestParam(value="admin_id", required=true) String adminId,
	    		 @RequestParam(value="admin_pwd", required=true) String adminPwd) 
	    {
		  
		  Admin admin = adminService.authenticate(adminId, adminPwd);
		  if (admin == null) { 
			  map.addAttribute("error", "Admin login failed");
			  return "admin/login";
		  }
		  // store admin id in session
		  HttpSession session = request.getSession();
		  session.setAttribute("admin_id", admin.getID());
		  
	        return "admin/dashboard"; 
	    }	  
	    
	  @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
	    public String dashboard(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		   
		  map.addAttribute("pageTitle", "ADMIN DASHBOARD");
	        return "admin/dashboard"; 
	    }
	  
	   
	  @RequestMapping(value = "/adminchangepassword", method = RequestMethod.GET)
	    public String changePwd(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  Admin admin = adminService.getAdminById((Long) session.getAttribute("admin_id"));
		  
		  map.addAttribute("admin", admin);
		  map.addAttribute("pageTitle", "ADMIN CHANGE PASSWORD");
	        return "admin/change-password"; 
	    }

	  
	  @RequestMapping(value = "/adminchangepwdaction", method = RequestMethod.POST)
	    public String updatePassword(ModelMap map,  @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "admin/change-password";
		  }
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("error", "Error , Passwords do not match.");
			  return "admin/change-password";
		  }
		  Admin admin = adminService.getAdminById((Long) session.getAttribute("admin_id")); 
		  admin.setAdminPwd(pwd);
		  adminService.updatePwd(admin);
		  
	        return "admin/dashboard";  
	    }		  

	  
	  @RequestMapping(value = "/adminplaces", method = RequestMethod.GET)
	    public String places(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  List<Place> list = placeService.getAllPlaces();

		  for(Place place: list) {
		  }
		  map.addAttribute("list", list);

		  map.addAttribute("pageTitle", "ADMIN SETUP PLACES");
	        return "admin/places"; 
	    }
	  
	  @RequestMapping(value = "/adminairlines", method = RequestMethod.GET)
	    public String airlines(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  List<Airline> list = airlineService.getAllAirlines();

		  for(Airline place: list) {
		  }
		  map.addAttribute("list", list);

		  map.addAttribute("pageTitle", "ADMIN SETUP AIRLINES");
	        return "admin/airlines"; 
	    }
	  
	  @RequestMapping(value = "/adminmembers", method = RequestMethod.GET)
	    public String members(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  List<User> list = userService.getAllUsers();
		  
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN BROWSE MEMBERS");
	        return "admin/members"; 
	    }
	  
	  @RequestMapping(value = "/adminbookings", method = RequestMethod.GET)
	    public String bookings(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  List<Booking> list = bookingService.getAllBookings();
		  
		  BigDecimal total = new BigDecimal(0.0);
		  
		  for(Booking booking: list) {
			  total = total.add(booking.getPrice());
		  }
		  
		  map.addAttribute("totalAmount", total); 
		  map.addAttribute("list", list);
		  map.addAttribute("pageTitle", "ADMIN BOOKINGS REPORT");
	        return "admin/bookings"; 
	    }	  

	  @RequestMapping(value = "/admindeleteplace",  method = RequestMethod.GET)
	    public String deletePlace(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  if (idValue > 0) {
			  placeService.deletePlace(idValue);
		  }
		  return "redirect:adminplaces";
	    }	
	  
	  
	  @RequestMapping(value = "/admineditplace",  method = RequestMethod.GET)
	    public String editPlace(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  Place place = new Place();
		  if (idValue > 0) {
			  place = placeService.getPlaceById(idValue);
		  } else {
			  place.setID(0);
		  }
		  map.addAttribute("place", place);
		  map.addAttribute("pageTitle", "ADMIN EDIT PLACE");
	        return "admin/edit-place"; 
	    }		  

	  
	  @RequestMapping(value = "/admineditplaceaction", method = RequestMethod.POST)
	    public String updatePlace(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  long idValue = Long.parseLong(id); 
		  
		  if (name == null || name.equals("") ) { 
			  map.addAttribute("error", "Error, A place name must be specified");
			  return "redirect:admineditplace?id="+id;
		  }
		  	Place place = new Place();
		  	place.setID(idValue); 
		  	place.setName(name);
		  	
		  	placeService.updatePlace(place); 
		  	
	        return "redirect:adminplaces";  
	    }
	  
	  @RequestMapping(value = "/admindeleteairline",  method = RequestMethod.GET)
	    public String deleteAirline(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  Airline airline = new Airline();
		  if (idValue > 0) {
			  airlineService.deleteAirline(idValue);
		  }
		  return "redirect:adminairlines";
	    }	
	  

	  
	  @RequestMapping(value = "/admineditairline",  method = RequestMethod.GET)
	    public String editAirline(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  
		  long idValue = Long.parseLong(id);
		  Airline airline = new Airline();
		  if (idValue > 0) {
			  airline = airlineService.getAirlineById(idValue);
		  } else {
			  airline.setID(0);
		  }
		  map.addAttribute("airline", airline);
		  map.addAttribute("pageTitle", "ADMIN EDIT AIRLINE");
	        return "admin/edit-airline"; 
	    }
	  
	  
	  @RequestMapping(value = "/admineditairlineaction", method = RequestMethod.POST)
	    public String updateAirline(ModelMap map, javax.servlet.http.HttpServletRequest request,
	    		 @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name) 
	    {
		  long idValue = Long.parseLong(id); 
		  BigDecimal priceValue = new BigDecimal(0.0);
		  
		  if (name == null || name.equals("") ) { 
			  map.addAttribute("error", "Error, Airline name must be specified");
			  return "redirect:admineditairline?id="+id;
		  }
		  
		  	Airline airline = new Airline();
		  	airline.setID(idValue); 
		  	airline.setName(name);
		  	
		  	airlineService.updateAirline(airline); 
		  	
	        return "redirect:adminairlines"; 
	    }	
	   
	  
	  @RequestMapping(value = "/adminflights", method = RequestMethod.GET)
	    public String flights(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		    HttpSession session = request.getSession();
		  List<Flight> list = flightService.getAllFlights();

		  for(Flight flight: list) {
		  }
		  map.addAttribute("list", list);

		  map.addAttribute("pageTitle", "FLIGHTS");
	        return "admin/flights"; 
	    }

	  
	  @RequestMapping(value = "/admineditflight",  method = RequestMethod.GET)
	    public String editFlight(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  Flight flight = new Flight();
		  if (idValue > 0) {
			  flight = flightService.getFlightById(idValue);
		  } else {
			  flight.setID(0);
		  }
		  String airlineDropDown = airlineService.getAsDropDown(flight.getAirline());
		  String sourceDropDown = placeService.getAsDropDown(flight.getSource());
		  String destinationDropDown = placeService.getAsDropDown(flight.getDestination());
		  
		  map.addAttribute("flight", flight);
		  map.addAttribute("airlineDropDown", airlineDropDown);
		  map.addAttribute("sourceDropDown", sourceDropDown);
		  map.addAttribute("destinationDropDown", destinationDropDown);
		  map.addAttribute("pageTitle", "ADMIN EDIT FLIGHT");
	        return "admin/edit-flight"; 
	    }		  

	  @RequestMapping(value = "/admineditflightaction", method = RequestMethod.POST)
	    public String updateFlight(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="source", required=true) long source, 
	    		 @RequestParam(value="destination", required=true) long destination,
	    		 @RequestParam(value="airline", required=true) long airline,
	    		 @RequestParam(value="departure", required=true) String departure,
	    		 @RequestParam(value="arrival", required=true) String arrival,
	    		 @RequestParam(value="duration", required=true) String duration,
	    		 @RequestParam(value="price", required=true) String price,
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  long idValue = Long.parseLong(id); 
		  BigDecimal priceValue = new BigDecimal(0.0);
		  
		  if (source == 0) { 
			  map.addAttribute("error", "Error, Source is required");
			  return "redirect:admineditflight?id="+id;
		  }
		  if (destination == 0) { 
			  map.addAttribute("error", "Error, Destination is required");
			  return "redirect:admineditflight?id="+id;
		  }
		  if (source == destination) { 
			  map.addAttribute("error", "Error, Source and destination cannot be the same");
			  return "redirect:admineditflight?id="+id;
		  }
		  if (airline == 0) { 
			  map.addAttribute("error", "Error, Airline is required");
			  return "redirect:admineditflight?id="+id;
		  }
		  if (departure == null || departure.equals("")) { 
			  map.addAttribute("error", "Error, Departure time is required");
			  return "redirect:admineditflight?id="+id;
		  }
		  if (arrival == null || arrival.equals("")) { 
			  map.addAttribute("error", "Error, Arrival time is required");
			  return "redirect:admineditflight?id="+id;
		  }
		  if (duration == null || duration.equals("")) { 
			  map.addAttribute("error", "Error, Flight duration is required");
			  return "redirect:admineditflight?id="+id;
		  }
		  try {
			  priceValue = new BigDecimal(price);
			  
		  } catch (Exception ex) {
			  map.addAttribute("error", "Error, Price is invalid");
			  return "redirect:admineditflight?id="+id;
		  }
		  	Flight flight = new Flight();
		  	flight.setID(idValue); 
		  	flight.setAirline(airline);
		  	flight.setSource(source);
		  	flight.setDestination(destination);
		  	flight.setDeparture(departure);
		  	flight.setArrival(arrival);
		  	flight.setDuration(duration);
		  	flight.setPrice(priceValue);
		  	
		  	flightService.updateFlight(flight); 
		  	
	        return "redirect:adminflights";  
	    }

	  @RequestMapping(value = "/admindeleteflight",  method = RequestMethod.GET)
	    public String deleteFlight(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if session is still alive
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "admin/login";
		  }
		  long idValue = Long.parseLong(id);
		  if (idValue > 0) {
			  flightService.deleteFlight(idValue);
		  }
		  return "redirect:adminflights";
	    }	

	  
	  @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	    public String logout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  	
	        return "admin/login"; 
	    }
}

