using IdentityManagement.Utilities;
using System.ComponentModel.DataAnnotations;

namespace IdentityManagement.Entities
{
    public class CompanyInfo
    {
        public string Id { get; set; }
        [Required]
        [Display(Name = "User Name")]
        public string UserName { get; set; }
        [Required]
        public string Email { get; set; }
        [DataType(DataType.Password)]
        public string Password { get; set; }
        public EnumUserStatus Status { get; set; }
        [Required]
        [Display(Name = "Company Name")]
        public string Name { get; set; }
        [Required]
        public string City { get; set; }
        [Required]
        public string State { get; set; }
        public bool IsActive { get; set; }
    }
}
