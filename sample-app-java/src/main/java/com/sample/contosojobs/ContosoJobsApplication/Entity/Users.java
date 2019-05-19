package com.sample.contosojobs.ContosoJobsApplication.Entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserID")
	private String UserID;
	
	@Column(name="Username")
	private String Username;
	
	@Column(name="CompanyId")
	private String CompanyId;
	
	@Column(name="CompanyName")
	private String CompanyName;
	
	@Column(name="UserRole")
	private String UserRole;
	
	public Users() {
		
	}
	
	public Users(String userID, String username, String companyId, String companyName, String userRole) {
		super();
		UserID = userID;
		Username = username;
		CompanyId = companyId;
		CompanyName = companyName;
		UserRole = userRole;
	}
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}

	public String getUserRole() {
		return UserRole;
	}
	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	@Override
	public String toString() {
		return "Users [UserID=" + UserID + ", Username=" + Username + ", CompanyId=" + CompanyId + ", CompanyName="
				+ CompanyName + ", UserRole=" + UserRole + "]";
	}

	
	
}
