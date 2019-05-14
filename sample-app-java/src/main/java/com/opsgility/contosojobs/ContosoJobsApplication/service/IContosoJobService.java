package com.opsgility.contosojobs.ContosoJobsApplication.service;

import java.util.List;


import com.opsgility.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;

public interface IContosoJobService {
	
	public List<JobPostingInfo> getAll();
	
	public List<JobPostingInfo> searchJobs(String searchString);
	
	public JobPostingInfo getJobInfoById(String id);
	
	public void save(JobPostingInfo jobInfo);
	
	public void deleteJobById(int id);
	
	public List<JobPostingInfo> searchJobsByCompanyName(String searchString);
	

}
