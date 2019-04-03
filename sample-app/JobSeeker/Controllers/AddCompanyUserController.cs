using IdentityManagement.Entities;
using JobSeeker.Models;
using JobSeeker.Services;
using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace JobSeeker.Controllers
{
    public class AddCompanyUserController : Controller
    {
        private string _userId;
        private ICompanyService _companyService;
        private IUserService _userService;
        public AddCompanyUserController(ICompanyService companyService, IUserService userService)
        {
            _companyService = companyService;
            _userService = userService;
        }
        // GET: AddCompanyUser
        public ActionResult Index()
        {
            List<SelectListItem> roles = new List<SelectListItem>();
            roles.Add(new SelectListItem { Text = "Administrator", Value = "Administrator" });
            roles.Add(new SelectListItem { Text = "JobViewer", Value = "JobViewer" });
            roles.Add(new SelectListItem { Text = "JobPoster", Value = "JobPoster" });


            CompanyUserViewModel Model = new CompanyUserViewModel();
            Model.Roles = roles;
            return View(Model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> AddUser([Bind()] CompanyUserViewModel company)
        {
            _userId = User.Identity.GetUserId();
            company.UserId = Guid.NewGuid().ToString();
            company.CompanyId = await _companyService.GetCompanyByUserId(_userId);

            if (ModelState.IsValid)
            {
                ApplicationUser newUser = new ApplicationUser();
                newUser.Id = company.UserId;
                newUser.Password = company.Password;
                newUser.UserName = company.UserName;
                newUser.Email = company.Email;


                // Add the user to users
                await _companyService.CreateUser(newUser);
                // Add the User to UsersRoles table
                await _companyService.AddUserToRole(company.UserId, company.UserRole);
                // Add the user to the companyuser table
                await _companyService.CreateCompanyUserRel(company.UserId, company.CompanyId);
              //  await _companyService.RegisterNewCompany(company);
            }
            return RedirectToAction("Index", "Login");
        }
    }
}