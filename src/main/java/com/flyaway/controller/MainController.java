package com.flyaway.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
public class MainController {

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
	

	  @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	    public String flights(ModelMap map, javax.servlet.http.HttpServletRequest request,
	    		@RequestParam(value="source", required=false) String source,
	    		@RequestParam(value="destination", required=false) String destination) 
	    {
		  long sourceId = 0, destinationId = 0;
		  try {
			  sourceId = Long.parseLong(source);
			  
		  } catch (Exception ex) {	  
		  }
		  
		  try {
			  destinationId = Long.parseLong(destination);
			  
		  } catch (Exception ex) {
			  
		  }
		  
		  String destinationDropDown = placeService.getAsDropDown(destinationId);
		  String sourceDropDown = placeService.getAsDropDown(sourceId);

		  
		  List<Flight> list = flightService.getFlightsBySourceAndDest(sourceId, destinationId);

		  for(Flight flight: list) {
		  }
		  map.addAttribute("list", list);
		  map.addAttribute("sourceDropDown", sourceDropDown);
		  map.addAttribute("destinationDropDown", destinationDropDown);
		  
		  map.addAttribute("pageTitle", "FLYAWAY HOMEPAGE");
	        return "index"; 
	    }
	  
	  @SuppressWarnings("unchecked")
	@RequestMapping(value = "/bookflight", method = RequestMethod.GET)
	    public String cart(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before booking a flight.");
			  return "redirect:home";
			  
		  } else {
			  long flightId = 0;
			  try {
				  flightId = Long.parseLong(id);
				  
			  } catch (Exception ex) {
				  map.addAttribute("error", "Error, Selected flight looks invalid.");
				  return "redirect:home";
			  }
			  Flight flight = flightService.getFlightById(flightId);
			  session.setAttribute("flight_id", flightId);
			  session.setAttribute("flightPrice", flight.getPrice());
			  session.setAttribute("flightDate", Calendar.getInstance().getTime());
			  session.setAttribute("flightAirline", flight.getAirlineRow().getName());
			  session.setAttribute("sourceName", flight.getSourceRow().getName());
			  session.setAttribute("destinationName", flight.getDestinationRow().getName());
			  session.setAttribute("flightDeparture", flight.getDeparture());
			  session.setAttribute("flightArrival", flight.getArrival());
			  session.setAttribute("flightDuration", flight.getDuration());
 		  }
		  
		  map.addAttribute("pageTitle", "FLYAWAY - PAYMENT GATEWAY");
	        return "gateway"; 
	    }
	  
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(ModelMap map) 
	    {
		  map.addAttribute("pageTitle", "FLYAWAY - MEMBER LOGIN");
	        return "login"; 
	    }	
	  
	  @RequestMapping(value = "/loginaction", method = RequestMethod.POST)
	    public String loginAction(ModelMap map,  javax.servlet.http.HttpServletRequest request,
	    		@RequestParam(value="email_id", required=true) String emailId,
	    		 @RequestParam(value="pwd", required=true) String pwd) 
	    {
		  User user = userService.authenticate(emailId, pwd);
		  if (user == null) { 
			  map.addAttribute("error", "Login failed");
			  return "login";
		  }
		  HttpSession session = request.getSession();
		  session.setAttribute("user_id", user.getID());
		  
		  return "redirect:dashboard"; 
	    }		  
	  
	  	  
	  @RequestMapping(value = "/signup", method = RequestMethod.GET)
	    public String signup(ModelMap map) 
	    {
		  map.addAttribute("pageTitle", "FLYAWAY - MEMBER REGISTRATION");
	        return "register"; 
	    }	
	  
	  @RequestMapping(value = "/signupaction", method = RequestMethod.POST)
	    public String signupAction(ModelMap map,  javax.servlet.http.HttpServletRequest request,
	    		@RequestParam(value="email_id", required=true) String emailId,
	    		 @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2,
	    		 @RequestParam(value="name", required=true) String name,
	    		 @RequestParam(value="address", required=true) String address,
	    		 @RequestParam(value="city", required=true) String city
	    		 ) 
	    {
		 
		  if (emailId == null || emailId.equals("")) {
			  map.addAttribute("error", "Email id is required.");
			  return "register";
		  }

		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "register";
		  }
		  
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("error", "Error , Passwords do not match.");
			  return "register";
		  }
		  
		  if (name == null || name.equals("")) {
			  map.addAttribute("error", "Name is required.");
			  return "register";
		  }

		  
		  User user = userService.getUserByEmailId(emailId);
		  if (user != null) { 
			  map.addAttribute("error", "This email id already exists.");
			  return "register";
		  }
		  user = new User();
		  user.setID(0);
		  user.setEmail(emailId);
		  user.setName(name);
		  user.setCity(city);
		  user.setAddress(address);
		  user.setPwd(pwd);
		  
		  userService.updateUser(user);
		  
		  
	        return "redirect:registerconfirm"; 
	    }
	  
	  
	  @RequestMapping(value = "/registerconfirm", method = RequestMethod.GET)
	    public String registerConfirm(ModelMap map) 
	    {
	        return "register-confirm"; 
	    }		 		  

	  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	    public String dashboard(ModelMap map,  javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  return "login";
		  }
		  map.addAttribute("pageTitle", "FLYAWAY - DASHBOARD");
	        return "dashboard"; 
	    }		

	  @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	    public String editProfile(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  return "login";
		  }
		  User user = userService.getUserById((Long) session.getAttribute("user_id"));

		  map.addAttribute("pageTitle", "FLYAWAY - EDIT PROFILE");
		  map.addAttribute("user", user);

	        return "edit-profile"; 
	    }		 	  


	  @RequestMapping(value = "/editprofileaction", method = RequestMethod.POST)
	    public String editProfileAction(ModelMap map,
	    		javax.servlet.http.HttpServletRequest request, 
	    		@RequestParam(value="user_id", required=true) String userId,
	    		 @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2,
	    		 @RequestParam(value="name", required=true) String name,
	    		 @RequestParam(value="city", required=true) String city,
	    		 @RequestParam(value="address", required=true) String address) 
	    {
		  
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  return "login";
		  }
		  
		  User user = userService.getUserById((Long) session.getAttribute("user_id"));
		  map.addAttribute("user", user);
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "edit-profile";
		  }
		  
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("error", "Error , Passwords do not match.");
			  return "edit-profile";
		  }
		  
		  if (name == null || name.equals("")) {
			  map.addAttribute("error", "Name is required.");
			  return "edit-profile";
		  }

		
		  user.setName(name);
		  user.setCity(city);
		  user.setAddress(address);
		  user.setPwd(pwd);
		  
		  userService.updateUser(user);
		  
	        return "redirect:dashboard"; 
	    }

	  @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public String logout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  	
	        return "redirect:home"; 
	    }
	  @RequestMapping(value = "/completepurchase", method = RequestMethod.GET)
	    public String completePurchase(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  // check if user is logged in
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login before completing your booking");
		  } else {
			  // take flight booked from session
			  long flightId = (Long) session.getAttribute("flight_id");
			  Flight flight = flightService.getFlightById(flightId);
			  
			  long userId = (Long) session.getAttribute("user_id") ;
			  
			  Booking booking= new Booking();
			  booking.setUserId(userId);
			  booking.setBookingDate(Calendar.getInstance().getTime());
			  booking.setFlightDate(booking.getBookingDate());
			  booking.setFlightId(flightId);
			  booking.setPrice(flight.getPrice());
			  long bookingId = bookingService.updateBooking(booking);
			  session.setAttribute("booking_id", bookingId);

			  map.addAttribute("pageTitle", "FLYAWAY - BOOKING CONFIRMATION");			  
			  map.addAttribute("flight", flight);
			  map.addAttribute("booking", booking);

		  }
		  
	        return "confirm";  
	    }

	  @RequestMapping(value = "/memberbookings", method = RequestMethod.GET)
	    public String bookings(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("user_id") == null) {
			  map.addAttribute("error", "Error, You need to login to see your bookings");
		  } else {
			  long userId = (Long) session.getAttribute("user_id") ;
			  List<Booking> list = bookingService.getAllBookingsByUserId(userId);
			  map.addAttribute("list", list);

			  map.addAttribute("pageTitle", "FLYAWAY - YOUR BOOKINGS");
		  }
	        return "bookings"; 
	    }


}
