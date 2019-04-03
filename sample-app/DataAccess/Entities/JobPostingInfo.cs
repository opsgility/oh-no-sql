using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Entities
{
    public class JobPostingInfo
    {
        public string JobPostingId { get; set; }
        public string CompanyId { get; set; }
        public string CompanyName { get; set; }
        public string JobTitle { get; set; }
        public string JobDescription { get; set; }
        public string City { get; set; }
        public string State { get; set; }
        public int NumberOfApplicantants { get; set; }
        public int AverageRating { get; set; }
        public DateTime JobCreationDate { get; set; }
    }
}
