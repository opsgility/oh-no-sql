using System;
namespace ContosoJobs.Models
{
    public class JobPostingViewModel
    {
        public string JobPostingId { get; set; }
        public string CompanyId { get; set; }
        public string CompanyName { get; set; }
        public string JobTitle { get; set; }
        public string JobDescription { get; set; }
        public string City { get; set; }
        public string State { get; set; }
        public int NumberOfApplicantants { get; set; }
        public DateTimeOffset JobCreationDate { get; set; }
    }
}