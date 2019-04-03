using DataAccess.Data;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Sql
{
    public static class UserRoleController
    {
        public static async Task<int> NewUserRoleAsync(string userID, string roleName)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserID", ParameterValue = userID });
            parameters.Add(new ParameterInfo() { ParameterName = "RoleName", ParameterValue = roleName });
            int success = await SqlHelper.ExecuteQueryAsync("NewUserRole", parameters);
            return success;
        }
        public static int NewUserRole(string userID, string roleName)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserID", ParameterValue = userID });
            parameters.Add(new ParameterInfo() { ParameterName = "RoleName", ParameterValue = roleName });
            int success = SqlHelper.ExecuteQuery("NewUserRole", parameters);
            return success;
        }

        public static int DeleteUserRole(string userID, string roleName)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserID", ParameterValue = userID });
            parameters.Add(new ParameterInfo() { ParameterName = "RoleName", ParameterValue = roleName });
            int success = SqlHelper.ExecuteQuery("DeleteUserRole", parameters);
            return success;
        }

        public static IList<string> GetUserRoles(string userID)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "UserID", ParameterValue = userID });
            IList<string> roles = SqlHelper.GetRecords<string>("GetUserRoles", parameters);
            return roles;
        }

        public static UserInfo GetUserByUsername(string userName)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "Username", ParameterValue = userName });
            UserInfo oUser = SqlHelper.GetRecord<UserInfo>("GetUserByUsername", parameters);
            return oUser;
        }
    }
}
