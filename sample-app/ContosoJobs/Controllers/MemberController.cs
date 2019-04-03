using ContosoJobs.Models;
using ContosoJobs.Services;
using Microsoft.AspNet.Identity;
using System.Threading.Tasks;
using System.Web.Mvc;
using PagedList;

namespace CustomMVCIdentity.Controllers
{
    [Authorize(Roles = "Member")]
    public class MemberController : Controller
    {
        private string _userId;
        private IMemberService _memberService;
        private IJobPostingService _jobPostingService;
     

        public MemberController(IMemberService memberService, IJobPostingService jobPostingService)
        {
            _memberService = memberService;
            _jobPostingService = jobPostingService;

        }
       
        public async Task<ActionResult> Index(int? i)
        {
            MemberViewModel Model = new MemberViewModel();
            _userId = User.Identity.GetUserId();
           var jobApplications = await _memberService.GetJobApplicationsByUser(_userId);
            Model.JobApplications = jobApplications.ToPagedList(i ?? 1, 6);
            Model.Skills = await _memberService.GetUserSkills(_userId);

            var skills = await _jobPostingService.GetSKillsAsync();
            ViewBag.Skills = new MultiSelectList(skills, "Id", "Skill");
            // TODO
            /**** Get Resumes from NoSql Data store ****/

            /*******************************************/

            return View(Model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> AddSkills(string[] id)
        {
            _userId = User.Identity.GetUserId();

            if (ModelState.IsValid)
            {
                await _memberService.InsertSkillUserAssociationAsync(_userId, id);
            }
            return RedirectToAction("Index", "Member");
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> DeleteSkills(string[] id)
        {
            _userId = User.Identity.GetUserId();

            if (ModelState.IsValid)
            {
                await _memberService.DeleteSkillUserAssociationAsync(_userId, id);
            }
            return RedirectToAction("Index", "Member");
        }

        // TODO
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public async Task<ActionResult> AddUpdateResume(MemberViewModel newResume)
        //{
        //    newResume.MyResume.Id = Request.QueryString["ResumeId"];
        //    newResume.MyResume.Key = Request.QueryString["Key"];
        //    newResume.MyResume.UserId = Request.QueryString["UserId"];

        //    //Save to NoSql store here


        //    return RedirectToAction("Index", "Member");
        //}
    }
}