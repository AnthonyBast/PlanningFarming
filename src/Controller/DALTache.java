package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Métier.Connexion;
import Métier.Tache;

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
}
