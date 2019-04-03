using Microsoft.AspNet.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess.Entities
{
    public class RoleInfo : IRole<string>
    {
        public string Id { get; set; }
        public string Name { get; set; }
    }
}
