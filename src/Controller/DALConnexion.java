package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.*;
import Exception.Exception;
import Métier.Connexion;
import Métier.Utilisateur;

public class DALConnexion extends HttpServlet {
	
	private static final long serialVersionUID = -2135238259758756344L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp")
			.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur monUtil;
		String pseudo = request.getParameter("login");
		String mdp = request.getParameter("pass");
		
		
		if (pseudo.equals("")) {
			Exception("Le pseudo ne doit pas être vide", request, response);
		}
		else {
			if (mdp.equals("")) {
				
				Exception("Le mot de passe ne doit pas être vide", request, response);
			}
			else {
				try {
			    monUtil = getUserByLogin(pseudo, mdp);
			    if (monUtil.getPseudo().equals("")) {
					Exception("Mauvaise combinaison pseudo/mdp", request, response);
			    }
			    else {
			    	 HttpSession session = request.getSession(true);
						session.setAttribute("idUtilisateur",Integer.toString(monUtil.getIdUtilisateur()));
						request.getRequestDispatcher("/CalendrierAffichage")
						   .forward(request, response);
			    }
			   
				}
				catch (Exception Exception) {
					Exception("Mauvaise combinaison pseudo/mdp", request, response);
				}
			}
		}
	}
	
	public void Exception(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   request.setAttribute("error", message);
		   request.getRequestDispatcher("/index.jsp")
		   .forward(request, response);
	}
	
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
				utilisateur.setIdUtilisateur(myRs.getInt("id"));
				utilisateur.setNom(myRs.getString("nom"));
				utilisateur.setPrenom(myRs.getString("prenom"));
				utilisateur.setVille(myRs.getString("ville"));
				utilisateur.setCodePostale(myRs.getString("codePostale"));
				utilisateur.setPseudo(myRs.getString("pseudo"));
				utilisateur.setMotDePasse(myRs.getString("motDePasse"));
				utilisateur.setStatut(myRs.getString("statut"));
			}
			
			if (utilisateur.getPseudo().equals("")) {
				utilisateur = new Utilisateur();
			}
		}
		catch (SQLException exc) {
			connect.getLogger().severe(exc.toString());
			utilisateur = new Utilisateur();
		}
		finally {

			try {
				myRs.close();
				myStmt.close();
			}
			catch (Exception | SQLException exc) {
				connect.getLogger().severe(exc.toString());
			}
		
		}
		return utilisateur;
	}	
}
