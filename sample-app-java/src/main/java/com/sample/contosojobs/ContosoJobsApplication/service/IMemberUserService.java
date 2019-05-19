package com.sample.contosojobs.ContosoJobsApplication.service;

import java.util.List;

import com.sample.contosojobs.ContosoJobsApplication.Entity.ApplicationUser;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;

public interface IMemberUserService {

	public int CreateNewUser(UserInfo user);

	public String GetUserIdByUserName(String currentUser);

	public void ApplyForJob(String jobId, String id);

	public List<JobPostingInfo> GetJobApplicationsByUser(String userId);
	public List<SkillInfo> GetSkillsByUser(String userId);

	public List<SkillInfo> GetSkills();

	public List<ApplicationUser> GetJobApplicantsbyJobId(String jobPostingId);
}
