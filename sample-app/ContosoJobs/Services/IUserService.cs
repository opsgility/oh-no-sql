using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ContosoJobs.Services
{
    public interface IUserService
    {
        bool AddNewUser(ApplicationUser user);
        bool AddUserToRole(string userRole, string userId);
    }
}
