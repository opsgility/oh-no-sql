package com.sample.contosojobs.ContosoJobsApplication.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.contosojobs.ContosoJobsApplication.Entity.ApplicationUser;
import com.sample.contosojobs.ContosoJobsApplication.Entity.CompanyJobInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingDetailViewModel;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.UserInfo;
import com.sample.contosojobs.ContosoJobsApplication.Entity.Users;
import com.sample.contosojobs.ContosoJobsApplication.service.ICompanyService;
import com.sample.contosojobs.ContosoJobsApplication.service.IContosoJobService;
import com.sample.contosojobs.ContosoJobsApplication.service.IMemberUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private IContosoJobService _jobInfoService;
	private IMemberUserService _memberUserService;
	private ICompanyService _companyService;
	
	public AdminController(IContosoJobService jobInfoService,IMemberUserService memberUserService
			,ICompanyService companyService) {
		_jobInfoService = jobInfoService;
		_memberUserService = memberUserService;
		_companyService = companyService;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"","/", "/{page}"})
	public ModelAndView Index(@PathVariable(required=false, name="page") String page, HttpServletRequest request, HttpServletResponse response) {
		 
		ModelAndView mv = new ModelAndView();
		
		request.getSession().setAttribute("title", "Admin");
		String _userId = GetUserId();
		String companyId = _companyService.GetCompanyIdByUser(_userId);
		PagedListHolder<CompanyJobInfo> jobsList;
		
		// Get users and add to ViewModel
		List<Users> users = _companyService.GetUsersByCompanyId(companyId);
		Users user;
		if(users != null) {
			user = users.get(0);
			request.getSession().setAttribute("companyName", user.getCompanyName());
		}
		 request.getSession().setAttribute("users", users);
		 
        if(page == null) {
    		jobsList = new PagedListHolder<CompanyJobInfo>();
    		List<CompanyJobInfo> jobs = _companyService.GetJobPostingsByCompanyId(companyId);
    		jobsList.setSource(jobs);
    		jobsList.setPageSize(6);
            request.getSession().setAttribute("jobsList", jobsList);
        }else if(page.equals("prev")) {
        	jobsList = (PagedListHolder<CompanyJobInfo>)request.getSession().getAttribute("jobsList");
        	jobsList.previousPage();
        }else if(page.equals("next")) {
        	jobsList = (PagedListHolder<CompanyJobInfo>)request.getSession().getAttribute("jobsList");
        	jobsList.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            jobsList = (PagedListHolder<CompanyJobInfo>)request.getSession().getAttribute("jobsList");
             jobsList.setPage(pageNum - 1);
        }
        
        mv.setViewName("admin");
        return mv;
	}
	
	@RequestMapping("/postingDetails")
	public String JobPostingDetails(HttpServletRequest request, Model model) {
		JobPostingDetailViewModel jobPosting = new JobPostingDetailViewModel();
		String jobPostingId = request.getParameter("jobId");
		

        JobPostingInfo jobInfo =  _jobInfoService.GetJobByJobId(jobPostingId);
        List<ApplicationUser> applicants = _memberUserService.GetJobApplicantsbyJobId(jobPostingId);
        jobPosting.setJob(jobInfo);
        jobPosting.setApplicants(applicants);
        
        model.addAttribute("applicants", jobPosting.getApplicants());
        model.addAttribute("jobInfo", jobPosting.getJob());
		
		return "jobposting-details";
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
