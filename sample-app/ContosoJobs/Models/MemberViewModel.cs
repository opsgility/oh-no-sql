using DataAccess.Entities;
using PagedList;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ContosoJobs.Models
{
    public class MemberViewModel
    {
        public IPagedList<JobPostingInfo> JobApplications { get; set; }
        public IEnumerable<SkillInfo> Skills { get; set; }
        public Resume MyResume { get; set; }
    }
}