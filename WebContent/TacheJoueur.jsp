<html >
	<head>
		<title>T�che</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
<body>
	<div id="header">
		<h1>T�che</h1>
	</div>

<%

%>
	<div id="content">
		<h2>@afficherTacheLibelle</h2>
		<div>
			<p>Description :@afficherTacheDescription</p>
			<p>Dur�e requise (en minute) : @afficherTacheDuree</p>
			<p>Objet(s) � Farm : - @afficherTacheObjet dropable sur @afficherCibleNom qui est un @afficherCibleTypeCible qui se trouve au lieu suivant : @afficherLieuCible</p>
			<p>Description : @afficherTacheDescription</p>
			<FORM METHOD=POST ACTION="TacheJoueur.jsp">
				<INPUT TYPE=SUBMIT VALUE="Effectuer" NAME="T�che effectu�e">
			</FORM>
		</div>
	</div>
</body>
</html>