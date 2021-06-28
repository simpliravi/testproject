package com.flyaway.service;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
import com.flyaway.dao.PlaceDAO;
import com.flyaway.entity.Airline;
import com.flyaway.entity.Place;

@Component
public class PlaceService {

	 @Autowired
	 private PlaceDAO placeDAO;
	 
	 @Transactional
	 public Place getPlaceById(long id) {
			return placeDAO.getPlaceById(id);
	 }
		
	 @Transactional
	 public Place getPlaceByName(String name) {
		 return placeDAO.getPlaceByName(name);
	 }
		
	 @Transactional
	 public void updatePlace(Place place) {
		 placeDAO.updatePlace(place);
	 }
	 
	 @Transactional
	 public List<Place> getAllPlaces() {
		 return placeDAO.getAllPlaces();
	 }
	 
	 @Transactional
	 public void deletePlace(long id) {
		 placeDAO.deletePlace(id);
	 }
	 
	 @Transactional
	 public String getAsDropDown(long id) {
		 StringBuilder sb = new StringBuilder("");
		 List<Place> list = placeDAO.getAllPlaces();
		 for(Place place: list) {
			 if (place.getID() == id)
				 sb.append("<option value=" + String.valueOf(place.getID()) + " selected>" + place.getName() + "</option>");
			 else
				 sb.append("<option value=" + String.valueOf(place.getID()) + ">" + place.getName() + "</option>");
				 
		 }
		 return sb.toString();
		}
}
