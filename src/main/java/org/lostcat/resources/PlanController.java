package org.lostcat.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.lostcat.models.Chat;
import org.lostcat.models.Statut;
import org.lostcat.models.repositories.Entrepots;
import org.lostcat.models.repositories.SessionManager;

import com.google.gson.Gson;
import com.sun.jersey.api.view.Viewable;
import com.sun.jersey.multipart.FormDataParam;

@Path(Paths.plan)
@Produces(Constantes.Text_Html_UTF8)
public class PlanController {

	@GET
	@Path("/plan")
	public Viewable plan() {
		SessionManager.getInstance().start();
		List<Chat> chats = Entrepots.chats().tous();
		String cats = new Gson().toJson(chats).replaceAll("\\\\", "\\\\\\\\");
		SessionManager.getInstance().flushAndStop();
		Map<String, String> datamodel = new HashMap<String, String>();

		datamodel.put("cats", cats);

		return new Viewable("/plan", datamodel);
	}

	@GET
	@Path("/lostCatForm")
	public Viewable lostCatForm() {
		return new Viewable("/lostCatForm");
	}

	@GET
	@Path("/foundCatForm")
	public Viewable foundCatForm() {
		return new Viewable("/foundCatForm");
	}

	@POST
	@Path("/addLostCat")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Viewable addLostCat(@FormDataParam("adresse") String adresse,
			@FormDataParam("latlng") String latlng, @FormDataParam("nom") String nom, @FormDataParam("race") String race,
			@FormDataParam("sexe") String sexe, @FormDataParam("couleur") String couleur, @FormDataParam("age") String age,
			@FormDataParam("tatouage") String tatouage, @FormDataParam("puce") String puce, @FormDataParam("dateDisparition") String dateDisparition,
			@FormDataParam("proprietaireNom") String proprietaireNom, @FormDataParam("proprietaireEmail") String proprietaireEmail,
			@FormDataParam("proprietaireTel") String proprietaireTel, @FormDataParam("proprietaireAdresse") String proprietaireAdresse,
			@FormDataParam("photo") InputStream photo) {

		SessionManager.getInstance().start();
		Chat chat = Chat.créer(nom, Statut.PERDU);
		chat.setCouleur(couleur);
		chat.setLatLng(latlng);
		chat.setAdresse(adresse);
		chat.setTatouage(tatouage);
		chat.setPuce(puce);
		chat.setRace(race);
		chat.setSexe(sexe);
		chat.setAge(age);
		chat.setDateDisparition(dateDisparition);
		chat.setProprietaireEmail(proprietaireEmail);
		chat.setProprietaireNomPrenom(proprietaireNom);
		chat.setProprietaireTelephone(proprietaireTel);
		chat.setProprietaireAdresse(proprietaireAdresse);

		if (photo != null) {
			try {
				BufferedImage read = ImageIO.read(photo);
				chat.setIconFileName(Entrepots.images().PutCatIcon(chat.getId().toString(), read));
				chat.setImageFileName(Entrepots.images().PutCatImage(chat.getId().toString(), read));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Entrepots.chats().ajouter(chat);
		SessionManager.getInstance().flushAndStop();

		return plan();
	}

	@POST
	@Path("/addFoundCat")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Viewable addFoundCat(@FormDataParam("adresse") String adresse,
			@FormDataParam("latlng") String latlng, @FormDataParam("nom") String nom, @FormDataParam("race") String race,
			@FormDataParam("sexe") String sexe, @FormDataParam("couleur") String couleur, @FormDataParam("age") String age,
			@FormDataParam("tatouage") String tatouage, @FormDataParam("puce") String puce, @FormDataParam("dateDisparition") String dateDisparition,
			@FormDataParam("proprietaireNom") String proprietaireNom, @FormDataParam("proprietaireEmail") String proprietaireEmail,
			@FormDataParam("proprietaireTel") String proprietaireTel, @FormDataParam("photo") InputStream photo) {

		SessionManager.getInstance().start();
		Chat chat = Chat.créer(nom, Statut.TROUVE);
		chat.setCouleur(couleur);
		chat.setLatLng(latlng);
		chat.setAdresse(adresse);
		chat.setTatouage(tatouage);
		chat.setPuce(puce);
		chat.setRace(race);
		chat.setSexe(sexe);
		chat.setAge(age);
		chat.setDateDisparition(dateDisparition);
		chat.setProprietaireEmail(proprietaireEmail);
		chat.setProprietaireNomPrenom(proprietaireNom);
		chat.setProprietaireTelephone(proprietaireTel);

		if (photo != null) {
			try {
				BufferedImage read = ImageIO.read(photo);
				chat.setIconFileName(Entrepots.images().PutCatIcon(chat.getId().toString(), read));
				chat.setImageFileName(Entrepots.images().PutCatImage(chat.getId().toString(), read));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Entrepots.chats().ajouter(chat);
		SessionManager.getInstance().flushAndStop();

		return plan();
	}

	@GET
	@Path("/detailCat/{id}")
	public Viewable detailCat(@PathParam("id") String id) {
		SessionManager.getInstance().start();
		Chat chat = Entrepots.chats().parId(id);
		SessionManager.getInstance().flushAndStop();

		Map<String, Chat> datamodel = new HashMap<String, Chat>();
		datamodel.put("chat", chat);
		return new Viewable("/detailCat", datamodel);
	}

}