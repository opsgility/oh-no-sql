package com.sample.contosojobs.ContosoJobsApplication.service;

import java.util.List;
import java.util.UUID;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyJobInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyUserInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.RegisterCompanyViewModel;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.Users;
import com.sample.contosojobs.ContosoJobsApplication.dao.ICompanyInfoDao;

@Service
public class CompanyService implements ICompanyService {

	private ICompanyInfoDao _companyDao;
	private IMemberUserService _memberUserService;
	public CompanyService(ICompanyInfoDao companyDao, IMemberUserService memberUserService) {
		_companyDao = companyDao;
		_memberUserService = memberUserService;
	}
	
	@Override
	public int CreateCompany(RegisterCompanyViewModel company) {
		CreateAdminUser(company);
		int result =  _companyDao.CreateCompany(company);
		return result;
	}
	
	@Async
	private void CreateAdminUser(RegisterCompanyViewModel company) {
		UserInfo user = new UserInfo();
		UUID uuid = UUID.randomUUID();
	    String uuidStr = uuid.toString();
	    
		user.setUserName(company.getName());
		user.setId(uuidStr);
		user.setEmail(company.getEmail());
		user.setPassword(company.getPassword());
		user.setRole("Administrator");
		user.setStatus(1);
          AddCompanyUser(company.getId(), user.getId());
		_memberUserService.CreateNewUser(user);
	}
	
	@Async
	public void CreateCompanyUser(CompanyUserInfo companyUser) {
		UserInfo user = new UserInfo();
		CompanyInfo company = new CompanyInfo();
		UUID uuid = UUID.randomUUID();
	    String uuidStr = uuid.toString();
	    
		user.setUserName(companyUser.getUserName());
		user.setId(uuidStr);
		user.setEmail(companyUser.getEmail());
		user.setPassword(companyUser.getPassword());
		user.setRole(companyUser.getUserRole());
		user.setStatus(1);
		
          AddCompanyUser(companyUser.getCompanyId(), user.getId());
		_memberUserService.CreateNewUser(user);
	}
	
	@Async
	private void AddCompanyUser(String companyId, String userId) {
		
		_companyDao.AddComanyUser(companyId, userId);
	}

	@Override
	public String GetCompanyIdByUser(String id) {
		String companyId = _companyDao.GetCompanyIdByUserId(id);
		return companyId;		
	}


	@Override
	public List<CompanyJobInfo> GetJobPostingsByCompanyId(String companyId) {
		List<CompanyJobInfo> jobs = _companyDao.GetJobPostingsByCompanyId(companyId);
		return jobs;
	}

	@Override
	public List<Users> GetUsersByCompanyId(String companyId) {
		 List<Users> users = _companyDao.GetUsersByCompanyId(companyId);
		return users;
	}

	@Override
	public CompanyInfo getCompanyByCompanyId(String companyId) {
		CompanyInfo company = _companyDao.GetCompanyInfoByCompanyId(companyId);
		return company;
	}

}
