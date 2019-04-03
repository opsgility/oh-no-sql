//using System;
//using System.Collections.Generic;
//using System.Data.SqlClient;
//using System.Linq;
//using System.Threading.Tasks;
//using System.Web;

//namespace JobSeeker.Data
//{
//    public class UserDataConnection : IUserDataConnection
//    {
//        public async Task<bool> RegisterUser(User user)
//        {
//            string cnnString = System.Configuration.ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;

//            SqlConnection cnn = new SqlConnection(cnnString);
//            SqlCommand cmd = new SqlCommand();
//            cmd.Connection = cnn;
//            cmd.CommandType = System.Data.CommandType.StoredProcedure;
//            cmd.CommandText = "RegisterUser";
//            cmd.Parameters.Add(new SqlParameter("@FirstName", user.FirstName));
//            cmd.Parameters.Add(new SqlParameter("@LastName", user.LastName));
//            cmd.Parameters.Add(new SqlParameter("@Email", user.Email));
//            cmd.Parameters.Add(new SqlParameter("@Password", user.Password));
//            cmd.Parameters.Add(new SqlParameter("@UserRole", "JobSeeker"));
//            cmd.Parameters.Add(new SqlParameter("@CompanyId", user.CompanyId));
//            await cnn.OpenAsync();
//            try
//            {
//                object o = await cmd.ExecuteScalarAsync();
//            }
//            catch
//            {
//                return false;
//            }
         
//            cnn.Close();
//            return true;
//        }
//        public async Task<User> LoginUser(User user)
//        {
//            User user2 = new User();
//            try
//            {
//                using (var connection = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString))
//                {
//                    SqlCommand cmd = new SqlCommand();
//                    cmd.Connection = connection;
//                    cmd.CommandType = System.Data.CommandType.StoredProcedure;
//                    cmd.CommandText = "LoginUser";
//                    cmd.Parameters.Add(new SqlParameter("@Email", user.Email));
//                    cmd.Parameters.Add(new SqlParameter("@Password", user.Password));
//                    using (cmd)
//                    {
//                       await connection.OpenAsync();
//                        using (var reader = await cmd.ExecuteReaderAsync())
//                        {
//                            while (await reader.ReadAsync())
//                            {
//                                user2.Id = reader.GetGuid(0);
//                                user2.FirstName = reader.GetString(1);
//                                user2.LastName = reader.GetString(2);
//                                user2.UserRoleId = reader.GetGuid(3);
//                                user2.CompanyId = reader.GetGuid(4);
//                                user2.Email = reader.GetString(5);
//                                user2.Password = reader.GetString(6);
//                                user2.IsActive = reader.GetBoolean(7);
                               
                               
//                            }
//                        }
//                    }
//                }
//            }
//            catch(Exception E)
//            {
//                var message = E.Message;
//                return null;
//            }
//            return user2;
//        }

//    }
//}