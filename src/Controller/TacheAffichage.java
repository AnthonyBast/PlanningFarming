package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Métier.EffectuerTache;
import Métier.Objet;
import Métier.Tache;
import Métier.Utilisateur;

public class TacheAffichage extends HttpServlet{
	
		private static final long serialVersionUID = -2135238259758756344L;

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/accueil.jsp")
				.forward(request, response);
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession(true);			
			DALEffectuerTache DALEffectuerTache = new DALEffectuerTache();
			DALUtilisateur DALUtilisateur = new DALUtilisateur();
			DALTache DALTache = new DALTache();
			DALObjet DALObjet = new DALObjet();
			DALObjetTache DALObjetTache = new DALObjetTache();
			EffectuerTache effectuerTache = new EffectuerTache();
			Date dateTache = new Date(0);			
			Utilisateur utilisateur = DALUtilisateur.getUtilisateurById((int)session.getAttribute("idUtilisateur"));
			dateTache = (Date)session.getAttribute("date");
			session.setAttribute("idUtilisateur",(int)session.getAttribute("idUtilisateur"));
			session.setAttribute("date",(Date)session.getAttribute("date"));
			
			if(utilisateur.getStatut().equals("joueur")){
				Tache tache = DALTache.getTacheById((int)session.getAttribute("idTacheSelect"));
				if(request.getParameter("Effectuer") != null){
					DALEffectuerTache.updateIsEffectuer((int)session.getAttribute("idUtilisateur"),tache.getIdTache());
				}
				effectuerTache = DALEffectuerTache.getEffectuerTacheById(tache.getIdTache(),utilisateur.getIdUtilisateur());
				request.setAttribute("tache", tache);
				request.setAttribute("effectuerTache", effectuerTache);				
				request.getRequestDispatcher("/TacheJoueur.jsp")
				.forward(request, response);
			}
			else if (utilisateur.getStatut().equals("admin")){
				if (request.getParameter("modifierTache") != null && request.getParameter("libelle") != null && !request.getParameter("libelle").equals("") && request.getParameter("description") != null
						&& !request.getParameter("description").equals("") && request.getParameter("dureeMin")!= null && !request.getParameter("dureeMin").equals("")){
					String dateDebut = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+request.getParameter("heureDebut")+":"+request.getParameter("minDebut")+":00";
					String dateFin = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+request.getParameter("heureFin")+":"+request.getParameter("minFin")+":00";
					DALTache.updateTache((int)session.getAttribute("idTache"),request.getParameter("libelle"),request.getParameter("description"),request.getParameter("dureeMin"),dateDebut,dateFin);
					session.setAttribute("libelleEmpty",request.getParameter("libelle"));
					session.setAttribute("descriptionEmpty",request.getParameter("description"));
					session.setAttribute("dureeEmpty",request.getParameter("dureeMin"));
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("removeObjet") != null){
					int idRemove = Integer.parseInt(request.getParameter("removeObjet"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					DALObjetTache.deleteObjetTacheById(tache.getIdTache(),idRemove);
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",session.getAttribute("heureDebut"));
					session.setAttribute("minDebut",session.getAttribute("minDebut"));
					session.setAttribute("heureFin",session.getAttribute("heureFin"));
					session.setAttribute("minFin",session.getAttribute("minFin"));
					tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("AjoutObjet") != null && request.getParameter("nbDrop") != null && !request.getParameter("nbDrop").equals("")){
					DALObjetTache.addObjetToTache((int)session.getAttribute("idTache"),request.getParameter("listeObjets"),Integer.parseInt(request.getParameter("nbDrop")));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",session.getAttribute("heureDebut"));
					session.setAttribute("minDebut",session.getAttribute("minDebut"));
					session.setAttribute("heureFin",session.getAttribute("heureFin"));
					session.setAttribute("minFin",session.getAttribute("minFin"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);					
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("modiftask") != null){
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTacheSelect"));
					session.setAttribute("idTache", tache.getIdTache());
					session.setAttribute("libelleEmpty",tache.getLibelle());
					session.setAttribute("descriptionEmpty",tache.getDescription());
					session.setAttribute("dureeEmpty",tache.getDureeMin());
					session.setAttribute("heureDebut",tache.getHeureDebut());
					session.setAttribute("minDebut",tache.getMinuteDebut());
					session.setAttribute("heureFin",tache.getHeureFin());
					session.setAttribute("minFin",tache.getMinuteFin());
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("AjoutObjet") != null){
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",session.getAttribute("heureDebut"));
					session.setAttribute("minDebut",session.getAttribute("minDebut"));
					session.setAttribute("heureFin",session.getAttribute("heureFin"));
					session.setAttribute("minFin",session.getAttribute("minFin"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("removeJoueur") != null){
					int idRemove = Integer.parseInt(request.getParameter("removeJoueur"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					DALEffectuerTache.deleteEffectuerTacheById(tache.getIdTache(),idRemove);
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",session.getAttribute("heureDebut"));
					session.setAttribute("minDebut",session.getAttribute("minDebut"));
					session.setAttribute("heureFin",session.getAttribute("heureFin"));
					session.setAttribute("minFin",session.getAttribute("minFin"));
					tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("AjoutJoueur") != null){
					String dateDebut = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+session.getAttribute("heureDebut")+":"+session.getAttribute("minDebut")+":00";
					String dateFin = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+session.getAttribute("heureFin")+":"+session.getAttribute("minFin")+":00";
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					Utilisateur user = DALUtilisateur.getUtilisateurByPseudo(request.getParameter("listeJoueurs"));
					DALEffectuerTache.insertEffectuerTache(user.getIdUtilisateur(), tache.getIdTache());					
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",session.getAttribute("heureDebut"));
					session.setAttribute("minDebut",session.getAttribute("minDebut"));
					session.setAttribute("heureFin",session.getAttribute("heureFin"));
					session.setAttribute("minFin",session.getAttribute("minFin"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);					
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("modifierTache") != null){
					if(request.getParameter("libelle") != null && !request.getParameter("libelle").equals(""))
						request.setAttribute("libelleEmpty",request.getParameter("libelle"));						
					else
						request.setAttribute("libelleEmpty","Le libelle peut pas être vide");
					if(request.getParameter("description") != null && !request.getParameter("description").equals(""))
						request.setAttribute("descriptionEmpty",request.getParameter("description"));						
					else
						request.setAttribute("descriptionEmpty","La description ne peut pas être vide");
					if(request.getParameter("dureeMin") != null && !request.getParameter("dureeMin").equals(""))
						request.setAttribute("dureeEmpty",request.getParameter("dureeMin"));						
					else
						request.setAttribute("dureeEmpty","La duree ne peut pas être vide");

					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					request.setAttribute("tache", tache);
					//session.setAttribute("listeUtilisateurs", listeUtilisateurs);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}					
				else if (request.getParameter("creer") != null && request.getParameter("libelle") != null && !request.getParameter("libelle").equals("") && request.getParameter("description") != null
						&& !request.getParameter("description").equals("") && request.getParameter("dureeMin")!= null && !request.getParameter("dureeMin").equals("")){
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					String dateDebut = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+session.getAttribute("heureDebut")+":"+session.getAttribute("minDebut")+":00";
					String dateFin = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+session.getAttribute("heureFin")+":"+session.getAttribute("minFin")+":00";
					DALTache.createTache(request.getParameter("libelle"),request.getParameter("description"),Integer.parseInt(request.getParameter("dureeMin")),dateDebut,dateFin);
					Tache tache = DALTache.getTacheByLibelle(request.getParameter("libelle"),request.getParameter("description"));
					session.setAttribute("libelleEmpty",request.getParameter("libelle"));
					session.setAttribute("descriptionEmpty",request.getParameter("description"));
					session.setAttribute("dureeEmpty",request.getParameter("dureeMin"));
					
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet(tache.getIdTache());
					request.setAttribute("listeDesObjets", listeDesObjets);
					ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur(tache.getIdTache());
					request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);		
					session.setAttribute("idTache", tache.getIdTache());
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				
				else if (request.getParameter("creer") != null) {					
					if(request.getParameter("libelle") != null && !request.getParameter("libelle").equals(""))
						request.setAttribute("libelleEmpty",request.getParameter("libelle"));						
					else
						request.setAttribute("libelleEmpty","Le libelle peut pas être vide");
					if(request.getParameter("description") != null && !request.getParameter("description").equals(""))
						request.setAttribute("descriptionEmpty",request.getParameter("description"));						
					else
						request.setAttribute("descriptionEmpty","La description ne peut pas être vide");
					if(request.getParameter("dureeMin") != null && !request.getParameter("dureeMin").equals(""))
						request.setAttribute("dureeEmpty",request.getParameter("dureeMin"));						
					else
						request.setAttribute("dureeEmpty","La durée ne peut pas être vide");

					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					
					request.getRequestDispatcher("/TacheAdminCreate.jsp")
					.forward(request, response);
				}
				else {
					session.setAttribute("heureDebut",0);
					session.setAttribute("minDebut",0);
					session.setAttribute("heureFin",0);
					session.setAttribute("minFin",0);
					request.getRequestDispatcher("/TacheAdminCreate.jsp")
					.forward(request, response);
				}
			}
			else
				System.out.println("Bug, il n'y a que des \"admin\" ou des \"joueur\"");
		}
}
