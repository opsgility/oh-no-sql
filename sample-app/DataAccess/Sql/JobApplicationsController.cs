using DataAccess.Data;
using DataAccess.Entities;
using DataAccess.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace DataAccess.Sql
{
    public static class JobApplicationsController
    {
        public static async Task<IEnumerable<JobPostingInfo>> GetJobApplicationsByUser(string userId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserID", ParameterValue = userId });
            return await SqlHelper.GetRecordsAsync<JobPostingInfo>("GetJobApplicationsByUser", parameters);
        }

        public static async Task<IEnumerable<JobPostingInfo>> SearchJobs(string searchString)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "SearchString", ParameterValue = searchString });
            return await SqlHelper.GetRecordsAsync<JobPostingInfo>("SearchJobPosting", parameters);
        }

        public static async Task<int> ApplyToJobAsync(string userId, string jobId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserId", ParameterValue = userId });
            parameters.Add(new ParameterInfo() { ParameterName = "JobId", ParameterValue = jobId });
            return await SqlHelper.ExecuteQueryAsync("ApplyToJob", parameters);
        }

        public async static Task<IEnumerable<SkillInfo>> GetSkillAsync()
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "GetAll", ParameterValue = "All" });
            return await SqlHelper.GetRecordsAsync<SkillInfo>("GetSkills", parameters);
        }
        public async static Task<IEnumerable<SkillInfo>> GetSkillByJobPostIdAsync(string UserId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserId", ParameterValue = UserId });
            return await SqlHelper.GetRecordsAsync<SkillInfo>("GetSkillsByJobPostId", parameters);
        }
        public static async Task<IEnumerable<JobPostingInfo>> SearchJobsByCompany(string companyName)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "CompanyName", ParameterValue = companyName });
            return await SqlHelper.GetRecordsAsync<JobPostingInfo>("SearchJobPostingByCompany", parameters);
        }

        public static  List<JobInfo> GetJobApplicationsByCompany(string compnyId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "CompanyID", ParameterValue = compnyId });
            return SqlHelper.GetRecords<JobInfo>("GetJobApplicationsByCompany", parameters);
        }

        public static Task<IEnumerable<ApplicationUser>> GetJobApplicationsByJobId(string JobId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "JobPostingID", ParameterValue = JobId });
            return SqlHelper.GetRecordsAsync<ApplicationUser>("GetJobApplicationsByJobId", parameters);
        }

    }
}
