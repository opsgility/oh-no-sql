using IdentityManagement.DAL;
using IdentityManagement.Entities;
using JobSeeker.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;

namespace JobSeeker.Controllers
{
    public class CompanyController : Controller
    {
        private static ICompanyService _companyService;
        public CompanyController(ICompanyService companyService)
        {
            _companyService = companyService;
        }
        // GET: Company
        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> RegisterCompany([Bind()] CompanyInfo company)
        {
            if (ModelState.IsValid)
            {
                await _companyService.RegisterNewCompany(company);
            }
                return RedirectToAction("Index", "Login");
        }
    }
}