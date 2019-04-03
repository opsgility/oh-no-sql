using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ContosoJobs.Services
{
    public interface IMemberService
    {
        // TODO uncomment these as needed
        Task<IEnumerable<JobPostingInfo>> GetJobApplicationsByUser(string userId);
        Task<IEnumerable<ApplicationUser>> GetJobApplicantsbyJobId(string jobId);
        Task<IEnumerable<SkillInfo>> GetUserSkills(string userId);
        Task InsertSkillUserAssociationAsync(string userId, string[] skillIds);
        Task DeleteSkillUserAssociationAsync(string userId, string[] id);
        //Task<IEnumerable<Resume>> GetDocumentByUserId(string userId);
        //Task<Resume> GetDocumentAsync(string resumeId, string userId);
        //Task UpdateDocumentAsync(Resume newResume);
    }
}
