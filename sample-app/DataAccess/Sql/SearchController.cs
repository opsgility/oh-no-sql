using DataAccess.Data;
using DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Sql
{
    public static class SearchController
    {
        public static List<SearchJobsDTO> SearchJobPostingByText(string searchText)
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            parameters.Add(new ParameterInfo() { ParameterName = "SearchString", ParameterValue = searchText });
            return SqlHelper.GetRecords<SearchJobsDTO>("SearchJobPosting", parameters);
        }

        public static List<SearchJobsDTO> GetJobPosting()
        {
            List<ParameterInfo> parameters = new List<ParameterInfo>();
            return SqlHelper.GetRecords<SearchJobsDTO>("GetJobPostings", parameters);
        }

    }
}
