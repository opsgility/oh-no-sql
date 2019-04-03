using ContosoJobs.Services;
using DataAccess.Entities;
using System.Web.Mvc;

namespace ContosoJobs.Controllers
{
    public class RegisterUserController : Controller
    {
        private static IUserService _userservice;
        public RegisterUserController(IUserService userservice)
        {
            _userservice = userservice;
        }

        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult RegisterUser([Bind()] ApplicationUser user)
        {

            if (ModelState.IsValid)
            {
                 _userservice.AddNewUser(user);

            }
            return RedirectToAction("Index", "Login");
        }
    }
}