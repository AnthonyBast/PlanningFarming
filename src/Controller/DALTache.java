package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import M�tier.Connexion;
import M�tier.Tache;

public class DALTache {
	public Tache getTacheById(int idTache){
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Tache tache = null;
		DALObjetTache DALObjetTache = new DALObjetTache();

		try {
			String sql = "SELECT * FROM Tache WHERE idTache = "+idTache+";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				tache = new Tache(myRs.getInt("idTache"),myRs.getString("libelle"),myRs.getString("description"),myRs.getInt("dureeMin"),DALObjetTache.getAllObjetTacheById(idTache));		
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
		
		return tache;
	}

	public void createTache(String libelle, String description, int dureeMin) {
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql = "insert into tache (libelle,description,dureemin) values (\"" + libelle + "\",\"" + description + "\"," + dureeMin + ");";
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

	public Tache getTacheByLibelle(String libelle, String description) {
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Tache tache = null;
		DALObjetTache DALObjetTache = new DALObjetTache();

		try {
			String sql = "SELECT * FROM Tache WHERE libelle = \""+libelle+"\" and description = \"" + description + "\";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			if (myRs.next()){
				tache = new Tache(myRs.getInt("idTache"),myRs.getString("libelle"),myRs.getString("description"),myRs.getInt("dureeMin"),DALObjetTache.getAllObjetTacheById(myRs.getInt("idTache")));		
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
		
		return tache;
	}

	public void updateTache(int idTache, String libelle, String description, String dureeMin) {
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql =  "UPDATE Tache set libelle=\"" + libelle + "\", description = \"" + description + "\", dureeMin = " + dureeMin + " WHERE idTache="+idTache+";";
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
