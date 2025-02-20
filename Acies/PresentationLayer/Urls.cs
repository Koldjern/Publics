namespace PresentationLayer;

public static class Urls
{
	public static void AddUrls(this IEndpointRouteBuilder app)
	{
		app.MapDefaultControllerRoute();
		// add urls prettyfication
	}
}
