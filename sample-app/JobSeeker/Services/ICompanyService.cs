using IdentityManagement.Entities;
using JobSeeker.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JobSeeker.Services
{
    public interface ICompanyService
    {
        Task<bool> RegisterNewCompany(CompanyInfo company);
        Task<string> GetCompanyByUserId(string userId);
        Task CreateCompanyUserRel(string userId, string companyId);
        Task<IEnumerable<CompanyUserInfo>> GetUsersByCompanyId(string companyId);
        Task<IEnumerable<JobPostingInfo>> GetJobPostingsByCompanyId(string companyId);
        Task<string> CreateUser(ApplicationUser user);
        Task<bool> AddUserToRole(string userId, string userRole);
    }
}
