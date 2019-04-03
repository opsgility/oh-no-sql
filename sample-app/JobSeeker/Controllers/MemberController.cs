using JobSeeker.Models;
using JobSeeker.Services;
using Microsoft.AspNet.Identity;
using System.Threading.Tasks;
using System.Web.Mvc;
using PagedList.Mvc;
using PagedList;

namespace CustomMVCIdentity.Controllers
{
    [Authorize(Roles = "Member")]
    public class MemberController : Controller
    {
        private string _userId;
        private IMemberService _memberService;

        public MemberController(IMemberService memberService)
        {
            _memberService = memberService;
        }
        // GET: Member
        public async Task<ActionResult> Index(int? i)
        {
            MemberViewModel Model = new MemberViewModel();
            _userId = User.Identity.GetUserId();
            Model.JobApplications = await _memberService.GetJobApplicationsByUser(_userId);
           var ViewModel = Model.JobApplications.ToPagedList(i ?? 1, 5);
            return View(ViewModel);
        }
    }
}