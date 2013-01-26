package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.pageerror;

public class ErrorController extends Controller {

	public static Result showError(String error) {
		return ok(pageerror.render(error));
	}
}