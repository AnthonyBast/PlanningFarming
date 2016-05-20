package Métier;

import java.sql.Date;

public class EffectuerTache {
	private Utilisateur utilisateur;
	private Tache tache;
	private Boolean isEffectuer;
	private Date dateHeureDebut;
	private Date dateHeureFin;
	
	public EffectuerTache(){
		this.utilisateur = new Utilisateur();
		this.tache = new Tache();
		this.isEffectuer = false;
		this.dateHeureDebut = new Date(0);
		this.dateHeureFin = new Date(0);
	}
	
	public EffectuerTache(Utilisateur utilisateur, Tache tache, Boolean isEffectuer,
			Date dateHeureDebut, Date dateHeureFin){
		this.utilisateur = utilisateur;
		this.tache = tache;
		this.isEffectuer = isEffectuer;
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public Boolean getIsEffectuer() {
		return isEffectuer;
	}

	public void setIsEffectuer(Boolean isEffectuer) {
		this.isEffectuer = isEffectuer;
	}

	public Date getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(Date dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	public Date getDateHeureFin() {
		return dateHeureFin;
	}

	public void setDateHeureFin(Date dateHeureFin) {
		this.dateHeureFin = dateHeureFin;
	}
}
