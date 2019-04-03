using IdentityManagement.Data;
using IdentityManagement.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IdentityManagement.DAL
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

        public static List<JobInfo> GetJobPostingByCompany(string companyId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "ComanyId", ParameterValue = companyId });
            return SqlHelper.GetRecords<JobInfo>("JobPostingByCompany", parameters);
        }
    }
}
