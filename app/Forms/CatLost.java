package Forms;

import play.data.validation.Constraints.Required;

public class CatLost {

	@Required
	private String nom;

	private String couleur;
	private String taille;
	private String latlng;
	private String tatouage;
	private String puce;
	private String race;
	private String sexe;
	private String age;
	private String dateDisparition;
	private String adresse;
	private String proprietaireNomPrenom;
	private String proprietaireEmail;
	private String proprietaireTelephone;
	private String proprietaireAdresse;

	public String getCouleur() {
		return couleur;
	}

	public void setcouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getLatlng() {
		return latlng;
	}

	public void setLatlng(String latlng) {
		this.latlng = latlng;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

}
