package Métier;

import java.sql.Date;

public class EffectuerTache {
	private Utilisateur utilisateur;
	private Tache tache;
	private Boolean isEffectuer;
	
	
	public EffectuerTache(){
		this.utilisateur = new Utilisateur();
		this.tache = new Tache();
		this.isEffectuer = false;
	}
	
	public EffectuerTache(Utilisateur utilisateur, Tache tache, Boolean isEffectuer){
		this.utilisateur = utilisateur;
		this.tache = tache;
		this.isEffectuer = isEffectuer;
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
}
