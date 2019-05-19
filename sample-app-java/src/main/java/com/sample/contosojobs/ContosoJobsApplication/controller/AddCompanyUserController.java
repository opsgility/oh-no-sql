package com.sample.contosojobs.ContosoJobsApplication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyUserInfo;
import com.sample.contosojobs.ContosoJobsApplication.service.ICompanyService;
import com.sample.contosojobs.ContosoJobsApplication.service.IMemberUserService;

@Controller
@RequestMapping("/AddCompanyUser")
public class AddCompanyUserController {

	private ICompanyService _companyService;
	private IMemberUserService _memberUserService;
	public AddCompanyUserController(ICompanyService companyService,IMemberUserService memberUserService) {
		_companyService = companyService;
		_memberUserService = memberUserService;
	}
	
	@RequestMapping("")
	public String Index(Model model) {
		CompanyUserInfo companyUser = new CompanyUserInfo();
		
		model.addAttribute("companyUser", companyUser);
		model.addAttribute("title", "Add Company User");
		
		return "add-company-user";
	}
	
	@RequestMapping("/AddUser")
	public String Index(@ModelAttribute("companyUser") CompanyUserInfo companyUser, Model model){
		model.addAttribute("title", "Add user");
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
			companyUser.setCompanyId(companyId);
		}
		// Add user to db
		try {
			_companyService.CreateCompanyUser(companyUser);
		}catch (Exception e){
			System.out.println("Could not create company user : " + companyUser.getUserName());
		}
		
		return "add-company-user";
	}
	
	   @ModelAttribute("roleList")
	   public Map<String, String> getRoleList() {
	      Map<String, String> roleList = new HashMap<String, String>();
			roleList.put("Administrator", "Administrator");
			roleList.put("JobViewer", "JobViewer");
			roleList.put("JobPoster", "JobPoster");
	      return roleList;
	   }
}
