package com.sample.contosojobs.ContosoJobsApplication.Entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyJobInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="JobPostingID")
    private String JobPostingID;
	
	@Column(name="CompanyID")
    private String CompanyID;
	
	@Column(name="JobTitle")
    private String JobTitle;
	
	@Column(name="CompanyName")
    private String CompanyName;
	
	@Column(name="City")
    private String City;
	
	@Column(name="State")
    private String State;
	
	@Column(name="JobDescription")
    private String JobDescription;
    
	@Column(name="JobCreationDate")
    private OffsetDateTime CreatedOnDate;
	

	public CompanyJobInfo() {
		
	}


	public CompanyJobInfo(String jobPostingID, String companyID, String jobTitle, String companyName, String city,
			String state, String jobDescription, OffsetDateTime createdOnDate) {
		super();
		JobPostingID = jobPostingID;
		CompanyID = companyID;
		JobTitle = jobTitle;
		CompanyName = companyName;
		City = city;
		State = state;
		JobDescription = jobDescription;
		CreatedOnDate = createdOnDate;
	}


	public String getJobPostingID() {
		return JobPostingID;
	}

	public void setJobPostingID(String id) {
		JobPostingID = id;
	}

	public String getCompanyID() {
		return CompanyID;
	}

	public void setCompanyID(String companyId) {
		CompanyID = companyId;
	}

	public String getJobTitle() {
		return JobTitle;
	}

	public void setJobTitle(String title) {
		JobTitle = title;
	}

	public String getJobDescription() {
		return JobDescription;
	}

	public void setJobDescription(String description) {
		JobDescription = description;
	}

	public OffsetDateTime getCreatedOnDate() {
		return CreatedOnDate;
	}

	public void setCreatedOnDate(OffsetDateTime createdOnDate) {
		CreatedOnDate = createdOnDate;
	}


	public String getCompanyName() {
		return CompanyName;
	}


	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}



	public String getCity() {
		return City;
	}


	public void setCity(String city) {
		City = city;
	}


	public String getState() {
		return State;
	}


	public void setState(String state) {
		State = state;
	}


	@Override
	public String toString() {
		return "CompanyJobInfo [JobPostingID=" + JobPostingID + ", CompanyID=" + CompanyID + ", JobTitle=" + JobTitle
				+ ", CompanyName=" + CompanyName + ", City=" + City + ", State=" + State + ", JobDescription="
				+ JobDescription + ", CreatedOnDate=" + CreatedOnDate + "]";
	}


}
