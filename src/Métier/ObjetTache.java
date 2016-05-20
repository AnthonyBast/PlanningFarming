package Métier;

public class ObjetTache {
	private Objet Objet;
	private int nbDrop;
	
	public ObjetTache(){
		this.Objet = new Objet();
		this.nbDrop = 0;
	}
	
	public ObjetTache(Objet Objet, int nbDrop){
		this.Objet = Objet;
		this.nbDrop = nbDrop;
	}

	public Objet getObjet() {
		return Objet;
	}

	public void setObjet(Objet Objet) {
		this.Objet = Objet;
	}

	public int getNbDrop() {
		return nbDrop;
	}

	public void setNbDrop(int nbDrop) {
		this.nbDrop = nbDrop;
	}
}
