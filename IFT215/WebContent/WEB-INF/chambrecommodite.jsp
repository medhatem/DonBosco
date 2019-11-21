<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>Système de gestion d'équipe de soccer</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<META NAME="author" CONTENT="Marc Fortier">
		<META NAME="description" CONTENT="page d'accueil système de gestion d'equipeSoccer">
		<jsp:include page="/WEB-INF/menu.jsp" />
	</HEAD>
		<BODY>
			<CENTER>
				<FORM ACTION="ChambreCommodite" METHOD="POST">
					<H1>Association des chambres et des commodités</H1>
					<TABLE border="1">
						<thead>
							<tr>
								<th>Liste des chambres :
								<th>
								<th>Liste des commodites :
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<%
										GestionTerrain chambre = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionTerrain();
									%>
									
								<td style="text-align: center; vertical-align: middle;"><b>&lt;--&gt;</b> 
								<td>				
									<%
														GestionFacture commodite = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionFacture();
													%>
									<%=commodite.listerFactures((String)session.getAttribute("commoditeEnCours"))%>
						</tbody>
					</TABLE>
					<BR>
					<TABLE>
						<tbody>
						<tr>
							<td><INPUT type="submit" name="bouton" value="Retirer">
							<td><INPUT type="submit" name="bouton" value="Inclure"><BR>
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

<%--
	<%  String resultat;
		GestionChambre chambre = ((GestionAuberge) session.getAttribute("equipeSoccerInterrogation")).getGestionChambre();
		if((String)session.getAttribute("idChambre") == null)
		{
			resultat = chambre.listerChambreLibres((String)session.getAttribute("idChambre"));
		}
		else
			resultat = chambre.afficher((String)session.getAttribute("idChambre")); %>
	<%= resultat %> --%>