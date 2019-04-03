using IdentityManagement.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace JobSeeker.Models
{
    public class AdminViewModel
    {
        public IEnumerable<CompanyUserInfo> Users { get; set; }
        public IEnumerable<JobPostingInfo> JobPostings { get; set; }
        public List<JobApplicationsViewModel> JobApplications { get; set; }
    }
}