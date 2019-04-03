using DataAccess.Entities;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ContosoJobs.Models
{
    public class AdminViewModel
    {
        public IEnumerable<CompanyUserInfo> Users { get; set; }
        public IPagedList<JobPostingInfo> JobPostings { get; set; }
        public List<JobApplicationsViewModel> JobApplications { get; set; }
        public int AverageRating { get; set; }

    }
}