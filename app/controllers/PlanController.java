package controllers;

import models.Chat;
import models.Statut;
import models.Repositories.Entrepots;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.foundCat;
import views.html.lostCat;
import views.html.plan;
import Forms.CatFound;
import Forms.CatLost;

import com.google.gson.Gson;

public class PlanController extends Controller {

	public static Result plan() {
		Entrepots.start();
		Gson gson = new Gson();
		String cats = gson.toJson(Entrepots.chats().tous());
		Entrepots.flushAndStop();

		return ok(plan.render(cats));
	}

	public static Result lostCat() {
		return ok(lostCat.render(form(CatLost.class)));
	}

	public static Result foundCat() {
		return ok(foundCat.render(form(CatFound.class)));
	}

	public static Result addLostCat() {
		CatLost form = form(CatLost.class).bindFromRequest().get();
		FilePart picture = request().body().asMultipartFormData().getFile("photo");

		Entrepots.start();
		Chat chat = Chat.créer(form.getNom(), form.getCouleur(), form.getTaille(), form.getLatlng(), Statut.PERDU);
		chat.setPhoto(picture.getFile());
		Entrepots.chats().ajouter(chat);
		Entrepots.flushAndStop();

		return redirect(routes.PlanController.plan());
	}

	public static Result addFoundCat() {
		CatFound form = form(CatFound.class).bindFromRequest().get();
		FilePart picture = request().body().asMultipartFormData().getFile("photo");

		Entrepots.start();
		Chat chat = Chat.créer(form.getNom(), form.getCouleur(), form.getTaille(), form.getLatlng(), Statut.TROUVE);
		chat.setPhoto(picture.getFile());
		Entrepots.chats().ajouter(chat);
		Entrepots.flushAndStop();

		return redirect(routes.PlanController.plan());
	}
}