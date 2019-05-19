package com.sample.contosojobs.ContosoJobsApplication.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sample.contosojobs.ContosoJobsApplication.Entity.ApplicationUser;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;

@Repository
public interface IMemberInfoDao {

	public int CreateUser(UserInfo user);
	public int AddUserToRole(UserInfo user);
	public String GetUserIdByUserName(String currentUser);
	public void ApplyForJpb(String jobId, String id);
	public List<JobPostingInfo> GetJobApplicationsByUser(String userId);
	public List<SkillInfo> GetSkillsByUser(String userId);
	public List<SkillInfo> GetSkills();
	public List<ApplicationUser> GetJobApplicantsbyJobId(String jobPostingId);
}
