package com.sample.contosojobs.ContosoJobsApplication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;



@Repository
public class JobInfoDao implements IJobInfoDao {

	private EntityManager _entityManager;
	
	@Autowired
	public JobInfoDao(EntityManager entityManager) {
		_entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public List<JobPostingInfo> getAll() {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query<JobPostingInfo> jobQuery = currentSession.createQuery("from JobPostings", JobPostingInfo.class );
		List<JobPostingInfo> jobInfo = jobQuery.getResultList();
		
		return jobInfo;
	}

	@Override
	@Transactional
	public List<JobPostingInfo> getJobByJobId(String id) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetJobPosting(:Id)}")
				.addEntity(JobPostingInfo.class)
				.setParameter("Id", id);
						
			List<JobPostingInfo> result = query.list();
			 List<JobPostingInfo> result2 = new ArrayList<JobPostingInfo>(); 
			for(int i=0; i<result.size(); i++){
				JobPostingInfo job = (JobPostingInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<JobPostingInfo> searchJobs(String searchString) {
		
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL SearchJobPosting(:SearchString)}")
				.addEntity(JobPostingInfo.class)
				.setParameter("SearchString", searchString);
						
		List<JobPostingInfo> result = query.list();
			 List<JobPostingInfo> result2 = new ArrayList<JobPostingInfo>(); 
			for(int i=0; i<result.size(); i++){
				JobPostingInfo job = (JobPostingInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<JobPostingInfo> searchJobsByCompanyName(String searchString) {

		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL SearchJobPostingByCompany(:SearchString)}")
				.addEntity(JobPostingInfo.class)
				.setParameter("SearchString", searchString);
						
		 List<JobPostingInfo> result = query.list();
			 List<JobPostingInfo> result2 = new ArrayList<JobPostingInfo>(); 
			for(int i=0; i<result.size(); i++){
				JobPostingInfo job = (JobPostingInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}

	@Override
	@Transactional
	public List<SkillInfo> GetSkills() {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetSkills(:GetAll)}")
				.addEntity(SkillInfo.class)
				.setParameter("GetAll", "GetAll");
						
		List<SkillInfo> result = query.list();
			 List<SkillInfo> result2 = new ArrayList<SkillInfo>(); 
			for(int i=0; i<result.size(); i++){
				SkillInfo skill = (SkillInfo)result.get(i);
				result2.add(skill);
			}
						
		return result2;
		
	}

	@Override
	public JobPostingInfo GetJobByJobId(String jobPostingId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetJobPosting(:Id)}")
				.addEntity(JobPostingInfo.class)
				.setParameter("Id",jobPostingId);
						
		JobPostingInfo result = (JobPostingInfo) query.getSingleResult();

						
		return result;
	}


}
