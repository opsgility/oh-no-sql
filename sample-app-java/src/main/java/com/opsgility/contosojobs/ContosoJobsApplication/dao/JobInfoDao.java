package com.opsgility.contosojobs.ContosoJobsApplication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;



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
						
			List result = query.list();
			 List<JobPostingInfo> result2 = new ArrayList<JobPostingInfo>(); 
			for(int i=0; i<result.size(); i++){
				JobPostingInfo job = (JobPostingInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}

	
	@Override
	@Transactional
	public List<JobPostingInfo> searchJobs(String searchString) {
		
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL SearchJobPosting(:SearchString)}")
				.addEntity(JobPostingInfo.class)
				.setParameter("SearchString", searchString);
						
			List result = query.list();
			 List<JobPostingInfo> result2 = new ArrayList<JobPostingInfo>(); 
			for(int i=0; i<result.size(); i++){
				JobPostingInfo job = (JobPostingInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}

	@Override
	public List<JobPostingInfo> searchJobsByCompanyName(String searchString) {

		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL SearchJobPostingByCompany(:SearchString)}")
				.addEntity(JobPostingInfo.class)
				.setParameter("SearchString", searchString);
						
			List result = query.list();
			 List<JobPostingInfo> result2 = new ArrayList<JobPostingInfo>(); 
			for(int i=0; i<result.size(); i++){
				JobPostingInfo job = (JobPostingInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}


}
