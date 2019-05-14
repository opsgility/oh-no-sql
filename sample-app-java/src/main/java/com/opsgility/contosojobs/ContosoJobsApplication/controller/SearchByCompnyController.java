package com.opsgility.contosojobs.ContosoJobsApplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.opsgility.contosojobs.ContosoJobsApplication.service.IContosoJobService;

@Controller
@RequestMapping("/")
public class SearchByCompnyController {

	private IContosoJobService _jobInfoService;
	
	public SearchByCompnyController(IContosoJobService jobInfoService) {
		_jobInfoService = jobInfoService;
	}
	
@SuppressWarnings("unchecked")
@RequestMapping("SearchByCompany")
public ModelAndView Index(@PathVariable(required=false, name="page") String page, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		  String companyName = request.getParameter("SearchByCompany");
		  String companySearch = request.getParameter("CompanySearch");
		  String searchString = request.getParameter("Search");
		  List<JobPostingInfo> jobs = new ArrayList<JobPostingInfo>();
		  
		  if (searchString != null && !companySearch.isEmpty()) {
			  // Acquire the list and search inside it
			
			  List<JobPostingInfo> post = _jobInfoService.searchJobsByCompanyName(companySearch);
			  Stream<JobPostingInfo> filteredList =  post.stream().filter(x->x.getJobTitle().contains(searchString) || x.getJobDescription().contains(searchString));
		      jobs = filteredList.collect(Collectors.toList());
			
		  }
		  
		  else if(companyName != null && !companyName.isEmpty())
		  {
			  request.getSession().setAttribute("companyName", companyName);
			  jobs = _jobInfoService.searchJobsByCompanyName(companyName);
		  }
	
	PagedListHolder<JobPostingInfo> jobsList;
        if(page == null) {
    		jobsList = new PagedListHolder<JobPostingInfo>();
    		
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
        
        mv.setViewName("SearchByCompany");
        return mv;

	}
}
