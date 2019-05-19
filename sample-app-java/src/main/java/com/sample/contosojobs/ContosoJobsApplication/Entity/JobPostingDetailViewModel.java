package com.sample.contosojobs.ContosoJobsApplication.Entity;

import java.util.List;

public class JobPostingDetailViewModel {

    private JobPostingInfo Job;
    private List<ApplicationUser> Applicants;
    private CompanyRating CompanyRating;
	public JobPostingInfo getJob() {
		return Job;
	}
	public void setJob(JobPostingInfo job) {
		Job = job;
	}
	public List<ApplicationUser> getApplicants() {
		return Applicants;
	}
	public void setApplicants(List<ApplicationUser> applicants) {
		Applicants = applicants;
	}
	public CompanyRating getCompanyRating() {
		return CompanyRating;
	}
	public void setCompanyRating(CompanyRating companyRating) {
		CompanyRating = companyRating;
	}
	@Override
	public String toString() {
		return "JobPostingDetailViewModel [Job=" + Job + ", Applicants=" + Applicants + ", CompanyRating="
				+ CompanyRating + "]";
	}
    
    
}
