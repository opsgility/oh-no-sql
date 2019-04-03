using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Web.Mvc;

namespace JobSeeker.Models
{
    public class CompanyUserViewModel
    {
        public string UserId { get; set; }
        public string CompanyId { get; set; }
        [Required]
        public string UserName { get; set; }
        [Required]
        public string UserRole { get; set; }
        [Required]
        public string Email { get; set; }
        [Required]
        [DataType(DataType.Password)]
        public string Password { get; set; }
        public IEnumerable<SelectListItem> Roles { get; set; }

    }
}