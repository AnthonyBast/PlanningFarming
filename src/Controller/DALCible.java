package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Métier.Cible;
import Métier.Connexion;

public class DALCible {
	public Cible getCibleById(int idCible){
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Cible cible = null;

		try {
			String sql = "SELECT * FROM Cible WHERE idCible = "+idCible+";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				cible = new Cible(myRs.getInt("idCible"),myRs.getString("nom"),myRs.getString("lieu"),myRs.getString("typeCible"));		
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
		
		return cible;
	}
}
