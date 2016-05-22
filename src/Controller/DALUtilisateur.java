package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Métier.Connexion;
import Métier.Objet;
import Métier.Utilisateur;

public class DALUtilisateur {
	public Utilisateur getUtilisateurById(int idUtilisateur){
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Utilisateur utilisateur = null;

		try {
			String sql = "SELECT * FROM Utilisateur WHERE id = "+idUtilisateur+";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				utilisateur = new Utilisateur(myRs.getInt("id"),myRs.getString("nom"),myRs.getString("prenom"),myRs.getString("ville"),myRs.getString("codePostale"),myRs.getString("pseudo"),myRs.getString("motDePasse"),myRs.getString("statut"));		
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
		
		return utilisateur;
	}

	public ArrayList<Utilisateur> getAllUtilisateur(int idTache) {
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Utilisateur utilisateur = null;
		ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();

		try {
			String sql = "SELECT * FROM Utilisateur WHERE id NOT IN (Select idUtilisateur FROM effectuertache where idTache="+idTache+");";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				utilisateur = new Utilisateur(myRs.getInt("id"),myRs.getString("nom"),myRs.getString("prenom"),myRs.getString("ville"),myRs.getString("codePostale"),myRs.getString("pseudo"),myRs.getString("motDePasse"),myRs.getString("statut"));		
				listeUtilisateurs.add(utilisateur);
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
		
		return listeUtilisateurs;
	}
	public ArrayList<Utilisateur> getAllUtilisateurByTache(int idTache) {
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Utilisateur utilisateur = null;
		ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();

		try {
			String sql = "SELECT u.* FROM effectuertache et, utilisateur u WHERE u.id=et.idUtilisateur and idTache = "+idTache + ";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				utilisateur = new Utilisateur(myRs.getInt("id"),myRs.getString("nom"),myRs.getString("prenom"),myRs.getString("ville"),myRs.getString("codePostale"),myRs.getString("pseudo"),myRs.getString("motDePasse"),myRs.getString("statut"));		
				listeUtilisateurs.add(utilisateur);
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
		
		return listeUtilisateurs;
	}

	public Utilisateur getUtilisateurByPseudo(String pseudo) {
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Utilisateur utilisateur = null;

		try {
			String sql = "SELECT * FROM Utilisateur WHERE pseudo = \""+pseudo+"\";";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				utilisateur = new Utilisateur(myRs.getInt("id"),myRs.getString("nom"),myRs.getString("prenom"),myRs.getString("ville"),myRs.getString("codePostale"),myRs.getString("pseudo"),myRs.getString("motDePasse"),myRs.getString("statut"));		
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
		
		return utilisateur;		
	}
}
