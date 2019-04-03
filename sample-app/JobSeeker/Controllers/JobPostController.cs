using IdentityManagement.Entities;
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
    public class JobPostController : Controller
    {
        private string _userId;
        private ICompanyService _companyService;
        private IJobPostingService _jobPostingService;
        public JobPostController(ICompanyService companyService, IJobPostingService jobPostingService)
        {
            _jobPostingService = jobPostingService;
            _companyService = companyService;
        }
        // GET: JobPost
        public ActionResult Index()
        {
            _userId = User.Identity.GetUserId();
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> PostJob([Bind()] JobInfo company)
        {
            _userId = User.Identity.GetUserId();
            company.CompanyId = await _companyService.GetCompanyByUserId(_userId);

            if (ModelState.IsValid)
            {

                await _jobPostingService.PostNewJob(company);
            }
            return RedirectToAction("Index", "Admin");
        }
    }
}