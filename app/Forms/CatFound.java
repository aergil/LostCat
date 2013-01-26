package Forms;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

public class CatFound {

	@Required
	@MaxLength(value = 128)
	private String couleur2;

	@Required
	@MaxLength(value = 128)
	private String taille2;

	@Required
	@MaxLength(value = 128)
	private String latlng2;

	@Required
	@MaxLength(value = 128)
	private String nom2;

	@Required
	@MaxLength(value = 128)
	private String adresse2;

	public String getCouleur2() {
		return couleur2;
	}

	public void setCouleur2(String couleur) {
		this.couleur2 = couleur;
	}

	public String getTaille2() {
		return taille2;
	}

	public void setTaille2(String taille2) {
		this.taille2 = taille2;
	}

	public String getLatlng2() {
		return latlng2;
	}

	public void setLatlng2(String latlng2) {
		this.latlng2 = latlng2;
	}

	public String getNom2() {
		return nom2;
	}

	public void setNom2(String nom) {
		this.nom2 = nom;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse) {
		this.adresse2 = adresse;
	}
}
