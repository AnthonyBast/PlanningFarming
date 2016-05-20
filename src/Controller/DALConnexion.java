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

import Exception.Exception;
import Métier.Connexion;
import Métier.Utilisateur;

public class DALConnexion extends HttpServlet {
	
	private static final long serialVersionUID = -2135238259758756344L;

	// Méthode doGet qui permet de récupérer les informations de la page précédente (non utilisée du coup)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp")
			.forward(request, response);
	}

	// Méthode doPost qui est utilisée lors du clic sur le bouton se connecter 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur monUtil;
		String pseudo = request.getParameter("login");
		String mdp = request.getParameter("pass");
		if (pseudo.equals("")) {
			Exception exc = new Exception("Le pseudo ne doit pas être vide.");
			request.setAttribute("error", exc.getMessage());
			request.getRequestDispatcher("/index.jsp")
			.forward(request, response);
			   //response.sendRedirect("index.jsp");
		}
		else {
			if (mdp.equals("")) {
				Exception exc = new Exception("Le mot de passe ne doit pas être vide.");
				request.setAttribute("error", exc.getMessage());
				request.getRequestDispatcher("/index.jsp")
				.forward(request, response);
			}
			else {
				try {
			    monUtil = getUserByLogin(pseudo, mdp);
			    HttpSession session = request.getSession(true);
				session.setAttribute(Integer.toString(monUtil.getIdUtilisateur()), "idUtilisateur");
				response.sendRedirect("accueil.jsp");
				}
				catch (Exception Exception) {
					 request.setAttribute("error", Exception.getMessage());
					   response.sendRedirect("index.jsp");
				}
			}
		}
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
		}
		catch (SQLException exc) {
			connect.getLogger().severe(exc.toString());
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
