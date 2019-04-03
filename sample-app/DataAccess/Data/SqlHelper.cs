using Dapper;
using DataAccess.Utilities;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Data
{
    public static class SqlHelper
    {
        public static T GetRecord<T>(string spName, List<ParameterInfo> parameters)
        {
            T objRecord = default(T);
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }

                objRecord = SqlMapper.Query<T>(objConnection, spName, p, commandType: CommandType.StoredProcedure).FirstOrDefault();
                objConnection.Close();
            }
            return objRecord;
        }
        public static async Task<T> GetRecordAsync<T>(string spName, List<ParameterInfo> parameters)
        {
            T objRecord = default(T);
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }

                objRecord = await SqlMapper.QueryFirstOrDefaultAsync<T>(objConnection, spName, p, commandType: CommandType.StoredProcedure);
                objConnection.Close();
            }
            return objRecord;
        }
        public static async Task<IEnumerable<T>> GetRecordsAsync<T>(string spName, List<ParameterInfo> parameters)
        {
            IEnumerable<T> recordList;
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }

                recordList = await SqlMapper.QueryAsync<T>(objConnection, spName, p, commandType: CommandType.StoredProcedure);
                objConnection.Close();
            }
            return recordList;
        }
        public static List<T> GetRecords<T>(string spName, List<ParameterInfo> parameters)
        {
            List<T> recordList = new List<T>();
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }

                recordList = SqlMapper.Query<T>(objConnection, spName, p, commandType: CommandType.StoredProcedure).ToList<T>();
                objConnection.Close();
            }
            return recordList;
        }

        public static int GetIntRecord<T>(string spName, List<ParameterInfo> parameters)
        {
            int intRecord = 0;
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }

                using (var reader = SqlMapper.ExecuteReader(objConnection, spName, p, commandType: CommandType.StoredProcedure))
                {
                    if (reader != null && reader.Read())
                    {
                        intRecord = Convert.ToInt32(reader[0].ToString());
                    }
                }
                objConnection.Close();
            }
            return intRecord;
        }

        public static int ExecuteQuery(string spName, List<ParameterInfo> parameters)
        {
            int success = 0;
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }
                success = SqlMapper.Execute(objConnection, spName, p, commandType: CommandType.StoredProcedure);
                objConnection.Close();
            }
            return success;
        }
        public async static Task<int> ExecuteQueryAsync(string spName, List<ParameterInfo> parameters)
        {
            int success = 0;
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }
                success = await SqlMapper.ExecuteAsync(objConnection, spName, p, commandType: CommandType.StoredProcedure);
                objConnection.Close();
            }
            return success;
        }

        public static int ExecuteQueryWithIntOutputParam(string spName, List<ParameterInfo> parameters)
        {
            int success = 0;
            using (SqlConnection objConnection = new SqlConnection(Utils.ConnectionString()))
            {
                objConnection.Open();
                DynamicParameters p = new DynamicParameters();
                foreach (var param in parameters)
                {
                    p.Add("@" + param.ParameterName, param.ParameterValue);
                }
                success = SqlMapper.Execute(objConnection, spName, p, commandType: CommandType.StoredProcedure);
                objConnection.Close();
            }
            return success;
        }
    }
}
