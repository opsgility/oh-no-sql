using ContosoJobs.Services;
using System.Web.Mvc;
using System.Threading.Tasks;
using PagedList.Mvc;
using PagedList;
using DataAccess.Entities;
using Microsoft.AspNet.Identity;
using System;
using System.Linq;

using System.Collections.Generic;

namespace ContosoJobs.Controllers
{
    public class HomeController : Controller
    {
        private IJobPostingService _jobPostingService;
        private ICompanyService _companyService;
        public HomeController(IJobPostingService jobPostingService, ICompanyService companyService)
        {
            _jobPostingService = jobPostingService;
            _companyService = companyService;

        }

        // Public Search Page
        public async Task<ActionResult> Index(int? i)
        {
            string searchText = Request.QueryString["Search"];

            if (string.IsNullOrEmpty(searchText))
            {
                searchText = " ";
            }
            var _jobPostings = await _jobPostingService.SearchJobs(searchText);

            var ViewModel = _jobPostings.ToPagedList(i ?? 1, 15);
            // TODO
            //   ViewModel = await MapRatings(ViewModel);

            return View(ViewModel);
        }

        // TODO
        //private async Task<IPagedList<JobPostingInfo>> MapRatings(IPagedList<JobPostingInfo> viewModel)
        //{
        //    foreach (var jobInfo in viewModel)
        //    {
        //       /* Get ratings here and add them to view here */
        //       var rating = ratings.FirstOrDefault(r => r.CompanyId == jobInfo.CompanyId);
        //       jobInfo.AverageRating = GetAverageRating(rating);
        //    }
        //    return viewModel;
        //}

        private int GetAverageRating(CompanyRating rating)
        {
            var total = 0;
            foreach (var item in rating.Ratings)
            {
                total += item.NumberOfStars;
            }
            return total / rating.Ratings.Count();
        }

        [HttpPost]
        public async Task<ActionResult> JobDetails()
        {
            string jobId = Request.QueryString["jobPostingId"];
            // TODO
            // Alter this method to get job details from NoSQL
            JobPostingInfo jobInfo = await _jobPostingService.GetJobByJobId(jobId);

            // TODO
            /* Add ratings from NoSQL */

            /* Then uncomment the following code and adapt to your ratings */

            //var rating = ratings.FirstOrDefault(r => r.CompanyId == jobInfo.CompanyId);
            //jobInfo.AverageRating = GetAverageRating(rating);

            return View(jobInfo);
        }

        [HttpPost]
        public async Task<ActionResult> Apply()
        {
            // get user Id 
            var userId = User.Identity.GetUserId();
            if (DataAccess.Utilities.Utils.IfUserInRole("Member"))
            {
                string jobId = Request.QueryString["Apply"];
                int success = await _jobPostingService.ApplyToJobAsync(userId, jobId);

                //Redirect to member page
                return RedirectToAction("Index", "Member");
            }

            //Redirect to Home page
            return RedirectToAction("Index", "Login");
        }

        // TODO
        //public async Task<ActionResult> AddReview()
        //{
        //    string ratingId = Request.QueryString["ratingId"];
        //    string companyId = Request.QueryString["CompanyRating"];
        //    // Get the document to edit
        //    CompanyRating companyRating = await _companyService.GetDocumentAsync(ratingId, companyId);
        //    //Rating Model = new Rating();
        //    return View(companyRating);
        //}

        // TODO
        //[HttpPost]
        //public async Task<ActionResult> AddReview(Rating newRating)
        //{
        //    string ratingId = Request.QueryString["ratingId"];
        //    string companyId = Request.QueryString["CompanyRating"];
        //    // Get the No SQL document to edit
        //    

        // TODO
        //    // Add the document then send it to the update method

        //    CompanyRating NewCompanyRating = mapNewRatings(companyRating, newRating);

        //    //Save to doc db
        //    await _companyService.UpdateDocumentAsync(NewCompanyRating);
        //    if(newRating.Body != null && newRating.Heading != null)
        //    {
        //        return RedirectToAction("CompanyRating", "Home", new { CompanyRating = NewCompanyRating.CompanyId });
        //    }

        //    return View(NewCompanyRating);
        //}

        private CompanyRating mapNewRatings(CompanyRating companyRating, Rating newRating)
        {
            List<Rating> RatingList = new List<Rating>();
            foreach (var item in companyRating.Ratings)
            {
                if(item.Body != null)
                {
                    RatingList.Add(item);
                }
              
            }
            if(newRating.Body != null)
            {
                newRating.Date = DateTime.Now;
                RatingList.Add(newRating);
            }

            CompanyRating newCompanyRating = new CompanyRating
            {
                Id = companyRating.Id,
                CompanyId = companyRating.CompanyId,
                Key = companyRating.Key,
                CompanyName = companyRating.CompanyName,
                Ratings = RatingList,
            };
            return newCompanyRating;
        }

        // TODO
        //public async Task<ActionResult> CompanyRating()
        //{
        //    string companyId = Request.QueryString["CompanyRating"];
        //    CompanyRating Model = new CompanyRating();

        //    CompanyInfo company = await _companyService.GetCompanyByCompanyId(companyId);

                /*Get rating by Company Id here  */
          

               /*  Then uncomment out the code to work with the view*/

        //     Model = ratings.FirstOrDefault(r => r.CompanyId == companyId);
        //    // get the rating info 
        //    return View(Model);
        //    // populate  rating view

        //}
    }
}