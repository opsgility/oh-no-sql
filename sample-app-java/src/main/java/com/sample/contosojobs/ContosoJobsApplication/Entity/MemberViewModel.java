package com.sample.contosojobs.ContosoJobsApplication.Entity;

import java.util.List;

public class MemberViewModel {
    private List<JobPostingInfo> JobApplications;
    private List<SkillInfo> Skills;
    private List<SkillInfo> AllSkills;
    private Resume MyResume;
	public List<JobPostingInfo> getJobApplications() {
		return JobApplications;
	}
	public void setJobApplications(List<JobPostingInfo> jobApplications) {
		JobApplications = jobApplications;
	}
	public List<SkillInfo> getSkills() {
		return Skills;
	}
	public void setSkills(List<SkillInfo> skills) {
		Skills = skills;
	}
	public Resume getMyResume() {
		return MyResume;
	}
	public void setMyResume(Resume myResume) {
		MyResume = myResume;
	}
	
	public List<SkillInfo> getAllSkills() {
		return AllSkills;
	}
	public void setAllSkills(List<SkillInfo> allSkills) {
		AllSkills = allSkills;
	}
	@Override
	public String toString() {
		return "MemberViewModel [JobApplications=" + JobApplications + ", Skills=" + Skills + ", AllSkills=" + AllSkills
				+ ", MyResume=" + MyResume + "]";
	}
    
    
}
