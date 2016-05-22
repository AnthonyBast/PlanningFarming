<%@ page import="M�tier.*,Controller.*" %>
<jsp:useBean id="tache" scope="request" class="M�tier.Tache" />
<jsp:useBean id="effectuerTache" scope="request" class="M�tier.EffectuerTache" />
<html>
	<head>
		<title>T�che</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
<body>
	<div id="header">
		<h1>T�che</h1>
	</div>
	<div id="content">
		<h2>${tache.libelle} du <%
			int month = tache.getDateHeureDebut().getMonth()+1;
			int year = tache.getDateHeureDebut().getYear()+1900;
			out.println(tache.getDateHeureDebut().getDate()+"/"+month+"/"+year); 
			%>: 
		<% 
			if(effectuerTache.getIsEffectuer())
				out.println("Tache d�j� effectu�");
			else
				out.println("Tache non effectu�");
		%>
		</h2>
		<div>
			<p>Description : ${tache.description}</p>
			<p>Dur�e Estim�e (en minute) : ${tache.dureeMin}</p>
			<p>Objet(s) � Farm : </p>
			<table border="1">
  				<tr>
       				<th>Nom Objet</th>
       				<th>Monstre</th>
       				<th>Type Monstre</th>
       				<th>Lieu</th>
       				<th>Nb � drop</th>
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
			<p>Heure de d�but : ${tache.heureDebut}h<% if(tache.getMinuteDebut()<10 && tache.getMinuteDebut()>=0){%>0${tache.minuteDebut}<% }else {%> ${tache.minuteDebut} <% } %></p>
			<p>Heure de fin :  ${tache.heureFin}h<% if(tache.getMinuteFin()<10 && tache.getMinuteFin()>=0){%>0${tache.minuteFin}<% }else {%> ${tache.minuteFin} <% } %></p>
			<% 
			if(!effectuerTache.getIsEffectuer()){
				%>
				<FORM METHOD=POST ACTION="TacheAffichage">
					<INPUT TYPE=SUBMIT VALUE="T�che effectu�e" NAME="Effectuer">
				</FORM>
				<%
			}				
			%>			
		</div>
	</div>
</body>
</html>