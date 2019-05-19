package com.sample.contosojobs.ContosoJobsApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
//	@Autowired
	//SecurityContextHolderAwareRequestWrapper authorize;
	
	@GetMapping("showLogin")
	public String ShowLogin(Model model) {
		model.addAttribute("title", "Login");
		
		// check if logged in then redirect to their profile page.
	//	boolean isAdmin = authorize.isUserInRole("ROLE_ADMINISTRATOR");
	//	boolean isMember = authorize.isUserInRole("ROLE_MEMBER");

		/*if(isAdmin) {
			return "admin";
		}
		else if (isMember) {
			return "member";	
		}
		*/
		return "login";
	}
}
