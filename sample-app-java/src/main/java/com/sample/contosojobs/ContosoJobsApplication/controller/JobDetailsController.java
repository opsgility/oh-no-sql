package com.sample.contosojobs.ContosoJobsApplication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sample.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.sample.contosojobs.ContosoJobsApplication.service.IContosoJobService;

@Controller
@RequestMapping("/JobDetails")
public class JobDetailsController {
	private IContosoJobService _jobInfoService;
	
	public JobDetailsController(IContosoJobService jobInfoService) {
		_jobInfoService = jobInfoService;
	}
	
	@RequestMapping("")
	public String JobDetails(HttpServletRequest request, Model model) {
		
		String jobId = request.getParameter("jobId");
		
		if (jobId != null) {
			JobPostingInfo jobPostingInfo = _jobInfoService.getJobInfoById(jobId);
			model.addAttribute("Job",jobPostingInfo);
		}

		model.addAttribute("title", "Job Details");

		
		return "JobDetails";
	}
}
