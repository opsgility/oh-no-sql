using DataAccess.Data;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Sql
{
    public static class CompanyController
    {
        //
        //NewCompanyUserRelationship
        public static async Task<int> NewCompany(CompanyInfo company)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Id", ParameterValue = company.Id });
            parameters.Add(new ParameterInfo() { ParameterName = "Name", ParameterValue = company.Name });
            parameters.Add(new ParameterInfo() { ParameterName = "City", ParameterValue = company.City });
            parameters.Add(new ParameterInfo() { ParameterName = "State", ParameterValue = company.State });
            int success = await SqlHelper.ExecuteQueryAsync("NewCompany", parameters);
            return success;
        }
        public static async Task<int> NewCompanyUserRelationship(string userID, string companyID)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserId", ParameterValue = userID });
            parameters.Add(new ParameterInfo() { ParameterName = "CompanyId", ParameterValue = companyID });
            int success = await SqlHelper.ExecuteQueryAsync("NewCompanyUser", parameters);
            return success;
        }

        public static string GetCompanyByUserId(string userId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserId", ParameterValue = userId });
            var success = SqlHelper.GetRecord<string>("GetCompanyIdByUserId", parameters);
            return success;
        }

        public async static Task<IEnumerable<CompanyUserInfo>> GetUsersByCompanyId(string companyId)
        {
            //GetCompanyUsersByCompanyId
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "@CompanyId", ParameterValue = companyId });
            var success = await SqlHelper.GetRecordsAsync<CompanyUserInfo>("GetCompanyUsersByCompanyId", parameters);
            return success;
        }

        public static async Task<IEnumerable<JobPostingInfo>> GetJobPostingsByCompanyId(string companyId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "@CompanyId", ParameterValue = companyId });
            var success = await SqlHelper.GetRecordsAsync<JobPostingInfo>("GetJobPostingsByCompanyId", parameters);
            return success;
        }

        public static async Task<CompanyInfo> GetCompanyByCompanyId(string companyId)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Id", ParameterValue = companyId });
            var success = await SqlHelper.GetRecordAsync<CompanyInfo>("GetCompany", parameters);
            return success;
        }
    }
}
