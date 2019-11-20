<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Système de gestion d'auberge</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<META NAME="author" CONTENT="Marc Fortier">
		<META NAME="description" CONTENT="page d'accueil système de gestion d'auberge">
		<jsp:include page="/WEB-INF/menu.jsp" />
	</HEAD>
		<BODY>
			<CENTER>
				<FORM ACTION="Chambre" METHOD="POST">
					<H1>Chambre</H1>
					Liste des chambres :<BR><BR>
					<%
						GestionTerrain chambre = ((GestionEquipeSoccer) session.getAttribute("aubergeInterrogation")).getGestionTerrain();
					%>
					<%=chambre.listerTerrains((String)session.getAttribute("chambreEnCours"))%>
					<BR>
					<INPUT type="submit" name="bouton" value="Afficher">
					<INPUT type="submit" name="bouton" value="Supprimer">
					<INPUT type="submit" name="bouton" value="Commodites...">
					<INPUT type="submit" name="bouton" value="Reserver..."><BR>
					<%
						String detailsString = "";
									String chambreEnCours = (String)session.getAttribute("chambreEnCours");
									
									if(chambreEnCours != null && !chambreEnCours.equals("-1"))
										detailsString = ((GestionEquipeSoccer) session.getAttribute("aubergeInterrogation")).getGestionChambreCommodite().afficherChambreCommodite(chambreEnCours);
									else
										detailsString = "<i>(Sélectionnez une chambre)</i>";
					%>
					<%= detailsString %>
					<BR><H2>Ajouter une chambre :</H2>
					<TABLE>
						<tbody>
						<tr><td><p align="right">Id :</p><td><INPUT TYPE="TEXT" NAME="id" VALUE=""><BR>
						<tr><td><p align="right">Nom :</p><td><INPUT type="TEXT" name="nom" value=""><BR>
						<tr><td><p align="right">Type de lit :</p><td><INPUT type="TEXT" name="typeLit" value=""><BR>
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