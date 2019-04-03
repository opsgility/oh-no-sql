using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ContosoJobs.Services
{
    public interface IJobPostingService
    {
        Task<bool> PostNewJob(JobInfo job, string[] id);
        Task<IEnumerable<JobPostingInfo>> SearchJobs(string searchString);
        Task<JobPostingInfo> GetJobByJobId(string jobId);
        Task<int> ApplyToJobAsync(string userId, string jobId);
        Task<IEnumerable<JobPostingInfo>> SearchJobsByCompanyAsync(string companyName);
        Task<IList<SkillInfo>> GetSKillsAsync();
    }
}
