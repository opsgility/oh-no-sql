package com.sample.contosojobs.ContosoJobsApplication.Entity;

import java.util.List;

public class CompanyRating {

    
    private String Id;

    
    private String CompanyId;

   
    private String CompanyName;

    
    private String Key;

    
    private List<Rating> Ratings;


	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}


	public String getCompanyId() {
		return CompanyId;
	}


	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}


	public String getCompanyName() {
		return CompanyName;
	}


	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}


	public String getKey() {
		return Key;
	}


	public void setKey(String key) {
		Key = key;
	}


	public List<Rating> getRatings() {
		return Ratings;
	}


	public void setRatings(List<Rating> ratings) {
		Ratings = ratings;
	}


	@Override
	public String toString() {
		return "CompanyRating [Id=" + Id + ", CompanyId=" + CompanyId + ", CompanyName=" + CompanyName + ", Key=" + Key
				+ ", Ratings=" + Ratings + "]";
	}
    
    
}
