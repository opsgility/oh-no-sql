package com.opsgility.contosojobs.ContosoJobsApplication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.opsgility.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;
import com.opsgility.contosojobs.ContosoJobsApplication.service.IContosoJobService;

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
		JobPostingInfo jobPostingInfo = _jobInfoService.getJobInfoById(jobId);
		
		model.addAttribute("title", "Job Details");
		model.addAttribute("Job",jobPostingInfo);
		
		return "JobDetails";
	}
}
