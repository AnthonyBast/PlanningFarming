<html >
	<head>
		<title>Tâche</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
<body>
	<div id="header">
		<h1>Tâche</h1>
	</div>

<%

%>
	<div id="content">
		<h2>@afficherTacheLibelle</h2>
		<div>
			<p>Description :@afficherTacheDescription</p>
			<p>Durée requise (en minute) : @afficherTacheDuree</p>
			<p>Objet(s) à Farm : - @afficherTacheObjet dropable sur @afficherCibleNom qui est un @afficherCibleTypeCible qui se trouve au lieu suivant : @afficherLieuCible</p>
			<p>Description : @afficherTacheDescription</p>
			<FORM METHOD=POST ACTION="TacheJoueur.jsp">
				<INPUT TYPE=SUBMIT VALUE="Effectuer" NAME="Tâche effectuée">
			</FORM>
		</div>
	</div>
</body>
</html>