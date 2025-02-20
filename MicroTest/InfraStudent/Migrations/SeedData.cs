using Domain.Entities;
using InfraStudent.Persistence.EF;
using Microsoft.AspNetCore.Builder;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace InfraStudent.Migrations;

public static class SeedData
{
    public static void EnsurePopulated(this IApplicationBuilder app)
    {
        StudentEFContext context = app.ApplicationServices
        .CreateScope().ServiceProvider.GetRequiredService<StudentEFContext>();
        if (context.Database.GetPendingMigrations().Any())
        {
            context.Database.Migrate();
        }
        if (!context.Students.Any())
        {
            context.Students.AddRange(
            new Student
            {
                Name = "Alice Johnson",
                Email = "alice.johnson@example.com",
                Education = "Computer Science",
                Semester = 2
            },
            new Student
            {
                Name = "Bob Smith",
                Email = "bob.smith@example.com",
                Education = "Mathematics",
                Semester = 3
            },
            new Student
            {
                Name = "Charlie Brown",
                Email = "charlie.brown@example.com",
                Education = "Physics",
                Semester = 1
            },
            new Student
            {
                Name = "Diana Prince",
                Email = "diana.prince@example.com",
                Education = "Literature",
                Semester = 4
            },
            new Student
            {
                Name = "Edward Elric",
                Email = "edward.elric@example.com",
                Education = "Engineering",
                Semester = 5
            },
            new Student
            {
                Name = "Fiona Green",
                Email = "fiona.green@example.com",
                Education = "Computer Science",
                Semester = 3
            },
            new Student
            {
                Name = "George Martin",
                Email = "george.martin@example.com",
                Education = "Mathematics",
                Semester = 4
            },
            new Student
            {
                Name = "Hannah Lee",
                Email = "hannah.lee@example.com",
                Education = "Biology",
                Semester = 2
            },
            new Student
            {
                Name = "Ian Wright",
                Email = "ian.wright@example.com",
                Education = "Physics",
                Semester = 1
            },
            new Student
            {
                Name = "Jasmine Taylor",
                Email = "jasmine.taylor@example.com",
                Education = "Engineering",
                Semester = 3
            },
            new Student
            {
                Name = "Kevin Brown",
                Email = "kevin.brown@example.com",
                Education = "Computer Science",
                Semester = 4
            },
            new Student
            {
                Name = "Lily Evans",
                Email = "lily.evans@example.com",
                Education = "Literature",
                Semester = 2
            },
            new Student
            {
                Name = "Michael Jordan",
                Email = "michael.jordan@example.com",
                Education = "Mathematics",
                Semester = 5
            },
            new Student
            {
                Name = "Nina Adams",
                Email = "nina.adams@example.com",
                Education = "Biology",
                Semester = 3
            },
            new Student
            {
                Name = "Oscar Wilde",
                Email = "oscar.wilde@example.com",
                Education = "Engineering",
                Semester = 1
            },
            new Student
            {
                Name = "Pamela Anderson",
                Email = "pamela.anderson@example.com",
                Education = "Physics",
                Semester = 4
            },
            new Student
            {
                Name = "Quentin Tarantino",
                Email = "quentin.tarantino@example.com",
                Education = "Literature",
                Semester = 3
            },
            new Student
            {
                Name = "Rachel Green",
                Email = "rachel.green@example.com",
                Education = "Biology",
                Semester = 2
            },
            new Student
            {
                Name = "Steve Jobs",
                Email = "steve.jobs@example.com",
                Education = "Computer Science",
                Semester = 5
            },
            new Student
            {
                Name = "Tina Fey",
                Email = "tina.fey@example.com",
                Education = "Mathematics",
                Semester = 1
            },
            new Student
            {
                Name = "Ursula K. Le Guin",
                Email = "ursula.leguin@example.com",
                Education = "Literature",
                Semester = 4
            },
            new Student
            {
                Name = "Victor Hugo",
                Email = "victor.hugo@example.com",
                Education = "Biology",
                Semester = 3
            },
            new Student
            {
                Name = "Wendy Davis",
                Email = "wendy.davis@example.com",
                Education = "Engineering",
                Semester = 2
            }
            );
            context.SaveChanges();
        }
    }
}
