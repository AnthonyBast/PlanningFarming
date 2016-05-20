package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Métier.Connexion;
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
}
