package controllers;

import models.Chat;
import models.Statut;
import models.Repositories.Entrepots;

import org.apache.log4j.BasicConfigurator;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import Forms.CatFound;
import Forms.CatLost;

import com.google.gson.Gson;

public class PlanController extends Controller {

	public PlanController() {
		BasicConfigurator.configure();
	}

	public static Result plan() {
		return displayMessage("");
	}

	public static Result displayMessage(String message) {
		Entrepots.start();
		Gson gson = new Gson();
		String cats = gson.toJson(Entrepots.chats().tous());
		Form<CatLost> lostCatForm = form(CatLost.class);
		Form<CatFound> lostCatForm2 = form(CatFound.class);
		Entrepots.flushAndStop();

		return ok(views.html.plan.render(lostCatForm, lostCatForm2, cats, message));
	}

	public static Result submitFormLostCat() {
		CatLost form = form(CatLost.class).bindFromRequest().get();
		FilePart picture = request().body().asMultipartFormData().getFile("photo");

		Entrepots.start();
		Chat chat = Chat.créer(form.getNom(), form.getCouleur(), form.getTaille(), form.getLatlng(), Statut.PERDU);
		chat.setPhoto(picture.getFile());
		Entrepots.chats().ajouter(chat);
		Entrepots.flushAndStop();

		return redirect(routes.PlanController.displayMessage(form.getNom()));
	}

	public static Result submitFormFoundCat() {
		CatFound form = form(CatFound.class).bindFromRequest().get();
		FilePart picture = request().body().asMultipartFormData().getFile("photo2");

		Entrepots.start();
		Chat chat = Chat.créer(form.getNom2(), form.getCouleur2(), form.getTaille2(), form.getLatlng2(), Statut.TROUVE);
		chat.setPhoto(picture.getFile());
		Entrepots.chats().ajouter(chat);
		Entrepots.flushAndStop();

		return redirect(routes.PlanController.displayMessage(form.getNom2()));
	}

	public static Result ajax(String name, String location) {
		return ok("Here's my server-side data " + name);
	}
}