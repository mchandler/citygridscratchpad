package com.rmwebfx.citygrid;

import com.citygrid.CGException;
import com.citygrid.CityGrid;
import com.citygrid.content.places.search.*;
import com.citygrid.content.places.detail.*;

public class Test {
	
	public static void main(String[] args) {
		//testPlacesSearch();
		testPlace();
	}
	
	public static void testPlacesSearch() {
		CityGrid.setPublisher("10000002304");
		CityGrid.setSimulation(false);
		
		CGPlacesSearch search = CityGrid.placesSearch();
	    search.setType(CGPlacesSearchType.Restaurant);
	    search.setWhere("Simi Valley, CA");
	    search.setHistograms(true);
	    
	    try {
	        CGPlacesSearchResults results = search.search();
	        CGPlacesSearchLocation[] locations = results.getLocations();
	        CGPlacesSearchLocation location = locations[1];
	        int count = locations.length;
	        System.out.println("Total matches: " + new Integer(results.getTotalHits()).toString());
	        System.out.println("First Match: " + location.getName() + " - id: " + location.getLocationId());
	    }
	    catch (CGException e) {
	        System.out.println("Something went wrong" + e);
	    }
	}
	
	public static void testPlace() {
		// 35777906
		CityGrid.setPublisher("10000002304");
		CityGrid.setSimulation(false);
		
		CGPlacesDetail detail = CityGrid.placesDetail();
		detail.setLocationId(35777906);
		
		try {
			CGPlacesDetailResults results = detail.detail();
			CGPlacesDetailLocation location = results.getLocation();
			System.out.println("Worked! This is " + location.getName());
		} catch (CGException e) {
			System.out.println("Hosed: " + e);
		}
	}
}
