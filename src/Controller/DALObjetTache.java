package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Métier.Connexion;
import Métier.Objet;
import Métier.ObjetTache;

public class DALObjetTache {
	public ArrayList<ObjetTache> getAllObjetTacheById(int idTache){
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		ObjetTache objetTache = null;
		DALObjet DALObjet = new DALObjet();
		ArrayList<ObjetTache> listeObjets = new ArrayList<ObjetTache>();

		try {
			String sql = "SELECT * FROM ObjetTache WHERE idTache = "+idTache+";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				objetTache = new ObjetTache(DALObjet.getObjetById(myRs.getInt("idObjet")),myRs.getInt("nbDrop"));	
				listeObjets.add(objetTache);
			}
			
		}
		catch (SQLException exc) {
			connect.getLogger().severe(exc.toString());
		}
		finally {
			try {
				myRs.close();
				myStmt.close();
			}
			catch (Exception exc) {
				connect.getLogger().severe(exc.toString());
			}
		
		}
		
		return listeObjets;
	}

	public void addObjetToTache(int idTache, String libelleObjet, int nbDrop) {
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		DALObjet DALObjet = new DALObjet();
		Objet objet = DALObjet.getObjetByLibelle(libelleObjet);
		
		try {
			String sql = "insert into objettache (idTache,idObjet,nbDrop) values (" + idTache + "," + objet.getIdObjet() + "," + nbDrop + ");";
			myStmt = myConnect.getConnexion().createStatement();
			myStmt.executeUpdate(sql);
		}
		catch(SQLException exc) {
			myConnect.getLogger().severe(exc.toString());
			myConnect.getLogger().severe("Echec");
		}
		finally{
			try {
				myStmt.close();
			}
			catch (Exception exc) {
				myConnect.getLogger().severe(exc.toString());
				myConnect.getLogger().severe("Echec");
			}
		}
	}

	public  void deleteObjetTacheById(int idTache, int idObjet) {
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql = "delete from objettache where idTache="+idTache+ " and idObjet="+idObjet+";";
			myStmt = myConnect.getConnexion().createStatement();
			myStmt.executeUpdate(sql);
		}
		catch(SQLException exc) {
			myConnect.getLogger().severe(exc.toString());
			myConnect.getLogger().severe("Echec");
		}
		finally{
			try {
				myStmt.close();
			}
			catch (Exception exc) {
				myConnect.getLogger().severe(exc.toString());
				myConnect.getLogger().severe("Echec");
			}
		}
	}
}
