package com.sample.contosojobs.ContosoJobsApplication.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;
import com.sample.contosojobs.ContosoJobsApplication.dao.IJobInfoDao;


@Service
public class ContosoJobService implements IContosoJobService {

	private IJobInfoDao _jobInfoDao;

	public ContosoJobService(IJobInfoDao jobInfoDao) {
		_jobInfoDao = jobInfoDao;
	}
	@Override
	public List<JobPostingInfo> getAll() {
		
		return _jobInfoDao.getAll();
	}

	@Override
	public JobPostingInfo getJobInfoById(String id) {
		 List<JobPostingInfo> JobInfos = _jobInfoDao.getJobByJobId(id);
		 return JobInfos.get(0);
	}

	@Override
	public void save(JobPostingInfo jobInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteJobById(int id) {
		// TODO Auto-generated method stub

	}
	@Override
	public List<JobPostingInfo> searchJobs(String searchString) {
		return _jobInfoDao.searchJobs(searchString);
	}
	
	@Override
	public List<JobPostingInfo> searchJobsByCompanyName(String searchString) {
		return _jobInfoDao.searchJobsByCompanyName(searchString);
	}
	@Override
	public List<SkillInfo> GetSkills() {
		return _jobInfoDao.GetSkills();
	}
	@Override
	public JobPostingInfo GetJobByJobId(String jobPostingId) {
		JobPostingInfo jobInfo = _jobInfoDao.GetJobByJobId(jobPostingId);
		// TODO *************change this to NoSql call *******************
		return jobInfo;
	}

}
