using Domain.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraStudent.Persistence.EF;

public class StudentEFContext : DbContext, IStudentEFContext
{
    public StudentEFContext(DbContextOptions<StudentEFContext> options) : base(options)
    {
    }
    public DbSet<Student> Students => Set<Student>();

    public async Task<int> SaveDB()
    {
        return await SaveChangesAsync();
    }
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Student>((pro) =>
        {
            pro.ToTable("Students");
            pro.Property(x => x.Email).HasColumnName("Email");
            pro.Property(x => x.Name).HasColumnName("Name");
            pro.Property(x => x.Semester).HasColumnName("Semester");
            pro.Property(x => x.Education).HasColumnName("Education");
            pro.HasKey(x => x.Email);
        });
    }
}
