using JobSeeker.Models;
using JobSeeker.Services;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace JobSeeker.Controllers
{
    [Authorize(Roles = "Administrator")]
    public class AdminController : Controller
    {
        // GET: Admin
        private string _userId;
        private ICompanyService _companyService;
        private IJobPostingService _jobPostingService;
        public AdminController(ICompanyService companyService, IJobPostingService jobPostingService)
        {
            _jobPostingService = jobPostingService;
            _companyService = companyService;
        }

        public async Task<ActionResult> Index()
        {
            _userId = User.Identity.GetUserId();
            var companyId = await _companyService.GetCompanyByUserId(_userId);
            AdminViewModel Model = new AdminViewModel();
            // get all the company users and roles
            Model.Users = await _companyService.GetUsersByCompanyId(companyId);
            Model.JobPostings = await _companyService.GetJobPostingsByCompanyId(companyId);

            return View(Model);
        }

    }
}