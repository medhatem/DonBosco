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
		<FORM ACTION="Client" METHOD="POST">
			<H1>Client</H1>
			Liste des clients :<BR>
			<BR>
			<%
				GestionClient client = ((GestionAuberge) session.getAttribute("aubergeInterrogation")).getGestionClient();
			%>
			<%=client.listerClients((String) session.getAttribute("clientEnCours"))%>
			<BR> <INPUT type="submit" name="bouton" value="Afficher">
			<INPUT type="submit" name="bouton" value="Supprimer"> <INPUT
				type="submit" name="bouton" value="Reserver..."><BR>
			<%
				String detailsString = "";
				String clientEnCours = (String) session.getAttribute("clientEnCours");
				if (clientEnCours != null && !clientEnCours.equals("-1"))
				{
					detailsString = client.afficherClient(clientEnCours);
				}
				else
					detailsString = "<i>(Sélectionnez un client)</i>";
			%>
			<%=detailsString%>
			<BR>
			<H2>Ajouter un client :</H2>
			<TABLE>
				<tbody>
					<tr>
						<td><p align="right">Id :</p>
						<td><INPUT TYPE="TEXT" NAME="id" VALUE=""><BR>
					<tr>
						<td><p align="right">Prenom :</p>
						<td><INPUT type="TEXT" name="prenom" value=""><BR>
					<tr>
						<td><p align="right">Nom :</p>
						<td><INPUT type="TEXT" name="nom" value=""><BR>
					<tr>
						<td><p align="right">Âge :</p>
						<td><INPUT TYPE="TEXT" NAME="age" VALUE=""><BR>
					<tr>
						<td>
						<td><p align="right">
								<INPUT type="submit" name="bouton" value="Creer">
							</p>
							<BR>
				</tbody>
			</TABLE>
		</FORM>

	</CENTER>
	<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
	<jsp:include page="/WEB-INF/messageErreur.jsp" />
	<BR>
	<BR> Date et heure :
	<%=DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date())%>
</BODY>
</HTML>