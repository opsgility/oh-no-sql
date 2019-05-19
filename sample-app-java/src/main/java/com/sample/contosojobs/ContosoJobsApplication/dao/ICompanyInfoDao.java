package com.sample.contosojobs.ContosoJobsApplication.dao;

import java.util.List;

import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyJobInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.RegisterCompanyViewModel;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.Users;

public interface ICompanyInfoDao {

	public int CreateCompany(RegisterCompanyViewModel company);
	public int AddComanyUser(String companyId, String userId);
	public String GetCompanyIdByUserId(String id);
	public List<CompanyJobInfo> GetJobPostingsByCompanyId(String companyId);
	public List<Users> GetUsersByCompanyId(String companyId);
	public CompanyInfo GetCompanyInfoByCompanyId(String companyId);
}
