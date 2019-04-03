using ContosoJobs.Services;
using System.Web.Mvc;
using System.Threading.Tasks;
using PagedList.Mvc;
using PagedList;
using DataAccess.Entities;
using System.Collections.Generic;
using System.Linq;


namespace ContosoJobs.Controllers
{
    public class SearchByCompanyController : Controller
    {
        private IJobPostingService _jobPostingService;
        public SearchByCompanyController(IJobPostingService jobPostingService)
        {
            _jobPostingService = jobPostingService;
        }
       
        public async Task<ActionResult> Index(int? i, string company)
        {
            IEnumerable<JobPostingInfo> _jobPostings;
            IPagedList<JobPostingInfo> ViewModel;

            string searchText = Request.QueryString["Search"];
            string companySearch = Request.QueryString["CompanySearch"];
            string companyName = Request.QueryString["SearchByCompany"];

            if (!string.IsNullOrWhiteSpace(searchText) && !string.IsNullOrWhiteSpace(companySearch))
            {
                var postings = await _jobPostingService.SearchJobsByCompanyAsync(companySearch);
                _jobPostings = postings.Where(x => x.JobTitle.Contains(searchText) || x.JobDescription.Contains(searchText)).ToList();
            }
             else 
            {
                if (!string.IsNullOrWhiteSpace(companyName))
                {
                    company = companyName;
                }
                _jobPostings = await _jobPostingService.SearchJobsByCompanyAsync(company);
            }


            ViewModel = _jobPostings.ToPagedList(i ?? 1, 15);
            return View(ViewModel);
        }
    }
}