using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ContosoJobs.Models
{
    public class JobInfoViewModel
    {
        public JobInfo JobInfo { get; set; }
        public IList<SkillInfo> skills { get; set; }
    }
}