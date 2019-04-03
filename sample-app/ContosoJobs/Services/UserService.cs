using System;
using System.Threading.Tasks;
using DataAccess.Sql;
using DataAccess.Entities;

namespace ContosoJobs.Services
{
    public class UserService : IUserService
    {
        public bool AddNewUser(ApplicationUser user)
        {
            try
            { // await _userDataConnection.RegisterUser(user);
                var userRole = "Member";
                if(user.Id == null)
                {
                    user.Id = Guid.NewGuid().ToString();
                }

               Task.Factory.StartNew(async() =>
                {
                    return await UserController.NewUser(user);
                });
                // Add jobseeker member role to UserRoles table
                    var result = AddUserToRole(userRole, user.Id);

            }
            catch (Exception ex)
            {
                Console.WriteLine("failed to add user: " + ex.Message);
                return false;
            }
            return true;
        }

        public bool AddUserToRole(string userRole, string userId)
        {
            try
            {
              Task.Factory.StartNew(() =>
                {
                   UserRoleController.NewUserRole(userId, userRole);
                });
            }
            catch(Exception ex)
            {
                Console.WriteLine("failed to add user to role: " + ex.Message);
                return false;
            }
            return true;
        }
    }
}