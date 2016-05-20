package Métier;

public class Objet {
	private int idObjet;
	private String nom;
	private Cible cible;
	
	public Objet(){
		this.idObjet = 0;
		this.nom = "";
		this.cible = new Cible();
	}
	
	public Objet(int idObjet, String nom, Cible cible){
		this.idObjet = idObjet;
		this.nom = nom;
		this.cible = cible;
	}

	public int getIdObjet() {
		return idObjet;
	}

	public void setIdObjet(int idObjet) {
		this.idObjet = idObjet;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Cible getCible() {
		return cible;
	}

	public void setCible(Cible cible) {
		this.cible = cible;
	}	
}
