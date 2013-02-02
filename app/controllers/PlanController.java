package controllers;

import models.Chat;
import models.Statut;
import models.Repositories.Entrepots;
import models.Repositories.SessionManager;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.detailCat;
import views.html.foundCat;
import views.html.lostCat;
import views.html.plan;
import Forms.CatFound;
import Forms.CatLost;

import com.google.gson.Gson;

public class PlanController extends Controller {

	public static Result plan() {
		SessionManager.getInstance().start();
		Gson gson = new Gson();

		String cats = gson.toJson(Entrepots.chats().tous()).replaceAll("\\\\", "\\\\\\\\");
		SessionManager.getInstance().flushAndStop();

		return ok(plan.render(cats));
	}

	public static Result lostCatForm() {
		return ok(lostCat.render(form(CatLost.class)));
	}

	public static Result foundCatForm() {
		return ok(foundCat.render(form(CatFound.class)));
	}

	public static Result addLostCat() {
		CatLost form = form(CatLost.class).bindFromRequest().get();
		FilePart picture = request().body().asMultipartFormData().getFile("photo");

		SessionManager.getInstance().start();
		Chat chat = Chat.créer(form.getNom(), Statut.PERDU);
		chat.setCouleur(form.getCouleur());
		chat.setTaille(form.getTaille());
		chat.setLatLng(form.getLatlng());
		chat.setAdresse(form.getAdresse());
		chat.setTatouage(form.getTatouage());
		chat.setPuce(form.getPuce());
		chat.setRace(form.getRace());
		chat.setSexe(form.getSexe());
		chat.setAge(form.getAge());
		chat.setDateDisparition(form.getDateDisparition());
		chat.setProprietaireEmail(form.getProprietaireEmail());
		chat.setProprietaireNomPrenom(form.getProprietaireNomPrenom());
		chat.setProprietaireTelephone(form.getProprietaireTelephone());
		chat.setProprietaireAdresse(form.getProprietaireAdresse());
		if (picture != null)
			chat.setPhoto(picture.getFile());
		Entrepots.chats().ajouter(chat);
		SessionManager.getInstance().flushAndStop();

		return redirect(routes.PlanController.plan());
	}

	public static Result addFoundCat() {
		CatFound form = form(CatFound.class).bindFromRequest().get();
		FilePart picture = request().body().asMultipartFormData().getFile("photo");

		SessionManager.getInstance().start();
		Chat chat = Chat.créer(form.getNom(), Statut.TROUVE);
		chat.setPhoto(picture.getFile());
		Entrepots.chats().ajouter(chat);
		SessionManager.getInstance().flushAndStop();

		return redirect(routes.PlanController.plan());
	}

	public static Result detailCat(String id) {
		SessionManager.getInstance().start();
		Chat chat = Entrepots.chats().parId(id);
		SessionManager.getInstance().flushAndStop();

		return ok(detailCat.render(chat));
	}
}