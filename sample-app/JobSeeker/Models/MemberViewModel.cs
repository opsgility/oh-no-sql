using IdentityManagement.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace JobSeeker.Models
{
    public class MemberViewModel
    {
        public IEnumerable<JobPostingInfo> JobApplications { get; set; }
    }
}