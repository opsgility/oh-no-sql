using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using DataAccess.Sql;

using DataAccess.Entities;
using ContosoJobs.Models;

namespace ContosoJobs.Services
{
    public class CompanyService : ICompanyService
    {
        public async Task<bool> RegisterNewCompany(CompanyInfo company)
        {
            var companyId = Guid.NewGuid().ToString();
            company.Id = companyId;
            ApplicationUser User = new ApplicationUser
            {
                Email = company.Email,
                UserName = company.UserName,
                Password = company.Password
            };
            var userRole = "Administrator";
            try
            {
                var userId = await CreateUser(User);
                var success = AddUserToRole(userId, userRole);
                await CreateNewCompany(company);
                await CreateCompanyUserRel(userId, companyId);
            }
            catch
            {
                return false;
            }

            return true;

        }

        public async Task CreateCompanyUserRel(string userId, string companyId)
        {
            try
            {
                await CompanyController.NewCompanyUserRelationship(userId, companyId);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Could not create company User Relationship: " + companyId + " | " + ex.Message); 
            }
        }

        private async Task CreateNewCompany(CompanyInfo company)
        {
            try
            {
                await CompanyController.NewCompany(company);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Could not create company: " + company.Name + " | " + ex.Message);
            }
        }

        public async Task<bool> AddUserToRole(string userId, string userRole)
        {
            try
            {
                await UserRoleController.NewUserRoleAsync(userId, userRole);
            }
            catch(Exception ex)
            {
                Console.WriteLine("Could not add User to Role: " + userRole + " | " + ex.Message);
                return false;
            }
            return true;
        }

        public async Task<string> CreateUser(ApplicationUser user)
        {
            try
            {
                return await UserController.NewUser(user);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Could not create Company User: " + ex.Message);
            }
            return null;
        }

        public async Task<string> GetCompanyByUserId(string userId)
        {
          return await Task.Factory.StartNew(() =>
            {
              return CompanyController.GetCompanyByUserId(userId);
            });  
        }

        public async Task<IEnumerable<CompanyUserInfo>> GetUsersByCompanyId(string companyId)
        {
            return await CompanyController.GetUsersByCompanyId(companyId);
        }

        public async Task<IEnumerable<JobPostingInfo>> GetJobPostingsByCompanyId(string companyId)
        {
            return await CompanyController.GetJobPostingsByCompanyId(companyId);
        }

        // TODO
        //public async Task<IEnumerable<CompanyRating>> GetAllCompanyRatingsAsync(string companyId)
        //{
        //    /** Call your NoSQL store here **/
        //}

        // TODO
        //public async Task<IEnumerable<CompanyRating>> GeCompanyRatingByCompanyIdAsync(string companyId)
        //{
        /** Call your NoSQL store here **/
        //}

        public async Task<CompanyInfo> GetCompanyByCompanyId(string companyId)
        {
           return await CompanyController.GetCompanyByCompanyId(companyId);
        }

        // TODO
        //public Task<IEnumerable<CompanyRating>> GetAllCompanyRatingsAsync()
        //{
        //    throw new NotImplementedException();
        //}

        // TODO
        //public async Task<CompanyRating> GetDocumentAsync(string id, string companyId)
        //{
        /** Call your NoSQL store here **/
        //}

        // TODO
        //public async Task UpdateDocumentAsync(CompanyRating companyRating)
        //{
        /** Call your NoSQL store here **/
        //}
    }
}