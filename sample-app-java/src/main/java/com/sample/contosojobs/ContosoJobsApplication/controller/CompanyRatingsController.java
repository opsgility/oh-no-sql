package com.sample.contosojobs.ContosoJobsApplication.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyRating;
import com.sample.contosojobs.ContosoJobsApplication.Entity.Rating;
import com.sample.contosojobs.ContosoJobsApplication.service.ICompanyService;

@Controller
@RequestMapping("/CompanyRatings")
public class CompanyRatingsController {

	private ICompanyService _companyService;
	
	public CompanyRatingsController(ICompanyService companyService) {
		_companyService = companyService;
	}
	@RequestMapping("")
	public String CompanyRatings(@RequestParam("getId") String companyId, Model model) {
		model.addAttribute("title", "Company Rating");
		CompanyRating rating = new  CompanyRating();
		CompanyInfo company = _companyService.getCompanyByCompanyId(companyId);
		rating.setCompanyName(company.getName());
		rating.setCompanyId(company.getId());
		
		//  ********* replace this and get ratings from NoSql ************
		List<Rating> ratings = getMockRatings();
		rating.setRatings(ratings);

		// push to model and bind to view. 
		model.addAttribute("companyRating", rating);
		model.addAttribute("title", "Add Company User");
		
		 
		return "company-ratings";
	}
	
	private List<Rating> getMockRatings() {
		Date newDate = new Date();
		List<Rating> ratings = new ArrayList<Rating>();
		Rating rating1 = new Rating();

		rating1.setNumberOfStars(3);
		rating1.setHeading("Mock Rating 1");
		rating1.setBody("Mocke review body 1");
		rating1.setDate(newDate);
		
		Rating rating2 = new Rating();
		rating2.setNumberOfStars(4);
		rating2.setHeading("Mock Rating 2");
		rating2.setBody("Mocke review body 2");
		rating2.setDate(newDate);
		
		ratings.add(rating1);
		ratings.add(rating2);
		return ratings;
	}
	
	
	@RequestMapping("/AddReview")
	public String AddReview(Model model, HttpServletRequest request) {
		model.addAttribute("title", "Add Review");
		CompanyRating companyRating = new  CompanyRating();
		Rating newRating = new Rating();
		
		String ratingId = request.getParameter("ratingId");
		String companyId = request.getParameter("companyId");

		CompanyInfo company = _companyService.getCompanyByCompanyId(companyId);
		companyRating.setCompanyName(company.getName());
		companyRating.setCompanyId(company.getId());
		
		model.addAttribute("companyRating", companyRating);
		model.addAttribute("rating", newRating);
		
		// System.out.println("company Id = : " + companyId);
		return "add-review";
	}
	
	
	@RequestMapping("/processAddReviewForm")
	public String processAddReviewForm() {
		
		// Capture data from the form
		
		// ******* add the review to NoSQL DB *******
		
		// change return to "company-ratings"
		return "home";
	}
	// process form and redirect to company ratings. 
	
	   @ModelAttribute("starsList")
	   public Map<String, String> getStarsList() {
	      Map<String, String> starsList = new HashMap<String, String>();
	      starsList.put("1", "1");
	      starsList.put("2", "2");
	      starsList.put("3", "3");
	      starsList.put("4", "4");
	      starsList.put("5", "5");
	      return starsList;
	   }
}
