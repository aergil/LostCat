package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.contact;
import views.html.index;
import views.html.links;

public class Application extends Controller {

	public static Result links() {
		return ok(links.render());
	}

	public static Result index() {
		return ok(index.render());
	}

	public static Result contact() {
		return ok(contact.render());
	}
}
