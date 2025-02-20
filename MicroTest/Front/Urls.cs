namespace Front;

public static class Urls
{
	public static void AddPaths(this WebApplication app)
	{
		//Home
		app.MapControllerRoute("indexFull",
			"ProductsStudents/Category-{category}/Education-{education}/ProductPage-{productPage:int}/StudentPage-{studentPage:int}",
			new { Controller = "Home", action = "Index" });
		app.MapControllerRoute("indexCategory",
			"ProductsStudents/Category-{category}/ProductPage-{productPage:int}/StudentPage-{studentPage:int}",
			new { Controller = "Home", action = "Index" });
		app.MapControllerRoute("indexCategory",
			"ProductsStudents/Education-{education}/ProductPage-{productPage:int}/StudentPage-{studentPage:int}",
			new { Controller = "Home", action = "Index" });
		app.MapControllerRoute("indexPages",
			"ProductsStudents/ProductPage-{productPage:int}/StudentPage-{studentPage:int}",
			new { Controller = "Home", action = "Index" });
		//
	}
}
