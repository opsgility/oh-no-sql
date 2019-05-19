package com.sample.contosojobs.ContosoJobsApplication.service;

import java.util.List;


import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;

public interface IContosoJobService {
	
	public List<JobPostingInfo> getAll();
	
	public List<JobPostingInfo> searchJobs(String searchString);
	
	public JobPostingInfo getJobInfoById(String id);
	
	public void save(JobPostingInfo jobInfo);
	
	public void deleteJobById(int id);
	
	public List<JobPostingInfo> searchJobsByCompanyName(String searchString);

	public List<SkillInfo> GetSkills();

	public JobPostingInfo GetJobByJobId(String jobPostingId);
	

}
