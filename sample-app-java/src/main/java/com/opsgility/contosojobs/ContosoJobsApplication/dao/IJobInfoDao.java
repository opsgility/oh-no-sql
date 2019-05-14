package com.opsgility.contosojobs.ContosoJobsApplication.dao;

import java.util.List;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;

public interface IJobInfoDao {
	public List<JobPostingInfo> getAll();

	public List<JobPostingInfo> searchJobs(String searchString);

	public List<JobPostingInfo> searchJobsByCompanyName(String searchString);

	public List<JobPostingInfo> getJobByJobId(String id);
	
}
