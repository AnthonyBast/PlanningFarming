package Métier;

public class Cible {
	private int idCible;
	private String nom;
	private String lieu;
	private String typeCible;
	
	public Cible(){
		this.idCible = 0;
		this.nom = "";
		this.lieu = "";
		this.typeCible = "" ;
	}
	
	public Cible(int idCible, String nom, String lieu, String typeCible){
		this.idCible = idCible;
		this.nom = nom;
		this.lieu = lieu;
		this.typeCible = typeCible;
	}

	public int getIdCible() {
		return idCible;
	}

	public void setIdCible(int idCible) {
		this.idCible = idCible;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getTypeCible() {
		return typeCible;
	}

	public void setTypeCible(String typeCible) {
		this.typeCible = typeCible;
	}
}
