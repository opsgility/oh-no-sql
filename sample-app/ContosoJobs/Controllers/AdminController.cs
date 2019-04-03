using ContosoJobs.Models;
using ContosoJobs.Services;
using Microsoft.AspNet.Identity;
using System.Threading.Tasks;
using System.Web.Mvc;
using PagedList;


namespace ContosoJobs.Controllers
{
    [Authorize(Roles = "Administrator")]
    public partial class AdminController : Controller
    {
        private string _userId;
        private ICompanyService _companyService;
        private IJobPostingService _jobPostingService;
        private IMemberService _memberService;
        public AdminController(ICompanyService companyService, IJobPostingService jobPostingService, IMemberService memberService)
        {
            _jobPostingService = jobPostingService;
            _companyService = companyService;
            _memberService = memberService;
  
        }

        public async Task<ActionResult> Index(int? i)
        {
            _userId = User.Identity.GetUserId();
            var companyId = await _companyService.GetCompanyByUserId(_userId);
            AdminViewModel Model = new AdminViewModel();
            // TODO
            /** Add ratings  to the viewmodel **/
            // var rating = ratings.FirstOrDefault(r => r.CompanyId == companyId);
            // Model.AverageRating = GetAverageRating(rating);
            // get all the company users and roles
            Model.Users = await _companyService.GetUsersByCompanyId(companyId);
            var enumJobPosting = await _companyService.GetJobPostingsByCompanyId(companyId);
            Model.JobPostings = enumJobPosting.ToPagedList(i ?? 1, 8);
            return View(Model);
        }

        /** TODO uncomment when you have ratings **/
        //private int GetAverageRating(CompanyRating rating)
        //{
        //    var total = 0;
        //    foreach (var item in rating.Ratings)
        //    {
        //        total += item.NumberOfStars;
        //    }
        //    return total / rating.Ratings.Count();
        //}

        [HttpPost]
        public async Task<ActionResult> JobPostingDetails(int? i)
        {
            JobPostingDetailViewModel model = new JobPostingDetailViewModel();

            string jobId = Request.QueryString["jobPostingId"];
            model.Job = await _jobPostingService.GetJobByJobId(jobId);
            var applicants = await _memberService.GetJobApplicantsbyJobId(jobId);

            model.Applicants = applicants.ToPagedList(i ?? 1, 8);

            return View(model);
        }

    }
}