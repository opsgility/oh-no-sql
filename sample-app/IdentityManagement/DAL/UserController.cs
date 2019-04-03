using IdentityManagement.Data;
using IdentityManagement.Entities;
using IdentityManagement.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IdentityManagement.DAL
{
    public static class UserController
    {
        public static async Task<string> NewUser(ApplicationUser objUser)
        {
            string newId;
            if (string.IsNullOrEmpty(objUser.Id))
            {
                newId = Guid.NewGuid().ToString();
            }
            else
            {
                newId = objUser.Id;
            }

            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Id", ParameterValue = newId });
            parameters.Add(new ParameterInfo() { ParameterName = "UserName", ParameterValue = objUser.UserName });
            parameters.Add(new ParameterInfo() { ParameterName = "Email", ParameterValue = objUser.Email });
            parameters.Add(new ParameterInfo() { ParameterName = "Password", ParameterValue = objUser.Password });
            parameters.Add(new ParameterInfo() { ParameterName = "Status", ParameterValue = EnumUserStatus.Active });
            int success = await SqlHelper.ExecuteQueryAsync("NewUser", parameters);
            if (success > -1)
            {
                return null;
            }
            return newId;
        }
    

        public static int DeleteUser(ApplicationUser objUser)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Id", ParameterValue = objUser.Id });
            int success = SqlHelper.ExecuteQuery("DeleteUser", parameters);
            return success;
        }

        public static ApplicationUser GetUser(string id)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Id", ParameterValue = id });
            ApplicationUser oUser = SqlHelper.GetRecord<ApplicationUser>("GetUser", parameters);
            return oUser;
        }

        public static ApplicationUser GetUserByUsername(string userName)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Username", ParameterValue = userName });
            ApplicationUser oUser = SqlHelper.GetRecord<ApplicationUser>("GetUserByUsername", parameters);
            return oUser;
        }

        public static int UpdateUser(ApplicationUser objUser)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Email", ParameterValue = objUser.Email });
            int success = SqlHelper.ExecuteQuery("UpdateUser", parameters);
            return success;
        }
    }
}
