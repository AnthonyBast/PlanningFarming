package Métier;

public class Tache {
	private int idTache;
	
	private int dureeMin;
	private Objet objet;
	
	public Tache(){
		this.idTache = 0;
		this.dureeMin = 0;
		this.objet = new Objet();
	}
	
	public Tache(int idTache, int dureeMin, Objet objet){
		this.idTache = idTache;
		this.dureeMin = dureeMin;
		this.objet = objet;
	}
	
	public int getIdTache() {
		return idTache;
	}
	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}
	public int getDureeMin() {
		return dureeMin;
	}
	public void setDureeMin(int dureeMin) {
		this.dureeMin = dureeMin;
	}
	public Objet getObjet() {
		return objet;
	}
	public void setObjet(Objet objet) {
		this.objet = objet;
	}
}
