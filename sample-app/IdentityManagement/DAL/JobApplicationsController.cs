using IdentityManagement.Data;
using IdentityManagement.Entities;
using IdentityManagement.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace IdentityManagement.DAL
{
    public static class JobApplicationsController
    {
        public static async Task<IEnumerable<JobPostingInfo>> GetJobApplicationsByUser(string userId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserID", ParameterValue = userId });
            return await SqlHelper.GetRecordsAsync<JobPostingInfo>("GetJobApplicationsByUser", parameters);
        }

        public static  List<JobInfo> GetJobApplicationsByCompany(string compnyId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "CompanyID", ParameterValue = compnyId });
            return SqlHelper.GetRecords<JobInfo>("GetJobApplicationsByCompany", parameters);
        }

        public static List<JobInfo> GetJobApplicationsByJobId(string JobId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "JobPostingID", ParameterValue = JobId });
            return SqlHelper.GetRecords<JobInfo>("GetJobApplicationsByJobId", parameters);
        }
    }
}
