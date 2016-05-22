<%@ page import="Métier.*,Controller.*,java.util.ArrayList" %>
<jsp:useBean id="tache" scope="request" class="Métier.Tache" />
<html>
	<head>
		<title>Modify Tâche</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
<body>
	<div id="header">
		<h1>Modifier une tâche</h1>
	</div>
	<div id="content">
		<div>
			<FORM METHOD=POST ACTION="TacheAffichage">
				<p>Libelle : <INPUT VALUE="<% out.println(session.getAttribute("libelleEmpty")); %>" TYPE=TEXT NAME="libelle" size="75"></p>
				<p>Description : <INPUT VALUE="<% out.println(session.getAttribute("descriptionEmpty")); %>" TYPE=TEXT NAME="description" size="150"></p>
				<p>Durée Estimée (en minute) : <INPUT VALUE="<% out.println(session.getAttribute("dureeEmpty")); %>" TYPE=TEXT NAME="dureeMin" size="35"></p>
				<p>Heure de début : 
				<select name="heureDebut" id="heureDebut">
					<%
						for(int i=0;i<24;i++){
							if(Integer.parseInt(session.getAttribute("heureDebut").toString()) != i)
								out.println("<option>" + i + "</option>");
							else
								out.println("<option selected>" + i + "</option>");
						}
					%>
				</select>
				h
				<select name="minDebut" id="minDebut">
					<%
						for(int i=0;i<59;i++){
							if(Integer.parseInt(session.getAttribute("minDebut").toString()) != i)
								out.println("<option>" + i + "</option>");
							else
								out.println("<option selected>" + i + "</option>");
						}
					%>
				</select>
				</p>
				<p>Heure de fin : 
				<select name="heureFin" id="heureFin">
					<%
						for(int i=0;i<24;i++){
							if(Integer.parseInt(session.getAttribute("heureFin").toString()) != i)
								out.println("<option>" + i + "</option>");
							else
								out.println("<option selected>" + i + "</option>");
						}
					%>
				</select>
				h
				<select name="minFin" id="minFin">
					<%
						for(int i=0;i<60;i++){
							if(Integer.parseInt(session.getAttribute("minFin").toString()) != i)
								out.println("<option>" + i + "</option>");
							else
								out.println("<option selected>" + i + "</option>");
						}
					%>
				</select>
				</p>
				
				<INPUT TYPE=SUBMIT VALUE="Modifier" NAME="modifierTache">
			</FORM>
			<FORM METHOD=POST ACTION="TacheAffichage">
				<p>Liste des objets à  drop : </p>
				<table border="1">
  				<tr>
       				<th>Nom Objet</th>
       				<th>Monstre</th>
       				<th>Type Monstre</th>
       				<th>Lieu</th>
       				<th>Nb à drop</th>
       				<th>Supprimer</th>
   				</tr>
   				<%
				for(int i=0;i<tache.getListeObjetTache().size();i++){
					out.println("<tr>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getNom() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getCible().getNom() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getCible().getTypeCible() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getCible().getLieu() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getNbDrop() + "</center></td>");
					out.println("<td><center><form action=\"TacheAffichage\" method=\"post\"><button type=\"submit\" name=\"removeObjet\" value=\"" + tache.getListeObjetTache().get(i).getObjet().getIdObjet() + "\" class=\"btn-link\">Supprimer</button></form></center></td>");
					out.println("</tr>");
				}
				%>
			</table>				
				<p>Ajouter un nouvel objet à la tâche : 
				<select name="listeObjets" id="listeObjets">
				<% 
					ArrayList<Objet> listeDesObjets = new ArrayList<Objet>();
					listeDesObjets = (ArrayList<Objet>)request.getAttribute("listeDesObjets");
					for(int i=0;i<listeDesObjets.size();i++){
						out.println("<option>" + listeDesObjets.get(i).getNom() + "</option>");;
					}
				%>					
				</select>		
				<INPUT TYPE=TEXT NAME="nbDrop" size="35">					
				<INPUT TYPE=SUBMIT VALUE="Ajout de l'objet" NAME="AjoutObjet">
				</p>	
			</FORM>
			<FORM METHOD=POST ACTION="TacheAffichage">
				<p>Liste des joueurs pour la tâche : </p>
				<table border="1">
  				<tr>
       				<th>Nom</th>
       				<th>Prénom</th>
       				<th>Ville</th>
       				<th>Pseudo</th>
       				<th>Supprimer</th>
   				</tr>
   				<%
   				ArrayList<Utilisateur> listeDesUtilisateursByTache = new ArrayList<Utilisateur>();
   				listeDesUtilisateursByTache = (ArrayList<Utilisateur>)request.getAttribute("listeDesUtilisateursByTache");
				for(int i=0;i<listeDesUtilisateursByTache.size();i++){
					out.println("<tr>");
					out.println("<td><center>" + listeDesUtilisateursByTache.get(i).getNom() + "</center></td>");
					out.println("<td><center>" + listeDesUtilisateursByTache.get(i).getPrenom() + "</center></td>");
					out.println("<td><center>" + listeDesUtilisateursByTache.get(i).getVille() + "</center></td>");
					out.println("<td><center>" + listeDesUtilisateursByTache.get(i).getPseudo() + "</center></td>");
					out.println("<td><center><form action=\"TacheAffichage\" method=\"post\"><button type=\"submit\" name=\"removeJoueur\" value=\"" + listeDesUtilisateursByTache.get(i).getIdUtilisateur() + "\" class=\"btn-link\">Supprimer</button></form></center></td>");
					out.println("</tr>");
				}
				%>
			</table>
				<p>Ajouter un nouveau joueur à la tâche : 
				<select name="listeJoueurs" id="listeJoueurs">
					<%
					ArrayList<Utilisateur> listeDesUtilisateurs = new ArrayList<Utilisateur>();
	   				listeDesUtilisateurs = (ArrayList<Utilisateur>)request.getAttribute("listeDesUtilisateurs");
						for(int i=0;i<listeDesUtilisateurs.size();i++){
								out.println("<option>" + listeDesUtilisateurs.get(i).getPseudo() + "</option>");
						}
					%>
				</select>							
				<INPUT TYPE=SUBMIT VALUE="Ajout du joueur" NAME="AjoutJoueur">
				</p>	
			</FORM>
		</div>
	</div>
</body>
</html>