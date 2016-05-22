package Controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Métier.Connexion;
import Métier.EffectuerTache;

public class DALEffectuerTache {
	public void updateIsEffectuer(int idUtilisateur, int idTache){
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql = "UPDATE EffectuerTache set isEffectuer=1 WHERE idUtilisateur="+idUtilisateur+" and idTache ="+idTache+";" ;
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
	
	public void insertEffectuerTache(int idUtilisateur, int idTache){
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql = "insert into EffectuerTache values(" + idUtilisateur + "," + idTache + ",0);" ;
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
	public EffectuerTache getEffectuerTacheById(int idTache, int idUtilisateur){
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		EffectuerTache effectuerTache = null;
		DALTache DALTache = new DALTache();
		DALUtilisateur DALUtilisateur = new DALUtilisateur();

		try {
			String sql = "SELECT * FROM EffectuerTache WHERE idTache = "+idTache+" and idUtilisateur = "+idUtilisateur+";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				effectuerTache = new EffectuerTache(DALUtilisateur.getUtilisateurById(idUtilisateur),DALTache.getTacheById(idTache),myRs.getBoolean("isEffectuer"));		
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
		
		return effectuerTache;
	}

	public void updateEffectuerTache(int idUtilisateur, int idTache, String dateDebut, String dateFin) {
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql = "UPDATE EffectuerTache set dateHeureDebut=\"" + dateDebut + "\", dateHeureFin = \"" + dateFin + "\" WHERE idUtilisateur="+idUtilisateur+" and idTache = " + idTache + ";" ;
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

	public void deleteEffectuerTacheById(int idTache, int idUtilisateur) {
		Connexion myConnect = new Connexion();
		Statement myStmt = null;
		
		try {
			String sql = "delete from effectuertache where idTache="+idTache+ " and idUtilisateur="+idUtilisateur+";";
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
