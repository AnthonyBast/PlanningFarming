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
import M�tier.Connexion;
import M�tier.Utilisateur;

public class DALConnexion extends HttpServlet {

	private static final long serialVersionUID = -2135238259758756344L;

	// M�thode doGet qui permet de r�cup�rer les informations de la page
	// pr�c�dente (non utilis�e du coup)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	// M�thode doPost qui est utilis�e lors du clic sur le bouton se connecter
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On r�cup�re les param�tres pass�s
		Utilisateur monUtil;
		String pseudo = request.getParameter("login");
		String mdp = request.getParameter("pass");

		// Si le pseudo est vide on met un message d'erreur
		if (pseudo.equals("")) {
			Erreur("Le pseudo ne doit pas �tre vide", request, response);
		} else {
			// Sinon on v�rifie que le mot de passe n'est pas vide non plus, s'il l'est on met un message d'erreur
			if (mdp.equals("")) {
				Erreur("Le mot de passe ne doit pas �tre vide", request, response);
			} else {
				// Si tout est ok on essaie de r�cup�rer en BDD l'utilisateur qui correspond au pseudo/mdp
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

	// M�thode de g�n�ration d'erreur avec en param�tre le message � afficher � l'utilisateur
	private void Erreur(String message, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Exception exc = new Exception(message);
		request.setAttribute("error", exc.getMessage());
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public Utilisateur getUserByLogin(String pseudo, String password) {
		// On se connecte � la BDD
		Connexion connect = new Connexion();
		Statement myStmt = null;
		ResultSet myRs = null;
		Utilisateur utilisateur = new Utilisateur();
		try {
			// Requ�te pour rechercher l'utilisateur qui se connecte
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
			// Si l'utilisateur n'existe pas en BDD on initialise l'utilisateur vide (message d'erreur � la fin)
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
		// Enfin on retourne l'utilisateur (s'il est vide cela entra�nera une erreur)
		return utilisateur;
	}
}
