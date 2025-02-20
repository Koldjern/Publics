using Domain.Entities;
using Infrastructure.Sql.EF;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraStudent.Persistence.EF;

public interface IStudentEFContext : IEFContext
{
    DbSet<Student> Students { get; }
}
