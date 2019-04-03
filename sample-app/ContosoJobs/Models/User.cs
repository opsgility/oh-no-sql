using System;
using System.ComponentModel.DataAnnotations;

namespace JobSeeker.Data
{
    public class User
    {
        public Guid Id { get; set; }
        [Required]
        public string FirstName { get; set; }
        [Required]
        public string LastName { get; set; }
        [Required]
        public string Email { get; set; }
        [Required]
        [DataType(DataType.Password)]
        public string Password { get; set; }
        public bool IsActive { get; set; }
        public Guid CompanyId { get; set; }
        public string UserRole { get; set; }
        public Guid UserRoleId { get; set; }
    }
}