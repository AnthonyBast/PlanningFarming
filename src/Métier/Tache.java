package Métier;

import java.sql.Date;
import java.util.ArrayList;

public class Tache {
	private int idTache;
	private String libelle;
	private String description;
	private int dureeMin;
	private ArrayList<ObjetTache> listeObjetTache;
	private Date dateHeureDebut;
	private Date dateHeureFin;
	private int HeureDebut;
	private int MinuteDebut;
	private int HeureFin;
	private int MinuteFin;	

	public Tache(){
		this.idTache = 0;
		this.libelle = "";
		this.description = "";
		this.dureeMin = 0;
		this.listeObjetTache = new ArrayList<ObjetTache>();
	}
	
	public Tache(int idTache, String libelle, String description, int dureeMin, ArrayList<ObjetTache> listeObjetTache, Date dateHeureDebut, Date dateHeureFin, int HeureDebut, int MinuteDebut,int HeureFin, int MinuteFin){
		this.idTache = idTache;
		this.libelle = libelle;
		this.description = description;
		this.dureeMin = dureeMin;
		this.listeObjetTache = listeObjetTache;
		this.dateHeureDebut = dateHeureDebut;
		this.dateHeureFin = dateHeureFin;
		this.HeureDebut = HeureDebut;
		this.MinuteDebut = MinuteDebut;
		this.HeureFin = HeureFin;
		this.MinuteFin = MinuteFin;
	}
	
	public int getIdTache() {
		return idTache;
	}
	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDureeMin() {
		return dureeMin;
	}
	public void setDureeMin(int dureeMin) {
		this.dureeMin = dureeMin;
	}	
	
	public ArrayList<ObjetTache> getListeObjetTache() {
		return listeObjetTache;
	}

	public void setListeObjetTache(ArrayList<ObjetTache> listeObjetTache) {
		this.listeObjetTache = listeObjetTache;
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
