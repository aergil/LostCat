package Forms;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

public class CatLost {

	@Required
	@MaxLength(value = 128)
	private String couleur;

	@Required
	@MaxLength(value = 128)
	private String taille;

	@Required
	@MaxLength(value = 128)
	private String latlng;

	@Required
	@MaxLength(value = 128)
	private String nom;

	private String adresse;

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

}
