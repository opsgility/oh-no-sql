package com.sample.contosojobs.ContosoJobsApplication.Entity;

import java.util.List;


public class JobInfo {

    private String Id; 
    private String CompanyId;
    private String Title;
    private String Description;
    private List<SkillInfo> Skills;
    
	public List<SkillInfo> getSkills() {
		return Skills;
	}
	public void setSkills(List<SkillInfo> skills) {
		Skills = skills;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "JobInfo [Id=" + Id + ", CompanyId=" + CompanyId + ", Title=" + Title + ", Description=" + Description
				+ "]";
	}
    
    
}
