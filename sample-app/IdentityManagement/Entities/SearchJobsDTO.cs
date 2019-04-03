using System;
using System.ComponentModel.DataAnnotations;

namespace IdentityManagement.Entities
{
    public class SearchJobsDTO
    {
        public string JobPostingId { get; set; }
        public string CompanyId { get; set; }
        public string JobTitle { get; set; }
        public string JobDescription { get; set; }
        public string CompanyName { get; set; }
        public string City { get; set; }
        public string State { get; set; }
        public DateTimeOffset CreatedOn { get; set; }
    }
}
