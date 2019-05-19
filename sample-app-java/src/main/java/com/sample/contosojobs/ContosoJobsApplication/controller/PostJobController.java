package com.sample.contosojobs.ContosoJobsApplication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.contosojobs.ContosoJobsApplication.Entity.JobInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;
import com.sample.contosojobs.ContosoJobsApplication.service.ICompanyService;
import com.sample.contosojobs.ContosoJobsApplication.service.IContosoJobService;
import com.sample.contosojobs.ContosoJobsApplication.service.IMemberUserService;

@Controller
@RequestMapping("/PostJob")
public class PostJobController {
	
	private ICompanyService _companyService;
	private IMemberUserService _memberUserService;
	private IContosoJobService _contosoJobService;
	public PostJobController(ICompanyService companyService, IMemberUserService memberUserService
			,IContosoJobService contosoJobService) {
		_companyService = companyService;
		_memberUserService = memberUserService;
		_contosoJobService = contosoJobService;
	}
	
	@RequestMapping("")
	public String Index(Model model) {
		// Get list of Skills
		JobInfo jobPost = new JobInfo();

		
		model.addAttribute("jobPost", jobPost);
	
		model.addAttribute("title", "Post Job");
		
		return "post-job";
	}
	
	@RequestMapping("/PostNewJob")
	public String Index(@ModelAttribute("jobPost") JobInfo JobInfo){
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		   String currentUser = auth.getName();
		   String companyId = "";
		try {
			  String Id = _memberUserService.GetUserIdByUserName(currentUser);
			  companyId = _companyService.GetCompanyIdByUser(Id);
			  System.out.println("Company Id : " + companyId);
		}catch (Exception e) {
			System.out.println("Could not get company Id by user Id : " + e.getMessage());
		}finally {
			
		}
		// Add Job posting to db
		try {
			//_companyService.CreateCompanyUser(companyUser);
		}catch (Exception e){
			System.out.println("Could not create Job Post for Company : " + companyId);
		}
		
		return "add-company-user";
	}
	
	   @ModelAttribute("skillsList")
	   public Map<String, String> getSkillsList() {
			List<SkillInfo> skills = new ArrayList<SkillInfo>();
			try {
				skills = _contosoJobService.GetSkills();
				for (SkillInfo skill: skills) {
					System.out.println(skill.getId());
					System.out.println(skill.getSkill());
				}
				
			}catch(Exception e) {
				System.out.println("Could not get skills list from database : " + e.getMessage());
			}
	      Map<String, String> skillList = new HashMap<String, String>();
	      for (SkillInfo skill: skills){
	    	  skillList.put(skill.getId(), skill.getSkill());
	      }
	      return skillList;
	      
	   }

}
