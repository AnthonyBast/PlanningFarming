<%@ page import="Métier.*,Controller.*" %>
<jsp:useBean id="tache" scope="request" class="Métier.Tache" />
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
		<h2>${tache.libelle}</h2>
		<div>
			<p>Description : ${tache.description}</p>
			<p>Durée requise (en minute) : ${tache.dureeMin}</p>
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
			</br>	
			
			<FORM METHOD=POST ACTION="TacheJoueur">
				<INPUT TYPE=SUBMIT VALUE="Tâche effectuée" NAME="Effectuer">
			</FORM>
		</div>
	</div>
</body>
</html>