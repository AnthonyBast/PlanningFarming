<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<title>Connexion</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
	
<body>
	<div class="body">
	<div id="header">
		<h1 style="padding-top:30px;" align="center">Calendrier de Farming</h1>
	</div>

	<div id="content">
		<h3 align="center">Connexion</h2>
		<div >
			<FORM METHOD=POST ACTION="DALConnexion">
			<p style="margin-top:50px;" class="erreur" align="center"><span class="erreurSpan">${error}</span></p>
			<p style="margin-top:20px;"align="center"><INPUT TYPE=TEXT NAME="login" placeholder="Pseudo"></p>
			<p style="margin-top:20px;"align="center"><INPUT TYPE="password" NAME="pass" placeholder="Mot de passe"></p>
			<div style="margin-top:20px;" align="center">
				<INPUT margin="auto" class="btn btn-default" TYPE=SUBMIT VALUE="Connexion">
			</div>
			</FORM>
		</div>
	</div>
	</div>
</body>
</html>