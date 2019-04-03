using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IdentityManagement.Entities
{
    public class CompanyUserInfo
    {
        public string UserId { get; set; }
        public string CompanyId { get; set; }
        public string CompanyName { get; set; }
        public string UserName { get; set; }
        public string UserRole { get; set; }
    }
}
