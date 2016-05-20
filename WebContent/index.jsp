<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Farming Site</title>
		<meta http-equiv="Content-Language" content="French" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
<body>
	<div id="header">
		<h1>Site de programmation de farm</h1>
	</div>

	<div id="content">
		<h2>Connexion</h2>
		<div>
			<FORM METHOD=POST ACTION="DALConnexion">
			<span class="">${error}</span>
			<p>Login : <INPUT TYPE=TEXT NAME="login"></p>
			<p>Password  : <INPUT TYPE="password" NAME="pass"></p>
			<INPUT TYPE=SUBMIT VALUE="Connexion">
			</FORM>
		</div>
	</div>
</body>
</html>