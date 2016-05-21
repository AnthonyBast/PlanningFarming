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
			int month = effectuerTache.getDateHeureDebut().getMonth()+1;
			int year = effectuerTache.getDateHeureDebut().getYear()+1900;
			out.println(effectuerTache.getDateHeureDebut().getDate()+"/"+month+"/"+year); 
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
			<p>Dur�e requise (en minute) : ${tache.dureeMin}</p>
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
				for(int i=0;i<tache.getObjetTache().size();i++){
					out.println("<tr>");
					out.println("<td><center>" + tache.getObjetTache().get(i).getObjet().getNom() + "</center></td>");
					out.println("<td><center>" + tache.getObjetTache().get(i).getObjet().getCible().getNom() + "</center></td>");
					out.println("<td><center>" + tache.getObjetTache().get(i).getObjet().getCible().getTypeCible() + "</center></td>");
					out.println("<td><center>" + tache.getObjetTache().get(i).getObjet().getCible().getLieu() + "</center></td>");
					out.println("<td><center>" + tache.getObjetTache().get(i).getNbDrop() + "</center></td>");
					out.println("</tr>");
				}
			%>
			</table>	
			<p>Heure de d�but : ${effectuerTache.heureDebut}h<% if(effectuerTache.getMinuteDebut()<10 && effectuerTache.getMinuteDebut()>=0){%>0${effectuerTache.minuteDebut}<% }else {%> ${effectuerTache.minuteDebut} <% } %></p>
			<p>Heure de fin :  ${effectuerTache.heureFin}h<% if(effectuerTache.getMinuteFin()<10 && effectuerTache.getMinuteFin()>=0){%>0${effectuerTache.minuteFin}<% }else {%> ${effectuerTache.minuteFin} <% } %></p>
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