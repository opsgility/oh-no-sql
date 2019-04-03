using DataAccess.Entities;
using ContosoJobs.Services;
using Microsoft.AspNet.Identity;
using System.Threading.Tasks;
using System.Web.Mvc;
using ContosoJobs.Models;

namespace ContosoJobs.Controllers
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
        public async Task<ActionResult> Index()
        {
            var skills = await _jobPostingService.GetSKillsAsync();
            ViewBag.Skills = new MultiSelectList(skills, "Id", "Skill");
            _userId = User.Identity.GetUserId();
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> PostJob([Bind()] JobInfo company, string[] id)
        {
            _userId = User.Identity.GetUserId();
            company.CompanyId = await _companyService.GetCompanyByUserId(_userId);

            if (ModelState.IsValid)
            {
                await _jobPostingService.PostNewJob(company, id);
            }
            return RedirectToAction("Index", "Admin");
        }
    }
}