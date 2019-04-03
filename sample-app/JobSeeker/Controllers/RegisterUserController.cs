using IdentityManagement.DAL;
using IdentityManagement.Entities;
using JobSeeker.Data;
using JobSeeker.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace JobSeeker.Controllers
{
    public class RegisterUserController : Controller
    {
        private static IUserService _userservice;
        public RegisterUserController(IUserService userservice)
        {
            _userservice = userservice;
        }
        // GET: RegisterUser
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
            // return View("Index", user);
        }
    }
}