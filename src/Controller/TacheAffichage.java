package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import M�tier.EffectuerTache;
import M�tier.Objet;
import M�tier.Tache;
import M�tier.Utilisateur;

public class TacheAffichage extends HttpServlet{
	
		private static final long serialVersionUID = -2135238259758756344L;

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/index.html")
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
			Date dateTache = new Date(2016,05,22);	
			ArrayList<Objet> listeDesObjets = DALObjet.getAllObjet();
			request.setAttribute("listeDesObjets", listeDesObjets);
			ArrayList<Utilisateur> listeDesUtilisateurs = DALUtilisateur.getAllUtilisateur();
			request.setAttribute("listeDesUtilisateurs", listeDesUtilisateurs);			
			
			Utilisateur utilisateur = DALUtilisateur.getUtilisateurById(1);
			utilisateur.setStatut("admin");
			//Utilisateur utilisateur = DALUtilisateur.getUtilisateurById((int)session.getAttribute("idUtilisateur"));
			//dateTache = (Date)session.getAttribute("date");
			if(utilisateur.getStatut().equals("joueur")){
				if(request.getParameter("Effectuer") != null){
					//DALEffectuerTache.updateIsEffectuer((int)session.getAttribute("idUtilisateur"));
					DALEffectuerTache.updateIsEffectuer(1);
				}
				Tache tache = DALTache.getTacheById(1);
				//Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
				effectuerTache = DALEffectuerTache.getEffectuerTacheById(tache.getIdTache(),1);
				//DALEffectuerTache.getEffectuerTacheById(tache.getIdTache(),(int)session.getAttribute("idUtilisateur"));
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
					DALTache.updateTache((int)session.getAttribute("idTache"),request.getParameter("libelle"),request.getParameter("description"),request.getParameter("dureeMin"));
					DALEffectuerTache.updateEffectuerTache(2, (int)session.getAttribute("idTache"), dateDebut, dateFin);
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
					request.setAttribute("tache", tache);
					//session.setAttribute("listeUtilisateurs", listeUtilisateurs);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("removeObjet") != null){
					int idRemove = Integer.parseInt(request.getParameter("removeObjet"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					DALObjetTache.deleteObjetTacheById(tache.getIdTache(),idRemove);
					//DALObjetTache.deleteObjetTacheById((int)session.getAttribute("idUtilisateur"),idRemove);
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("AjoutObjet") != null && request.getParameter("nbDrop") != null && !request.getParameter("nbDrop").equals("")){
					//DALObjetTache.addObjetToTache((int)session.getAttribute("idUtilisateur"),request.getParameter("listeObjets"),request.getParameter("nbDrop"));
					DALObjetTache.addObjetToTache((int)session.getAttribute("idTache"),request.getParameter("listeObjets"),Integer.parseInt(request.getParameter("nbDrop")));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
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
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("removeJoueur") != null){
					int idRemove = Integer.parseInt(request.getParameter("removeJoueur"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					DALEffectuerTache.deleteEffectuerTacheById(tache.getIdTache(),idRemove);
					//DALObjetTache.deleteObjetTacheById((int)session.getAttribute("idUtilisateur"),idRemove);
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					request.setAttribute("tache", tache);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("AjoutJoueur") != null){
					//DALObjetTache.addObjetToTache((int)session.getAttribute("idUtilisateur"),request.getParameter("listeObjets"),request.getParameter("nbDrop"));
					String dateDebut = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+session.getAttribute("heureDebut")+":"+session.getAttribute("minDebut")+":00";
					String dateFin = dateTache.getYear()+"-"+dateTache.getMonth()+"-"+dateTache.getDate()+" "+session.getAttribute("heureFin")+":"+session.getAttribute("minFin")+":00";
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					//DALEffectuerTache.insertEffectuerTache((int)session.getAttribute("idUtilisateur"), tache.getIdTache(), dateDebut, dateFin);
					DALEffectuerTache.insertEffectuerTache(2, tache.getIdTache(), dateDebut, dateFin);					
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					session.setAttribute("libelleEmpty",session.getAttribute("libelleEmpty"));
					session.setAttribute("descriptionEmpty",session.getAttribute("descriptionEmpty"));
					session.setAttribute("dureeEmpty",session.getAttribute("dureeEmpty"));
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					request.setAttribute("tache", tache);					
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				else if(request.getParameter("modifierTache") != null){
					if(request.getParameter("libelle") != null && !request.getParameter("libelle").equals(""))
						request.setAttribute("libelleEmpty",request.getParameter("libelle"));						
					else
						request.setAttribute("libelleEmpty","Le libelle peut pas �tre vide");
					if(request.getParameter("description") != null && !request.getParameter("description").equals(""))
						request.setAttribute("descriptionEmpty",request.getParameter("description"));						
					else
						request.setAttribute("descriptionEmpty","La description ne peut pas �tre vide");
					if(request.getParameter("dureeMin") != null && !request.getParameter("dureeMin").equals(""))
						request.setAttribute("dureeEmpty",request.getParameter("dureeMin"));						
					else
						request.setAttribute("dureeEmpty","La duree ne peut pas �tre vide");

					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					Tache tache = DALTache.getTacheById((int)session.getAttribute("idTache"));
					session.setAttribute("idTache", (int)session.getAttribute("idTache"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					request.setAttribute("tache", tache);
					//session.setAttribute("listeUtilisateurs", listeUtilisateurs);
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}					
				else if (request.getParameter("creer") != null && request.getParameter("libelle") != null && !request.getParameter("libelle").equals("") && request.getParameter("description") != null
						&& !request.getParameter("description").equals("") && request.getParameter("dureeMin")!= null && !request.getParameter("dureeMin").equals("")){
					DALTache.createTache(request.getParameter("libelle"),request.getParameter("description"),Integer.parseInt(request.getParameter("dureeMin")));
					Tache tache = DALTache.getTacheByLibelle(request.getParameter("libelle"),request.getParameter("description"));
					session.setAttribute("libelleEmpty",request.getParameter("libelle"));
					session.setAttribute("descriptionEmpty",request.getParameter("description"));
					session.setAttribute("dureeEmpty",request.getParameter("dureeMin"));
					session.setAttribute("heureDebut",request.getParameter("heureDebut"));
					session.setAttribute("minDebut",request.getParameter("minDebut"));
					session.setAttribute("heureFin",request.getParameter("heureFin"));
					session.setAttribute("minFin",request.getParameter("minFin"));
					ArrayList<Utilisateur> listeDesUtilisateursByTache = DALUtilisateur.getAllUtilisateurByTache(tache.getIdTache());
					request.setAttribute("listeDesUtilisateursByTache", listeDesUtilisateursByTache);
					session.setAttribute("idTache", tache.getIdTache());
					request.getRequestDispatcher("/TacheAdminModifier.jsp")
					.forward(request, response);
				}
				
				else if (request.getParameter("creer") != null) {					
					if(request.getParameter("libelle") != null && !request.getParameter("libelle").equals(""))
						request.setAttribute("libelleEmpty",request.getParameter("libelle"));						
					else
						request.setAttribute("libelleEmpty","Le libelle peut pas �tre vide");
					if(request.getParameter("description") != null && !request.getParameter("description").equals(""))
						request.setAttribute("descriptionEmpty",request.getParameter("description"));						
					else
						request.setAttribute("descriptionEmpty","La description ne peut pas �tre vide");
					if(request.getParameter("dureeMin") != null && !request.getParameter("dureeMin").equals(""))
						request.setAttribute("dureeEmpty",request.getParameter("dureeMin"));						
					else
						request.setAttribute("dureeEmpty","La dur�e ne peut pas �tre vide");

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
