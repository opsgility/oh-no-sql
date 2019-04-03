using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using IdentityManagement.DAL;
using IdentityManagement.Entities;

namespace JobSeeker.Services
{
    public class JobPostingService : IJobPostingService
    {
        public async Task<bool> PostNewJob(JobInfo job)
        {
            job.Id = Guid.NewGuid().ToString();
            var success = await JobsController.NewJobPosting(job);
            return true;
        }
    }
}