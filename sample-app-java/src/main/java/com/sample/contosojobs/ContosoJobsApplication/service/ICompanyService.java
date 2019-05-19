package com.sample.contosojobs.ContosoJobsApplication.service;

import java.util.List;

import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyJobInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyUserInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.RegisterCompanyViewModel;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.Users;

public interface ICompanyService {

	 public int CreateCompany(RegisterCompanyViewModel company) throws InterruptedException;
	 public void CreateCompanyUser(CompanyUserInfo company);
	 public String GetCompanyIdByUser(String id);
	 public List<CompanyJobInfo> GetJobPostingsByCompanyId(String companyId);
	public List<Users> GetUsersByCompanyId(String companyId);
	public CompanyInfo getCompanyByCompanyId(String companyId);
}
