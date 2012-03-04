package com.rmwebfx.citygrid;

import com.citygrid.CGException;
import com.citygrid.CityGrid;
import com.citygrid.CGBaseReview;
import com.citygrid.ads.custom.CGAdsCustom;
import com.citygrid.ads.custom.CGAdsCustomAd;
import com.citygrid.ads.custom.CGAdsCustomResults;
import com.citygrid.content.places.search.*;
import com.citygrid.content.places.detail.*;

public class Test {
	
	public static void main(String[] args) {
		//testPlacesSearch();
		//testPlace();
		testAd();
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
	        CGPlacesSearchLocation location = locations[2];
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
		// 664259190
		CityGrid.setPublisher("10000002304");
		CityGrid.setSimulation(false);
		
		CGPlacesDetail detail = CityGrid.placesDetail();
		detail.setLocationId(664259190);
		
		try {
			CGPlacesDetailResults results = detail.detail();
			CGPlacesDetailLocation location = results.getLocation();
			CGPlacesDetailReviews reviewContainer = location.getReviews();
			CGBaseReview[] reviews = reviewContainer.getReviews();
			System.out.println("Worked! This is " + location.getName());
			System.out.println("Total Reviews: " + new Integer(reviewContainer.getCount()).toString());
			System.out.println("Rating: " + new Integer(location.getRating()).toString());
			System.out.println("Hours: " + location.getBusinessHours());
			
			CGBaseReview review = reviews[0];
			System.out.println(review.getTitle());
			System.out.println(review.getText());
			System.out.println(review.getRating());
		} catch (CGException e) {
			System.out.println("Hosed: " + e);
		}
	}
	
	public static void testAd() {
		CityGrid.setPublisher("10000002304");
		CityGrid.setSimulation(true);
		
		CGAdsCustom search = CityGrid.adsCustom();
		search.setWhat("restaurant");
		search.setWhere("Simi Valley, CA");
		search.setRadius(30.0f);
		
		try {
			CGAdsCustomResults results = search.search();
			CGAdsCustomAd[] ads = results.getAds(); //sometimes there are no ad matches!
			
			if (ads.length > 0) {
				CGAdsCustomAd ad = results.getAd();
				System.out.println("Ad for: " + ad.getName());
			}
			
		} catch (CGException e) {
			System.out.println("Poof: " + e);
		}
		
		
	}
}
