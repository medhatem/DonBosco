<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Système de gestion d'equipeSoccer</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<META NAME="author" CONTENT="Marc Fortier">
		<META NAME="description" CONTENT="page d'accueil système de gestion d'equipeSoccer">
		<jsp:include page="/WEB-INF/menu.jsp" />
	</HEAD>
		<BODY>
			<CENTER>
				<FORM ACTION="Commodite" METHOD="POST">
					<H1>Commodite</H1>
					Liste des commodites :<BR><BR>
					<%
						GestionFacture commodite = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionFacture();
					%>
					<%=commodite.listerFactures((String)session.getAttribute("FactureEnCours"))%>
					<BR>
					<INPUT type="submit" name="bouton" value="Afficher">
					<INPUT type="submit" name="bouton" value="Supprimer">
					<INPUT type="submit" name="bouton" value="Inclure..."><BR>
					<BR>Détails:
					<% 
					String detailsString = "";
					String FactureEnCours = (String) session.getAttribute("FactureEnCours");
					if (FactureEnCours != null && !FactureEnCours.equals("-1"))
					{
						detailsString = commodite.afficherFacture(FactureEnCours);
					}
					else
							detailsString = "<i>(Sélectionnez une commodite)</i>"; %>
					<%= detailsString %>
					<BR><H2>Ajouter une commodite :</H2>
					<TABLE>
						<tbody>
						<tr><td><p align="right">Id :</p><td><INPUT TYPE="TEXT" NAME="id" VALUE=""><BR>
						<tr><td><p align="right">Description :</p><td><INPUT type="TEXT" name="description" value=""><BR>
						<tr><td><p align="right">Prix :</p><td><INPUT TYPE="TEXT" NAME="prix" VALUE=""><BR>
						<tr><td><td><p align="right"><INPUT type="submit" name="bouton" value="Creer"></p><BR>
						</tbody>
					</TABLE>
				</FORM>

			</CENTER>
			<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
			<jsp:include page="/WEB-INF/messageErreur.jsp" />
			<BR><BR>
			Date et heure : <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
	</BODY>
</HTML>