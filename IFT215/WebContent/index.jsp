<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>DON BOSCO ATLETICO</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<META NAME="author" CONTENT="Marc Fortier">
		<META NAME="description" CONTENT="page d'accueil équipe sportive">
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<link rel="stylesheet" href="css/sheet.css">
	<!--<script src="js/jquery-3.3.1.min.js"></script>-->
		

		<jsp:include page="/WEB-INF/header.jsp" />
		    <link rel="stylesheet" href="css/header.css">
		    
	<%

	if(session.getAttribute("equipeSoccerInterrogation") == null){
		session.setAttribute("equipeSoccerInterrogation", new GestionEquipeSoccer());
	}	
	if(session.getAttribute("equipeSoccerUpdate") == null){
		GestionEquipeSoccer equipeSoccer = new GestionEquipeSoccer();
		session.setAttribute("equipeSoccerUpdate", equipeSoccer);
		session.setAttribute("equipeSoccerInterrogation", equipeSoccer);
	}
	
	%>  
		    
	</HEAD>
	<BODY>
	
				<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
				<jsp:include page="/WEB-INF/messageErreur.jsp" />
	
		<div class="container-fluid" style="margin: 50px">
			<div class="row">
				<div class="divborder Big3Div col-sm-2" id="divLeft">
					<ul >
						<li>groupe1</li>
					</ul>
				</div>
	
				<div class="container-fluid divborder Big3Div col-sm-5 col-sm-offset-1" id="divcenter">
					<div class="row divborderbottom" id="LieuDate">
						<div class="col-sm-6"><label>Lieu:</label></div> <div class="col-6"><label>Date:</label> <%= new java.util.Date() %> </div>
					</div>
					<div class="row" id="LabelsEquipes">
						<div class="col-sm-5" align="center" ><label >Équipe A</label></div> <div class="col-sm-5 col-sm-offset-2" align="center"><label >Équipe B</label></div>
					</div>
					<div class="row divborderbottom" id="Equipes">
						<div class="divborder col-sm-5" id="divcenter1">
							<ul>
								<li>groupe2</li>
							</ul>
						</div>
						<div class="divborder col-sm-5 col-sm-offset-2" id="divcenter2">
							<ul>
								<li>groupe3</li>
							</ul>
						</div>
					</div>
					<div class="row" id="parg" align="center">
						<div class="col-sm-12">
							<p>Connectez-vous pour accéder à plus de fonctionnalités</p>
						</div>
					</div>
				</div>
	
				<div id="divright" class="Big3Div col-sm-3 col-sm-offset-1">
					<div class="divborder" id="divrighttop" align="center">Dernier résultat</div>
					<br>
					<div class="divborder" id="divrightbottem" align="center">Flux d'actualités</div>
				</div>
			</div>
		</div>
	</BODY>
</HTML>