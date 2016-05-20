<%@ page import="Métier.*,Controller.*" %>
<jsp:useBean id="tache" scope="request" class="Métier.Tache" />
<meta charset="utf-8" />
<link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
        crossorigin="anonymous">

<link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
        integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
        crossorigin="anonymous">

<script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
        integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
        crossorigin="anonymous">
</script>
<link rel="stylesheet" type="text/css" href="stylesheet/style.css" media="screen">
<html>
	<head>
		<title>Tâche</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
<body>
	<div id="header">
		<h1 align="center">Tâche</h1>
	</div>
	<div id="content">
		<h2 align="center">${tache.libelle}</h2>
		<div>
			<p style="margin-left:5em;  font-weight: bold;">Description : ${tache.description}</p>
			<p style="margin-left:5em; font-weight: bold;">Durée requise (en minute) : ${tache.dureeMin}</p>
			<p style="margin-left:5em; font-weight: bold;">Objet(s) à Farm : </p>
			<table class="table table-striped" border="1">
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
				<div align="center">
				<INPUT class="btn btn-default" TYPE=SUBMIT VALUE="Tâche effectuée" NAME="Effectuer">
				</div>
			</FORM>
		</div>
	</div>
</body>
</html>