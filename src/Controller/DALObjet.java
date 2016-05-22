package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Métier.Connexion;
import Métier.Objet;

public class DALObjet {
	public Objet getObjetById(int idObjet){
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Objet objet = null;
		DALCible dalCible = new DALCible();

		try {
			String sql = "SELECT * FROM Objet WHERE idObjet = "+idObjet+";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				objet = new Objet(myRs.getInt("idObjet"),myRs.getString("nom"),dalCible.getCibleById(myRs.getInt("idCible")));		
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
		
		return objet;
	}
	public ArrayList<Objet> getAllObjet(int idTache){
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Objet objet = null;
		ArrayList<Objet> listeObjets = new ArrayList<Objet>();
		DALCible dalCible = new DALCible();

		try {
			String sql = "SELECT * FROM Objet WHERE idObjet NOT IN (Select idObjet FROM objettache where idTache="+idTache+");";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				objet = new Objet(myRs.getInt("idObjet"),myRs.getString("nom"),dalCible.getCibleById(myRs.getInt("idCible")));		
				listeObjets.add(objet);
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
	public Objet getObjetByLibelle(String nomObjet) {
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Objet objet = null;
		DALCible dalCible = new DALCible();

		try {
			String sql = "SELECT * FROM Objet WHERE nom = \""+nomObjet+"\";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				objet = new Objet(myRs.getInt("idObjet"),myRs.getString("nom"),dalCible.getCibleById(myRs.getInt("idCible")));		
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
		
		return objet;
	}
}
