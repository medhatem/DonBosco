<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Syst�me de gestion d'equipeSoccer</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<META NAME="author" CONTENT="Marc Fortier">
		<META NAME="description" CONTENT="page d'accueil syst�me de gestion d'equipeSoccer">
		<jsp:include page="/WEB-INF/menu.jsp" />
	</HEAD>
		<BODY>
			<CENTER>
				<FORM ACTION="Terrain" METHOD="POST">
					<H1>terrain</H1>
					Liste des terrains :<BR><BR>
					<%
						GestionTerrain terrain = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionTerrain();
					%>
					<%=terrain.listerTerrains((String)session.getAttribute("TerrainEnCours"))%>
					<BR>
					<INPUT type="submit" name="bouton" value="Afficher">
					<INPUT type="submit" name="bouton" value="Supprimer">
					<INPUT type="submit" name="bouton" value="Commodites...">
					<INPUT type="submit" name="bouton" value="Reserver..."><BR>
					<%
						String detailsString = "";
									String TerrainEnCours = (String)session.getAttribute("TerrainEnCours");
									
									detailsString = "<i>(S�lectionnez une terrain)</i>";
					%>
					<%= detailsString %>
					<BR><H2>Ajouter une terrain :</H2>
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