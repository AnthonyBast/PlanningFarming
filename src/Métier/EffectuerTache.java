package Métier;

import java.sql.Date;

public class EffectuerTache {
	private Utilisateur utilisateur;
	private Tache tache;
	private Boolean isEffectuer;
	private Date dateHeureDebut;
	private Date dateHeureFin;
	private int HeureDebut;
	private int MinuteDebut;
	private int HeureFin;
	private int MinuteFin;
	
	public EffectuerTache(){
		this.utilisateur = new Utilisateur();
		this.tache = new Tache();
		this.isEffectuer = false;
		this.dateHeureDebut = new Date(0);
		this.dateHeureFin = new Date(0);
		this.HeureDebut = 0;
		this.MinuteDebut = 0;
		this.HeureFin = 0;
		this.MinuteFin = 0;
	}
	
	public EffectuerTache(Utilisateur utilisateur, Tache tache, Boolean isEffectuer,
			Date dateHeureDebut, Date dateHeureFin, int HeureDebut, int MinuteDebut, int HeureFin, int MinuteFin ){
		this.utilisateur = utilisateur;
		this.tache = tache;
		this.isEffectuer = isEffectuer;
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.HeureDebut = HeureDebut;
		this.MinuteDebut = MinuteDebut;
		this.HeureFin = HeureFin;
		this.MinuteFin = MinuteFin;
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
	public int getHeureDebut() {
		return HeureDebut;
	}
	public void setHeureDebut(int heureDebut) {
		HeureDebut = heureDebut;
	}
	public int getMinuteDebut() {
		return MinuteDebut;
	}
	public void setMinuteDebut(int minuteDebut) {
		MinuteDebut = minuteDebut;
	}
	public int getHeureFin() {
		return HeureFin;
	}
	public void setHeureFin(int heureFin) {
		HeureFin = heureFin;
	}
	public int getMinuteFin() {
		return MinuteFin;
	}
	public void setMinuteFin(int minuteFin) {
		MinuteFin = minuteFin;
	}	
}
