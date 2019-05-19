package com.sample.contosojobs.ContosoJobsApplication.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sample.contosojobs.ContosoJobsApplication.Entity.ApplicationUser;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.sample.contosojobs.ContosoJobsApplication.dao.IMemberInfoDao;

@Service
public class MemberUserService implements IMemberUserService {
	
	private IMemberInfoDao _memberDao;
	public MemberUserService(IMemberInfoDao memberDao) {
		_memberDao = memberDao;
	}
	
	@Override
	@Async
	public int CreateNewUser(UserInfo user) {
		int result = _memberDao.CreateUser(user);
		
		int roleresult = AddUserToRole(user);
		return result;
	}
	
	@Async
	private int AddUserToRole(UserInfo user) {
		int result =_memberDao.AddUserToRole(user);
		return result;
	}

	@Override
	public String GetUserIdByUserName(String currentUser) {
		String userId = _memberDao.GetUserIdByUserName(currentUser);
		return userId;
	}

	@Override
	public void ApplyForJob(String jobId, String id) {
		// TODO Auto-generated method stub
		_memberDao.ApplyForJpb(jobId, id);
	}

	@Override
	public List<JobPostingInfo> GetJobApplicationsByUser(String userId) {
		List<JobPostingInfo> jobsList = _memberDao.GetJobApplicationsByUser(userId);
		return jobsList;
	}

	@Override
	public List<SkillInfo> GetSkillsByUser(String userId) {
		List<SkillInfo> skills = _memberDao.GetSkillsByUser(userId);
		return skills;
	}

	@Override
	public List<SkillInfo> GetSkills() {
		List<SkillInfo> skills = _memberDao.GetSkills();
		return skills;
	}

	@Override
	public List<ApplicationUser> GetJobApplicantsbyJobId(String jobPostingId) {
		List<ApplicationUser> applicants = _memberDao.GetJobApplicantsbyJobId(jobPostingId);
		return applicants;
	}

}
