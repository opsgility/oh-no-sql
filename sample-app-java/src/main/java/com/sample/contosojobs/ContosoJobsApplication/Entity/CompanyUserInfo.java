package com.sample.contosojobs.ContosoJobsApplication.Entity;

public class CompanyUserInfo {

	private String UserId;
	private String CompanyId;
	private String UserName;
	private String UserRole;
	private String Email;
	private String password;
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserRole() {
		return UserRole;
	}
	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "CompanyUserInfo [UserId=" + UserId + ", CompanyId=" + CompanyId + ", UserName=" + UserName
				+ ", UserRole=" + UserRole + ", Email=" + Email + ", password=" + password + "]";
	}
	
}
