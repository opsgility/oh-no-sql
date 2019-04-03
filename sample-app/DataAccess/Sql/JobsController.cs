using DataAccess.Data;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Sql
{
    public static class JobsController
    {
        public static async Task<int> NewJobPosting(JobInfo job)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "JobPostingId", ParameterValue = job.Id });
            parameters.Add(new ParameterInfo() { ParameterName = "CompanyId", ParameterValue = job.CompanyId });
            parameters.Add(new ParameterInfo() { ParameterName = "JobTitle", ParameterValue = job.Title });
            parameters.Add(new ParameterInfo() { ParameterName = "JobDescription", ParameterValue = job.Description });
            int success = await SqlHelper.ExecuteQueryAsync("NewJobPosting", parameters);
            return success;
        }

        public async static Task AddSkill(string jobId, string userId, string skillId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "JobPostingId", ParameterValue = jobId });
            parameters.Add(new ParameterInfo() { ParameterName = "UserId", ParameterValue = userId });
            parameters.Add(new ParameterInfo() { ParameterName = "SkillId", ParameterValue = skillId });
            int success = await SqlHelper.ExecuteQueryAsync("AddSkills", parameters);

        }

        public static async Task DeleteSkill(string jobId, string userId, string skillId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "JobPostingId", ParameterValue = jobId });
            parameters.Add(new ParameterInfo() { ParameterName = "UserId", ParameterValue = userId });
            parameters.Add(new ParameterInfo() { ParameterName = "SkillId", ParameterValue = skillId });
            int success = await SqlHelper.ExecuteQueryAsync("DeleteSkills", parameters);
        }

        public static List<JobInfo> GetJobPostingByCompany(string companyId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "ComanyId", ParameterValue = companyId });
            return SqlHelper.GetRecords<JobInfo>("JobPostingByCompany", parameters);
        }

        public static async Task<JobPostingInfo> GetJobPostingByJobId(string jobId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Id", ParameterValue = jobId });
            return await SqlHelper.GetRecordAsync<JobPostingInfo>("GetJobPosting", parameters);
        }

    }
}
