package com.sample.contosojobs.ContosoJobsApplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.service.IContosoJobService;
import com.sample.contosojobs.ContosoJobsApplication.service.IMemberUserService;

@Controller
@RequestMapping("/")
public class HomeController {

	private IContosoJobService _jobInfoService;
	private IMemberUserService _memberUserService;
	public HomeController(IContosoJobService jobInfoService,IMemberUserService memberUserService) {
		_jobInfoService = jobInfoService;
		_memberUserService = memberUserService;
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
    		jobsList.setSource(jobs);
    		jobsList.setPageSize(15);
            request.getSession().setAttribute("jobsList", jobsList);
        }else if(page.equals("prev")) {
        	jobsList = (PagedListHolder<JobPostingInfo>)request.getSession().getAttribute("jobsList");
        	jobsList.previousPage();
        }else if(page.equals("next")) {
        	jobsList = (PagedListHolder<JobPostingInfo>)request.getSession().getAttribute("jobsList");
        	jobsList.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            jobsList = (PagedListHolder<JobPostingInfo>)request.getSession().getAttribute("jobsList");
             jobsList.setPage(pageNum - 1);
        }
        
        mv.setViewName("home");
        return mv;

	}

	@RequestMapping("/Apply")
	public String Apply(HttpServletRequest request) {

	   String jobId = request.getParameter("jobId");

	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   String currentUser = auth.getName();

	   String Id = "";
	   try {
		   Id = _memberUserService.GetUserIdByUserName(currentUser);
	   }catch (Exception e) {
		   System.out.println("ID Exception: " + e.getMessage());
	   }finally {
		   System.out.println("User Id is: " + Id);
	   }
	   
	   _memberUserService.ApplyForJob(jobId, Id);
	   
		return "login";
	}
}
