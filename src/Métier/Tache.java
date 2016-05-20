package Métier;

import java.util.ArrayList;

public class Tache {
	private int idTache;
	private String libelle;
	private String description;
	private int dureeMin;
	private ArrayList<ObjetTache> listeObjetTache;
	
	public Tache(){
		this.idTache = 0;
		this.libelle = "";
		this.description = "";
		this.dureeMin = 0;
		this.listeObjetTache = new ArrayList<ObjetTache>();
	}
	
	public Tache(int idTache, String libelle, String description, int dureeMin, ArrayList<ObjetTache> listeObjetTache){
		this.idTache = idTache;
		this.libelle = libelle;
		this.description = description;
		this.dureeMin = dureeMin;
		this.listeObjetTache = listeObjetTache;
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
	public ArrayList<ObjetTache> getObjetTache() {
		return listeObjetTache;
	}
	public void setObjetTache(ArrayList<ObjetTache> listeObjetTache) {
		this.listeObjetTache = listeObjetTache;
	}	
}
