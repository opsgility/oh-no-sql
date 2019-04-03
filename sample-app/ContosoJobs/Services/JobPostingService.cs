using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DataAccess.Sql;
using DataAccess.Entities;


namespace ContosoJobs.Services
{
    public class JobPostingService : IJobPostingService
    {
        public async Task<JobPostingInfo> GetJobByJobId(string jobId)
        {
            return await JobsController.GetJobPostingByJobId(jobId);
            /* TODO Modify this code to call NoSql */ 
        }

        public async Task<bool> PostNewJob(JobInfo job, string[] id)
        {
            job.Id = Guid.NewGuid().ToString();
            // for each skill - add it to the skill rel table 
            await InsertSkillJobAssociationAsync(job.Id, id);
            var success = await JobsController.NewJobPosting(job);
            return true;
        }

        private async Task InsertSkillJobAssociationAsync(string jobId, string[] skillIds)
        {
            foreach (var item in skillIds)
            {
                await JobsController.AddSkill(jobId, null, item);
            }
            
        }

        public async Task<IEnumerable<JobPostingInfo>> SearchJobs(string searchString)
        {
            /* TODO Modify this code to call NoSql */
            return await JobApplicationsController.SearchJobs(searchString);
        }

        public async Task<int> ApplyToJobAsync(string userId, string jobId)
        {
            return await JobApplicationsController.ApplyToJobAsync(userId, jobId);
        }

        public async Task<IEnumerable<JobPostingInfo>> SearchJobsByCompanyAsync(string companyName)
        {
            /* TODO Modify this code to call NoSql */
            return await JobApplicationsController.SearchJobsByCompany(companyName);
        }
        public async Task<IList<SkillInfo>> GetSKillsAsync()
        {
            List<SkillInfo> Skills = new List<SkillInfo>();
            var skills = await JobApplicationsController.GetSkillAsync();
            foreach (var item in skills)
            {
                Skills.Add(item);
            }
            return Skills;
        }

    }
}