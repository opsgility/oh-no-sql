using DataAccess.Entities;
using PagedList;

namespace ContosoJobs.Models
{
    public class JobPostingDetailViewModel
    {
        public JobPostingInfo Job { get; set; }
        public IPagedList<ApplicationUser> Applicants { get; set; }
        public CompanyRating CompanyRating { get; set; }
    }
}