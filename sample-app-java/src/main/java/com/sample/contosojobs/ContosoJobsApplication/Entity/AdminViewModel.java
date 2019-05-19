package com.sample.contosojobs.ContosoJobsApplication.Entity;

import java.util.List;

public class AdminViewModel {

    private List<CompanyUserInfo> Users;
    private List<JobPostingInfo> JobPostings;
    private int AverageRating;
	public List<CompanyUserInfo> getUsers() {
		return Users;
	}
	public void setUsers(List<CompanyUserInfo> users) {
		Users = users;
	}
	public List<JobPostingInfo> getJobPostings() {
		return JobPostings;
	}
	public void setJobPostings(List<JobPostingInfo> jobPostings) {
		JobPostings = jobPostings;
	}
	public int getAverageRating() {
		return AverageRating;
	}
	public void setAverageRating(int averageRating) {
		AverageRating = averageRating;
	}
	@Override
	public String toString() {
		return "AdminViewModel [Users=" + Users + ", JobPostings=" + JobPostings + ", AverageRating=" + AverageRating
				+ "]";
	}
    
    
}
