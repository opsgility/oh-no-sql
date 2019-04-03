using DataAccess.Utilities;
using Microsoft.AspNet.Identity;
using System.ComponentModel.DataAnnotations;

namespace DataAccess.Entities
{
    public class UserInfo : IUser<string>
    {
        public string Id { get; set; }
        [Required]
        public string UserName { get; set; }
        [Required]
        public string Email { get; set; }
        [DataType(DataType.Password)]
        public string Password { get; set; }
        public EnumUserStatus Status { get; set; }
    }
}
