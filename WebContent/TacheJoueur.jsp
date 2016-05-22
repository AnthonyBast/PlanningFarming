<%@ page import="Métier.*,Controller.*" %>
<jsp:useBean id="tache" scope="request" class="Métier.Tache" />
<jsp:useBean id="effectuerTache" scope="request" class="Métier.EffectuerTache" />
<html>
	<head>
		<title>Tâche</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
<body>
	<div id="header">
		<h1>Tâche</h1>
	</div>
	<div id="content">
		<h2>${tache.libelle} du <%
			int month = tache.getDateHeureDebut().getMonth()+1;
			int year = tache.getDateHeureDebut().getYear()+1900;
			out.println(tache.getDateHeureDebut().getDate()+"/"+month+"/"+year); 
			%>: 
		<% 
			if(effectuerTache.getIsEffectuer())
				out.println("Tache déjà effectué");
			else
				out.println("Tache non effectué");
		%>
		</h2>
		<div>
			<p>Description : ${tache.description}</p>
			<p>Durée Estimée (en minute) : ${tache.dureeMin}</p>
			<p>Objet(s) à Farm : </p>
			<table border="1">
  				<tr>
       				<th>Nom Objet</th>
       				<th>Monstre</th>
       				<th>Type Monstre</th>
       				<th>Lieu</th>
       				<th>Nb à drop</th>
   				</tr>
   			<%
				for(int i=0;i<tache.getListeObjetTache().size();i++){
					out.println("<tr>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getNom() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getCible().getNom() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getCible().getTypeCible() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getObjet().getCible().getLieu() + "</center></td>");
					out.println("<td><center>" + tache.getListeObjetTache().get(i).getNbDrop() + "</center></td>");
					out.println("</tr>");
				}
			%>
			</table>	
			<p>Heure de début : ${tache.heureDebut}h<% if(tache.getMinuteDebut()<10 && tache.getMinuteDebut()>=0){%>0${tache.minuteDebut}<% }else {%> ${tache.minuteDebut} <% } %></p>
			<p>Heure de fin :  ${tache.heureFin}h<% if(tache.getMinuteFin()<10 && tache.getMinuteFin()>=0){%>0${tache.minuteFin}<% }else {%> ${tache.minuteFin} <% } %></p>
			<% 
			if(!effectuerTache.getIsEffectuer()){
				%>
				<FORM METHOD=POST ACTION="TacheAffichage">
					<INPUT TYPE=SUBMIT VALUE="Tâche effectuée" NAME="Effectuer">
				</FORM>
				<%
			}				
			%>			
		</div>
	</div>
</body>
</html>