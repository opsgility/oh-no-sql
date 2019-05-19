package com.sample.contosojobs.ContosoJobsApplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.MemberViewModel;
import com.sample.contosojobs.ContosoJobsApplication.Entity.RegisterCompanyViewModel;
import com.sample.contosojobs.ContosoJobsApplication.Entity.Resume;
import com.sample.contosojobs.ContosoJobsApplication.Entity.SkillInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.sample.contosojobs.ContosoJobsApplication.service.ICompanyService;
import com.sample.contosojobs.ContosoJobsApplication.service.IMemberUserService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private IMemberUserService _memberUserService;
	private ICompanyService _companyService;
	public MemberController(IMemberUserService memberUserService, ICompanyService companyService) {
		_memberUserService = memberUserService;
		_companyService = companyService;
	}

   // get show the form
	@RequestMapping("/registerUser")
	public String Index(Model model) {
		UserInfo userInfo = new UserInfo();
		model.addAttribute("user", userInfo);
		model.addAttribute("title", "Register");
		return "register-member";
	}
		
	//Process the register user form
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("user") UserInfo userInfo) {
		UUID uuid = UUID.randomUUID();
	    String uuidStr = uuid.toString();
		userInfo.setRole("Member");
		userInfo.setId(uuidStr);
		userInfo.setStatus(1);
		int result = 0;
		// todo Add user to the database 
		try {
	      result = _memberUserService.CreateNewUser(userInfo);
		}
		catch(Exception e) {
			// log errors
			System.out.println("The result number is : " + e.getMessage());
		} 
		finally {
			System.out.println("The result number is : " + result);
		}

		return "login";
	}
	
   // get show the register company form
	@RequestMapping("/registerCompany")
	public String RegisterCompany(Model model) {
		RegisterCompanyViewModel companyInfo = new RegisterCompanyViewModel();
		model.addAttribute("company", companyInfo);
		model.addAttribute("title", "Register Company");
		return "register-company";
	}
		
	//Process the register user form
	@RequestMapping("/processCompanyForm")
	public String processCompanyForm(@ModelAttribute("user") RegisterCompanyViewModel companyInfo) {

		UUID uuid = UUID.randomUUID();
	    String uuidStr = uuid.toString();
	    companyInfo.setId(uuidStr);
	    int result = 0;
	    try {
	    	result = _companyService.CreateCompany(companyInfo);
	    }
	    catch(Exception e) {
			// log errors
			System.out.println("The result number is : " + e.getMessage());
		} 
		finally {
			System.out.println("The result number is : " + result);
		}

		return "login";
	}

	@RequestMapping("/profile")
	public String  memberProfile(Model model) {
		List<SkillInfo> allSkills = new ArrayList<SkillInfo>();
		Resume resume = new Resume();
		MemberViewModel vm = new MemberViewModel();
		String userId = GetUserId();
		
		// Get List of Job Applications and paginate them
		try {
			List<JobPostingInfo> jobPostings = _memberUserService.GetJobApplicationsByUser(userId);
			vm.setJobApplications(jobPostings);
		}catch(Exception e) {
			System.out.println("could not get list of job postings: " + e.getMessage());
		}

		// Get List of all skills to choose from
		try {
			 allSkills = _memberUserService.GetSkills();
			 vm.setAllSkills(allSkills);
		}catch(Exception e) {
			System.out.println("could not get list of skills: " + e.getMessage());
		}
		
		// Get list of users Skills 
		try {
			List<SkillInfo> skills = _memberUserService.GetSkillsByUser(userId);
			vm.setSkills(skills);
		}catch(Exception e) {
			System.out.println("could not get list of skills: " + e.getMessage());
		}
		
		// *********  Get Resumes ******* //
		try {
			// TODO get Resumes from NoSQL db 
			
			// add resume to model
		
			resume.setContent("Sample Resume");
			resume.setId("abcdefg-1234567");
			
			
		}catch(Exception e) {
			System.out.println("could not get list of skills: " + e.getMessage());
		}
		
		List<String> allSkillsList = new ArrayList<String>();
		for (SkillInfo item : allSkills) {
			allSkillsList.add(item.getSkill());
		}
		
		model.addAttribute("resume", resume);
		model.addAttribute("skillsList", vm.getAllSkills());
		
		model.addAttribute("viewModel", vm);
		
		return "member-profile";
	}

	/**** add code for writing resume information to NoSql ****/
	@RequestMapping("/addUpdateResume")
	public String processResume(@ModelAttribute("resume") Resume resume) {
		
		// remove tests
		System.out.println("resume content: " + resume.getContent());
		System.out.println("resume ID: " + resume.getId());
		return "home";
	}
	
	@RequestMapping("/addSkills")
	public String processSkills(@ModelAttribute("skillsList") ArrayList<SkillInfo> skills, HttpServletRequest request) {
		
		// remove tests
		for(SkillInfo skill : skills) {
			System.out.println("skills content: " + skill.getId());
		}
		
		
		if (request.getParameter("add") != null) {
		    // Invoke FirstServlet's job here.
			
			
		} else if (request.getParameter("delete") != null) {
		    // Invoke SecondServlet's job here.
		}

		
		return "home";
	}
	/***** Helper Methods *****/
	
	private String GetUserId() {
		// Get logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();
		String _userId = _memberUserService.GetUserIdByUserName(currentUser);

		return _userId;
	}
}
