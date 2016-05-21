<%@ page import="Métier.*,Controller.*" %>
<jsp:useBean id="tache" scope="request" class="Métier.Tache" />
<html>
	<head>
		<title>Create Tâche</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
	
<body>
	<div id="header">
		<h1>Créer une tâche</h1>
	</div>
	<div id="content">
		<div>
			<FORM METHOD=POST ACTION="TacheAffichage">
				<p>Libelle : <INPUT VALUE="${libelleEmpty}" TYPE=TEXT NAME="libelle" size="75"></p>
				<p>Description : <INPUT VALUE="${descriptionEmpty}" TYPE=TEXT NAME="description" size="150"></p>
				<p>Durée Minimum : <INPUT VALUE="${dureeEmpty}" TYPE=TEXT NAME="dureeMin" size="35"></p>
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
				
				<INPUT TYPE=SUBMIT VALUE="Créer" NAME="creer">
			</FORM>
		</div>
	</div>
</body>
</html>