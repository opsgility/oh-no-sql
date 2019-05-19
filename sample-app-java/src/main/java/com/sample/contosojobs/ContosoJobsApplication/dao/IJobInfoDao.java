package com.sample.contosojobs.ContosoJobsApplication.dao;

import java.util.List;

import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;

public interface IJobInfoDao {
	public List<JobPostingInfo> getAll();

	public List<JobPostingInfo> searchJobs(String searchString);

	public List<JobPostingInfo> searchJobsByCompanyName(String searchString);

	public List<JobPostingInfo> getJobByJobId(String id);

	public List<SkillInfo> GetSkills();

	public JobPostingInfo GetJobByJobId(String jobPostingId);
	
}
