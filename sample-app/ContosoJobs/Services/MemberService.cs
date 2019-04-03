using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using DataAccess.Sql;
using DataAccess.Entities;


namespace ContosoJobs.Services
{
    public class MemberService : IMemberService
    {
        public async Task<IEnumerable<ApplicationUser>> GetJobApplicantsbyJobId(string jobId)
        {
            return await JobApplicationsController.GetJobApplicationsByJobId(jobId);
        }

        public async Task<IEnumerable<JobPostingInfo>> GetJobApplicationsByUser(string userId)
        {
            return await JobApplicationsController.GetJobApplicationsByUser(userId);
        }

        public async Task<IEnumerable<SkillInfo>> GetUserSkills(string userId)
        {
            return await UserController.GetSkillByUserIdAsync(userId);
        }
        public async Task InsertSkillUserAssociationAsync(string userId, string[] skillIds)
        {
            foreach (var item in skillIds)
            {
                await JobsController.AddSkill(null, userId, item);
            }

        }
        public async Task DeleteSkillUserAssociationAsync(string userId, string[] skillIds)
        {
            foreach (var item in skillIds)
            {
                await JobsController.DeleteSkill(null, userId, item);
            }
           
        }

        // TODO
        //public async Task<IEnumerable<Resume>> GetDocumentByUserId(string userId)
        //{
        /** Call NoSQL here **/
        //}

        // TODO
        //public async Task<Resume> GetDocumentAsync(string resumeId, string userId)
        //{
        /** Call NoSQL here **/
        //}

        // TODO
        //public async Task UpdateDocumentAsync(Resume newResume)
        //{
        //    /** Call NoSQL here **/
        //}
    }
}