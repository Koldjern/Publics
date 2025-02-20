using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Mvc;

namespace PresentationLayer.Models.Errors;

public class ExceptionFilter : IExceptionFilter
{
	public void OnException(ExceptionContext context)
	{
		// Log the exception if needed
		var exceptionMessage = context.Exception.Message;

		// Redirect to the Error action with the exception message
		context.Result = new RedirectToActionResult("Error", "Home", new { message = exceptionMessage });

		// Mark the exception as handled
		context.ExceptionHandled = true;
	}
}
