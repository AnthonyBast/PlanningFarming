package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Métier.Tache;

public class TacheAffichage extends HttpServlet{
	
		private static final long serialVersionUID = -2135238259758756344L;

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("")
				.forward(request, response);
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(false);			
			DALEffectuerTache DALEffectuerTache = new DALEffectuerTache();
			DALTache DALTache = new DALTache();
			//On récupère la tâche correspondante
			Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
			request.setAttribute("tache", tache);
			//Si la tâche est fini et le bouton "tâche effectuée" confirmer
			if(request.getParameter("Effectuer") != null){
				//Update : la tâche est effectuée
				DALEffectuerTache.updateIsEffectuer((int)session.getAttribute("idUtilisateur"));
			}
			request.getRequestDispatcher("/TacheJoueur.jsp")
			.forward(request, response);
		}
}
