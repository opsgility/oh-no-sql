using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using IdentityManagement.DAL;
using IdentityManagement.Entities;

namespace JobSeeker.Services
{
    public class MemberService : IMemberService
    {
        public async Task<IEnumerable<JobPostingInfo>> GetJobApplicationsByUser(string userId)
        {
            return await JobApplicationsController.GetJobApplicationsByUser(userId);
        }
    }
}