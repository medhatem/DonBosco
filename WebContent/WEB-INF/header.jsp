

<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<nav class="navbar navbar-expand-lg bg-secondary text-uppercase" id="mainNav">
	<div class="container">
	   <div class="navbar-header">
		  <img src="img/donBoscoLogo.jpg" class="myimg">
	   </div>
	   <div class="nav navbar-nav" id="divTitle">
		  <br>
		  <h2 id="title" style="color: aliceblue;"><b>DON BOSCO ATLETICO</b></h2>
	   </div>
	   
	   <div class="collapse navbar-collapse" id="navbarResponsive">
		  <ul class="navbar-nav ml-auto">
			 <li class="nav-item mx-0 mx-lg-1">
				<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="Rencontres">Rencontres</a>
			 </li>
			 <li class="nav-item mx-0 mx-lg-1">
				<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="Factures">Factures</a>
			 </li>
			 <li class="nav-item mx-0 mx-lg-1">
				<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="Galerie">Galerie</a>
			 </li>
			 <li class="nav-item mx-0 mx-lg-1">
				<a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href=Apropos>A propos</a>
			 </li>
		  </ul>
		  <ul class="nav navbar-nav navbar-right">
			 <li>
				<div class="dropdown" id="HeaderForm" align="center">
				   <!-- <button type="button" data-toggle="dropdown" id="BtnSeCont"><i class='fas fa-user-circle' style='font-size:24px'></i></span></button> <br> <small>Se connecter</small>  -->
				   <button type="button" data-toggle="dropdown" id="BtnSeCont"class="btn btn-success btn-circle btn-lg"><i class="fas fa-user-circle"style='font-size:30px'></i></button> <br>
				    <small id="headerText">
					<%
						TupleJoueur joueur = ((TupleJoueur) session.getAttribute("Joueur"));
			          	String res = "Se connecter";
			          	
			          	if(joueur != null) {
							res = joueur.getCourriel();
			          	}
					%>
					<%= res %>
					</small>
					<br>
					<%
					String dec = "";
					if(((TupleJoueur) session.getAttribute("Joueur"))!= null){
						dec = "Se deconnecter";
					}
					%>
					
					<a href="Logout" ><small id="headerText"><%= dec%></small> </a>

				   <form class="dropdown-menu" id="HeaderForm" ACTION="Login" METHOD="POST">
					  <div class="form-group">
						 <h4 align="center"><b>Se connecter</b></h4>
					  </div>
					  <div class="form-group HFP" >
						 <h5>Nom d'utilisateur</h5>
						 <input type="text" name="NomU" class="form-control" id="nomU">
					  </div>
					  <div class="form-group HFP">
						 <h5>Mot de passe</h5>
						 <input type="password" name="MotP" class="form-control" id="motP">
					  </div>
					  <div class="form-group HFP">
						 <%-- <button id="BtnConx">connexion</button>--%>
						 <INPUT TYPE="SUBMIT" VALUE="Soumettre" id="BtnConx" class="button mb-4 text-black">
					  </div>
					  <div class="form-group HFP">      
						 <a href="Inscription" id="Ins"><small>Inscription</small> </a><br>
						 <a href="#" id="MPO"><small>Mot de passe oubli�?</small> </a>
					  </div>
				   </form>
				</div>
			 </li>
		  </ul>
	   </div>
	</div>
		<%
	int notif = 0;
	String res2 = "";
	
	if(session.getAttribute("notification") != null)
		notif = ((int) session.getAttribute("notification"));

	if(notif == 1)
		res2 = "<script>alert('Inscription complétée avec succès!');</script>";
		
	session.setAttribute("notification", 0);%>
	<%=res2%>
 </nav>
