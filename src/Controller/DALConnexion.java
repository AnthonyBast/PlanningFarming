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

	// Méthode doGet qui permet de récupérer les informations de la page
	// précédente (non utilisée du coup)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	// Méthode doPost qui est utilisée lors du clic sur le bouton se connecter
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On récupère les paramètres passés
		Utilisateur monUtil;
		String pseudo = request.getParameter("login");
		String mdp = request.getParameter("pass");

		// Si le pseudo est vide on met un message d'erreur
		if (pseudo.equals("")) {
			Erreur("Le pseudo ne doit pas être vide", request, response);
		} else {
			// Sinon on vérifie que le mot de passe n'est pas vide non plus, s'il l'est on met un message d'erreur
			if (mdp.equals("")) {
				Erreur("Le mot de passe ne doit pas être vide", request, response);
			} else {
				// Si tout est ok on essaie de récupérer en BDD l'utilisateur qui correspond au pseudo/mdp
				monUtil = getUserByLogin(pseudo, mdp);
				// S'il n'existe pas on initialise son pseudo vide, et donc on met un message d'erreur
				if (monUtil.getPseudo().equals("")) {
					Erreur("Mauvaise combinaison pseudo/mdp", request, response);
				}
				// Si tout est ok on redirige vers l'accueil
				HttpSession session = request.getSession(true);
				session.setAttribute("idUtilisateur", Integer.toString(monUtil.getIdUtilisateur()));
				response.sendRedirect("accueil.jsp");
			}
		}
	}

	// Méthode de génération d'erreur avec en paramètre le message à afficher à l'utilisateur
	private void Erreur(String message, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Exception exc = new Exception(message);
		request.setAttribute("error", exc.getMessage());
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public Utilisateur getUserByLogin(String pseudo, String password) {
		// On se connecte à la BDD
		Connexion connect = new Connexion();
		Statement myStmt = null;
		ResultSet myRs = null;
		Utilisateur utilisateur = new Utilisateur();
		try {
			// Requête pour rechercher l'utilisateur qui se connecte
			String sql = "SELECT * FROM Utilisateur WHERE pseudo = '" + pseudo + "' AND motDePasse = '" + password
					+ "';";
			myStmt = connect.getConnexion().createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				utilisateur.setIdUtilisateur(myRs.getInt("id"));
				utilisateur.setNom(myRs.getString("nom"));
				utilisateur.setPrenom(myRs.getString("prenom"));
				utilisateur.setVille(myRs.getString("ville"));
				utilisateur.setCodePostale(myRs.getString("codePostale"));
				utilisateur.setPseudo(myRs.getString("pseudo"));
				utilisateur.setMotDePasse(myRs.getString("motDePasse"));
				utilisateur.setStatut(myRs.getString("statut"));
			}
			// Si l'utilisateur n'existe pas en BDD on initialise l'utilisateur vide (message d'erreur à la fin)
			if (utilisateur.getPrenom().equals("")) {
				utilisateur = new Utilisateur();
			}
		} catch (SQLException exc) {
			connect.getLogger().severe(exc.toString());
			utilisateur = new Utilisateur();
		} finally {

			try {
				myRs.close();
				myStmt.close();
			} catch (Exception | SQLException exc) {
				connect.getLogger().severe(exc.toString());
			}

		}
		// Enfin on retourne l'utilisateur (s'il est vide cela entraînera une erreur)
		return utilisateur;
	}
}
