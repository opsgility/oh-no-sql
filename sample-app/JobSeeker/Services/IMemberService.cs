using IdentityManagement.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JobSeeker.Services
{
    public interface IMemberService
    {
        Task<IEnumerable<JobPostingInfo>> GetJobApplicationsByUser(string userId);
    }
}
