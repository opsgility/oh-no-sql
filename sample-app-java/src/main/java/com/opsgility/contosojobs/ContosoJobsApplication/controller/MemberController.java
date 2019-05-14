package com.opsgility.contosojobs.ContosoJobsApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.CompanyInfo;
import com.opsgility.contosojobs.ContosoJobsApplication.Entity.UserInfo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
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

		//String name = model.user.userName;
		System.out.println("the user: " + userInfo.getUserName() +
		" the emails: " + userInfo.getEmail());
		return "member-profile";
	}
	   // get show the register company form
		@RequestMapping("/registerCompany")
		public String RegisterCompany(Model model) {
			CompanyInfo companyInfo = new CompanyInfo();
			model.addAttribute("company", companyInfo);
			model.addAttribute("title", "Register Company");
			return "register-company";
		}
			
		//Process the register user form
		@RequestMapping("/processCompanyForm")
		public String processCompanyForm(@ModelAttribute("user") CompanyInfo companyInfo) {

			//String name = model.user.userName;
			System.out.println("the user: " + companyInfo.getUserName() +
			" the emails: " + companyInfo.getEmail());
			return "company-profile";
		}
	
}
