using DataAccess.Entities;
using ContosoJobs.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ContosoJobs.Services
{
    public interface ICompanyService
    {
        // TODO uncomment these as needed
        Task<bool> RegisterNewCompany(CompanyInfo company);
        Task<string> GetCompanyByUserId(string userId);
        Task CreateCompanyUserRel(string userId, string companyId);
        Task<IEnumerable<CompanyUserInfo>> GetUsersByCompanyId(string companyId);
        Task<IEnumerable<JobPostingInfo>> GetJobPostingsByCompanyId(string companyId);
        Task<string> CreateUser(ApplicationUser user);
        Task<bool> AddUserToRole(string userId, string userRole);
        //Task<IEnumerable<CompanyRating>> GetAllCompanyRatingsAsync();
        //Task<IEnumerable<CompanyRating>> GeCompanyRatingByCompanyIdAsync(string companyId);
        Task<CompanyInfo> GetCompanyByCompanyId(string companyId);
        //Task<CompanyRating> GetDocumentAsync(string id, string companyId);
        //Task UpdateDocumentAsync(CompanyRating companyRating);
    }
}
