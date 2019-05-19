package com.sample.contosojobs.ContosoJobsApplication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyJobInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.RegisterCompanyViewModel;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.Users;

@Repository
public class CompanyInfoDao implements ICompanyInfoDao {

	private EntityManager _entityManager;
	public CompanyInfoDao(EntityManager entityManager) {
		_entityManager = entityManager;
	}
	@Override
	@Transactional
	public int CreateCompany(RegisterCompanyViewModel company) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL NewCompany(:Id,:Name,:City,:State)}")
				.setParameter("Id", company.getId())
				.setParameter("Name", company.getName())
				.setParameter("City", company.getCity())
				.setParameter("State", company.getState());
		int result = query.executeUpdate();
		return result;
	}
	
	@Override
	@Transactional
	public int AddComanyUser(String companyId, String userId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL NewCompanyUser(:UserId,:CompanyId)}")
				.setParameter("UserId", userId)
				.setParameter("CompanyId", companyId);
		int result = query.executeUpdate();
		return result;
		
	}
	@Override
	public String GetCompanyIdByUserId(String userId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery("SELECT u.CompanyID from CompanyUsers u where u.UserID = :id");
		query.setParameter("id", userId);
		Object result = query.getSingleResult();
		String id = result.toString();
		return id;
	}
	@Override
	@Transactional
	public List<CompanyJobInfo> GetJobPostingsByCompanyId(String companyId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL JavaGetJobPostingsByCompanyId(:CompanyId)}")
				.addEntity(CompanyJobInfo.class)
				.setParameter("CompanyId", companyId);
						
			List<CompanyJobInfo> result = query.list();
			 List<CompanyJobInfo> result2 = new ArrayList<CompanyJobInfo>(); 
			for(int i=0; i<result.size(); i++){
				CompanyJobInfo job = (CompanyJobInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}
	@Override
	@Transactional
	public List<Users> GetUsersByCompanyId(String companyId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetCompanyUsersByCompanyId(:CompanyId)}")
				.addEntity(Users.class)
				.setParameter("CompanyId", companyId);
						
			List<Users> result = query.list();
			 List<Users> result2 = new ArrayList<Users>(); 
			for(int i=0; i<result.size(); i++){
				Users job = (Users)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}
	@Override
	public CompanyInfo GetCompanyInfoByCompanyId(String companyId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetCompany(:Id)}")
				.addEntity(CompanyInfo.class)
				.setParameter("Id", companyId);
						
		CompanyInfo result = (CompanyInfo) query.getSingleResult();
					
		return result;
	}

}
