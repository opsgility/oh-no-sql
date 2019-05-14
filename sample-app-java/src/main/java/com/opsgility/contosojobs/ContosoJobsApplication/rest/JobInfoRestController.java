package com.opsgility.contosojobs.ContosoJobsApplication.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opsgility.contosojobs.ContosoJobsApplication.Entity.JobPostingInfo;

import com.opsgility.contosojobs.ContosoJobsApplication.dao.IJobInfoDao;
import com.opsgility.contosojobs.ContosoJobsApplication.service.IContosoJobService;

@RestController
@RequestMapping("/api")
public class JobInfoRestController {
	private IContosoJobService _jobInfoService;
	
	@Autowired
	public JobInfoRestController(IContosoJobService jobInfoService) {
		_jobInfoService = jobInfoService;
	}
	@GetMapping("/jobs")
	public List<JobPostingInfo> getAll() {
		return _jobInfoService.getAll();
	}
	
	@GetMapping("/searchJobs")
	public List<JobPostingInfo> searchAll() {
		return _jobInfoService.searchJobs("data");
	}
}
