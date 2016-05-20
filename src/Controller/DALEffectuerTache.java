package Controller;

import java.sql.SQLException;
import java.sql.Statement;

import Métier.Connexion;

public class DALEffectuerTache {
	public void updateIsEffectuer(int idUtilisateur){
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql = "UPDATE EffectuerTache set isEffectuer=1 WHERE idUtilisateur="+idUtilisateur+";" ;
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
