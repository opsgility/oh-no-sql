package com.opsgility.contosojobs.ContosoJobsApplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.opsgility.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.opsgility.contosojobs.ContosoJobsApplication.service.IContosoJobService;

@Controller
@RequestMapping("/")
public class HomeController {

	private IContosoJobService _jobInfoService;
	public HomeController(IContosoJobService jobInfoService) {
		_jobInfoService = jobInfoService;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/", "/home","/home/{page}"}, method = RequestMethod.GET)
	public ModelAndView Index(@PathVariable(required=false, name="page") String page, HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		request.getSession().setAttribute("title", "Home");
		
		  String searchString = request.getParameter("Search");
		  if(searchString == null || searchString.isEmpty())
		  {
			  searchString = "";
		  }
		  
	PagedListHolder<JobPostingInfo> jobsList;
        if(page == null) {
    		jobsList = new PagedListHolder<JobPostingInfo>();
    		List<JobPostingInfo> jobs = _jobInfoService.searchJobs(searchString);
            // Setting the source for PagedListHolder
    		jobsList.setSource(jobs);
    		jobsList.setPageSize(15);
            // Setting PagedListHolder instance to session
            request.getSession().setAttribute("jobsList", jobsList);
        }else if(page.equals("prev")) {
            // get the user list from session
        	jobsList = (PagedListHolder<JobPostingInfo>)request.getSession().getAttribute("jobsList");
            // switch to previous page
        	jobsList.previousPage();
        }else if(page.equals("next")) {
        	jobsList = (PagedListHolder<JobPostingInfo>)request.getSession().getAttribute("jobsList");
            // switch to next page
        	jobsList.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            jobsList = (PagedListHolder<JobPostingInfo>)request.getSession().getAttribute("jobsList");
            // set the current page number
            // page number starts from zero in PagedListHolder that's why subtracting 1
            jobsList.setPage(pageNum - 1);
        }
        
        mv.setViewName("home");
        return mv;

	}

	@RequestMapping("/Apply")
	public String Apply(@AuthenticationPrincipal Principal user) {
		// Name is the User Id
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   // currentUser = (UserInfo) auth.getPrincipal();
	String name = user.toString();
	//	System.out.println("The user name is " + currentUser.getId());
		return "login";
	}
}
