package models;

import java.io.File;

import models.Repositories.Entrepots;

public class Chat {

	public static Chat créer(String nom, String couleur, String taille, String adresse, Statut statut) {
		Chat cat = new Chat();
		cat.id = ObjectId.getInc();
		cat.nom = nom;
		cat.couleur = couleur;
		cat.taille = taille;
		cat.adresse = adresse;
		cat.statut = statut;
		return cat;
	}

	public void setPhoto(File photo) {
		if (photo == null)
			return;

		iconFileName = Entrepots.images().PutCatIcon(id, photo);
		imageFileName = Entrepots.images().PutCatImage(id, photo);

	}

	public String getNom() {
		return nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public String getTaille() {
		return taille;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public Statut getStatut() {
		return statut;
	}

	public String getId() {
		return id;
	}

	public String nom = "";
	private String couleur = "";
	private String taille = "";
	private String adresse = "";
	private String imageFileName = "";
	private String iconFileName = "";
	private Statut statut = Statut.PERDU;
	private String id;
}
