package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Métier.Connexion;
import Métier.Utilisateur;

public class DALConnexion {
	public Utilisateur getUserByLogin(String pseudo, String password){
		
		Connexion connect = new Connexion();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		Utilisateur utilisateur = new Utilisateur();
		

		try {
			String sql = "SELECT * FROM Utilisateur WHERE pseudo = '" + pseudo +"' AND motDePasse = '" + password + "';";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				utilisateur.setIdUtilisateur(myRs.getInt("idUtilisateur"));
				utilisateur.setNom(myRs.getString("nom"));
				utilisateur.setPrenom(myRs.getString("prenom"));
				utilisateur.setVille(myRs.getString("ville"));
				utilisateur.setCodePostale(myRs.getString("codePostale"));
				utilisateur.setPseudo(myRs.getString("pseudo"));
				utilisateur.setMotDePasse(myRs.getString("motDePasse"));
				utilisateur.setStatut(myRs.getString("statut"));
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
