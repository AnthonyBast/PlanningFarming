package Métier;

public class Utilisateur {
	private int idUtilisateur;
	private String nom;
	private String prenom;
	private String ville;
	private String codePostale;
	private String pseudo;
	private String motDePasse;
	private String statut;
	
	
	public Utilisateur(){
		this.idUtilisateur = 0;
		this.nom = "";
		this.prenom = "";
		this.ville = "";
		this.codePostale = "";
		this.pseudo = "";
		this.motDePasse = "";
		this.statut = "";
	}
	
	public Utilisateur(int idUtilisateur, String nom, String prenom, String ville,
			String codePostale, String pseudo, String motDePasse, String statut){
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.codePostale = codePostale;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.statut = statut;
	}
	
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}	
}
