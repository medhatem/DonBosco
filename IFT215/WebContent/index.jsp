<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>DON BOSCO ATLETICO</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<META NAME="author" CONTENT="Marc Fortier">
		<META NAME="description" CONTENT="page d'accueil �quipe sportive">
		<jsp:include page="/WEB-INF/menu.jsp" />
	</HEAD>
		<BODY>
			<CENTER>
					<H1>Accueil</H1>
			</CENTER>
			<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
			<jsp:include page="/WEB-INF/messageErreur.jsp" />
			<BR><BR>
			Date et heure : <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
	</BODY>
</HTML>