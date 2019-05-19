package com.sample.contosojobs.ContosoJobsApplication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.contosojobs.ContosoJobsApplication.Entity.ApplicationUser;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;

@Repository
public class MemberInfoDao implements IMemberInfoDao {

	private EntityManager _entityManager;
	
    @Autowired
	public MemberInfoDao(EntityManager entityManager) {
		_entityManager = entityManager;
	}

	@Override
	@Transactional
	public int  AddUserToRole(UserInfo user) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL NewUserRole(:UserID,:RoleName)}")
				.setParameter("UserID", user.getId())
				.setParameter("RoleName", user.getRole());
        int reuslt1 = query.getFirstResult();
		int result = query.executeUpdate();
		return result; 
	
			}

	@Override
	@Transactional
	public int CreateUser(UserInfo user) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL NewUser(:ID,:UserName,:Email,:Password,:Status)}")
				.setParameter("ID", user.getId())
				.setParameter("UserName", user.getUserName())
				.setParameter("Email", user.getEmail())
				.setParameter("Password", user.getPassword())
				.setParameter("Status", 1);
		int result = query.executeUpdate();
		return result; 
	}

	@Override
	public String GetUserIdByUserName(String currentUser) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery("SELECT u.Id from Users u where u.Username = :id");
		query.setParameter("id", currentUser);
		Object result = query.getSingleResult();
		String id = result.toString();
		return id; 
	}

	@Override
	@Transactional
	public void ApplyForJpb(String jobId, String id) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL ApplyToJob(:UserId,:JobId)}")
				.setParameter("UserId", id)
				.setParameter("JobId", jobId);

		int result = query.executeUpdate();
	
	}

	@Override
	@Transactional
	public List<JobPostingInfo> GetJobApplicationsByUser(String userId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetJobApplicationsByUser(:UserID)}")
				.addEntity(JobPostingInfo.class)
				.setParameter("UserID", userId);
						
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
	public List<SkillInfo> GetSkillsByUser(String userId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetSkillsByUserId(:UserId)}")
				.addEntity(SkillInfo.class)
				.setParameter("UserId", userId);
						
		List<SkillInfo> result = query.list();
			 List<SkillInfo> result2 = new ArrayList<SkillInfo>(); 
			for(int i=0; i<result.size(); i++){
				SkillInfo job = (SkillInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}

	@Override
	public List<SkillInfo> GetSkills() {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetSkills(:GetAll)}")
				.addEntity(SkillInfo.class)
				.setParameter("GetAll", "GetAll");
						
		List<SkillInfo> result = query.list();
			 List<SkillInfo> result2 = new ArrayList<SkillInfo>(); 
			for(int i=0; i<result.size(); i++){
				SkillInfo job = (SkillInfo)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}

	@Override
	public List<ApplicationUser> GetJobApplicantsbyJobId(String jobPostingId) {
		Session currentSession = _entityManager.unwrap(Session.class);
		Query query = currentSession.createSQLQuery(
				"{CALL GetJobApplicationsByJobId(:JobPostingID)}")
				.addEntity(ApplicationUser.class)
				.setParameter("JobPostingID", jobPostingId);
						
		List<ApplicationUser> result = query.list();
			 List<ApplicationUser> result2 = new ArrayList<ApplicationUser>(); 
			for(int i=0; i<result.size(); i++){
				ApplicationUser job = (ApplicationUser)result.get(i);
				result2.add(job);
			}
						
		return result2;
	}
}
