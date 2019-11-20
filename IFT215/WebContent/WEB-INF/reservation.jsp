<%@ page import="java.util.*,java.text.*,equipeSoccer.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Système de gestion d'auberge</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="author" CONTENT="Marc Fortier">
<META NAME="description"
	CONTENT="page d'accueil système de gestion d'auberge">
<jsp:include page="/WEB-INF/menu.jsp" />
</HEAD>
<BODY>
	<CENTER>
		<FORM ACTION="Reservation" METHOD="POST">
			<H1>Réservation</H1>
			<H2>Ajouter une réservation :</H2>
			<TABLE border="1">
				<thead>
					<tr>
						<th>Liste des clients :
						<th>
						<th>Liste des chambres :
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<%
								GestionClient client = ((GestionAuberge) session.getAttribute("aubergeInterrogation")).getGestionClient();
							%>
							<%=client.listerClients((String) session.getAttribute("clientEnCours"))%>
						<td style="text-align: center; vertical-align: middle;"><b>&lt;--&gt;</b>
						<td>
							<%
								GestionChambre chambre = ((GestionAuberge) session.getAttribute("aubergeInterrogation")).getGestionChambre();
							%>
							<%=chambre.listerChambres((String) session.getAttribute("chambreEnCours"))%>
				</tbody>
			</TABLE>
			<BR>
			<TABLE>
				<tbody>
					<tr>
						<td><p align="right">Date début :</p>
						<td><INPUT TYPE="TEXT" NAME="dateDebut" VALUE=""><BR>
						<td><p align="right">Date fin :</p>
						<td><INPUT type="TEXT" name="dateFin" value=""><BR>
					<tr>
						<td><INPUT type="submit" name="bouton" value="Reserver">
				</tbody>
			</TABLE>
			Liste des réservations :<BR> <BR>
			<%
				GestionReservation reservation = ((GestionAuberge) session.getAttribute("aubergeInterrogation")).getGestionReservation();
			%>
			<%=reservation.listerReservations((String) session.getAttribute("idReservation"))%>
			<BR> <INPUT type="submit" name="bouton" value="Canceller">
			<BR>
		</FORM>

	</CENTER>
	<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
	<jsp:include page="/WEB-INF/messageErreur.jsp" />
	<BR>
	<BR> Date et heure :
	<%=DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date())%>
</BODY>
</HTML>