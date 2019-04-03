using DataAccess.Entities;
using ContosoJobs.Services;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace ContosoJobs.Controllers
{
    public class CompanyController : Controller
    {
        private static ICompanyService _companyService;
        public CompanyController(ICompanyService companyService)
        {
            _companyService = companyService;
        }
        
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