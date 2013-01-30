package models;

import java.io.File;

import models.Repositories.Entrepots;

public class Chat {

	public static Chat créer(String nom, Statut perdu) {
		Chat cat = new Chat();
		cat.id = ObjectId.getInc();
		cat.nom = nom;
		return cat;
	}

	public Statut getStatut() {
		return statut;
	}

	public String getId() {
		return id;
	}

	public void setPhoto(File photo) {
		if (photo == null)
			return;

		setIconFileName(Entrepots.images().PutCatIcon(id, photo));
		setImageFileName(Entrepots.images().PutCatImage(id, photo));

	}

	public String getNom() {
		return nom;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String string) {
		couleur = string;
	}

	public String getTaille() {
		return taille;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getIconFileName() {
		return iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		this.iconFileName = iconFileName;
	}

	public String getLatlng() {
		return getLatLng();
	}

	public String getTatouage() {
		return tatouage;
	}

	public void setTatouage(String tatouage) {

		this.tatouage = tatouage;
	}

	public String getPuce() {
		return puce;
	}

	public void setPuce(String puce) {

		this.puce = puce;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDateDisparition() {
		return dateDisparition;
	}

	public void setDateDisparition(String dateDisparition) {
		this.dateDisparition = dateDisparition;
	}

	public String getProprietaireNomPrenom() {
		return proprietaireNomPrenom;
	}

	public void setProprietaireNomPrenom(String proprietaireNomPrenom) {
		this.proprietaireNomPrenom = proprietaireNomPrenom;
	}

	public String getProprietaireEmail() {
		return proprietaireEmail;
	}

	public void setProprietaireEmail(String proprietaireEmail) {
		this.proprietaireEmail = proprietaireEmail;
	}

	public String getProprietaireTelephone() {
		return proprietaireTelephone;
	}

	public void setProprietaireTelephone(String proprietaireTelephone) {
		this.proprietaireTelephone = proprietaireTelephone;
	}

	public String getProprietaireAdresse() {
		return proprietaireAdresse;
	}

	public void setProprietaireAdresse(String proprietaireAdresse) {
		this.proprietaireAdresse = proprietaireAdresse;
	}

	public String getLatLng() {
		return latLng;
	}

	public void setLatLng(String latLng) {

		this.latLng = latLng;
	}

	public String nom = "";
	private String couleur = "";
	private String taille = "";
	private String adresse = "";
	private String imageFileName = "";
	private String iconFileName = "";
	private final Statut statut = Statut.PERDU;
	private String id;
	private String latLng;
	private String tatouage;
	private String puce;
	private String race;
	private String sexe;
	private String age;
	private String dateDisparition;
	private String proprietaireEmail;
	private String proprietaireNomPrenom;
	private String proprietaireTelephone;
	private String proprietaireAdresse;

}
