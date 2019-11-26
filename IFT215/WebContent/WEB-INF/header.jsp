<nav class="navbar navbar-default" style="background-color: white;">
	<div class="container-fluid" id="divHeader">
		<div class="navbar-header">
			<img src="images/donBoscoLogo.jpg" class="myimg">
		</div>
		<div class="nav navbar-nav" id="divTitle">
			<br>
			<h2 id="title"><b>DON BOSCO ATLETICO</b></h2>	
		</div>

		<ul class="nav navbar-nav navbar-right">
		  <li>
		    <div class="dropdown" id="HeaderForm" align="center">
		      <button type="button" data-toggle="dropdown" id="BtnSeCont"><span class="glyphicon glyphicon-user" style="font-size: 20px;"></span></button> <br> <small>Se connecter</small> 

		      <form class="dropdown-menu" id="HeaderForm" ACTION="Login" METHOD="POST">
		        <div class="form-group">
		          <h4 align="center"><b>Se connecter<b></h4>
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
		          <INPUT TYPE="SUBMIT" VALUE="Soumettre" id="BtnConx">
		        </div>
		        <div class="form-group HFP">      
		          <a href="Inscription" id="Ins"><small>Inscription</small> </a><br>
		          <a href="#" id="MPO"><small>Mot de passe oublié?</small> </a>
		        </div>
		      </form>
		    </div>
		  </li>
		</ul>
					
			
		<br><br><br>
		<ul class="nav navbar-nav">
			<li><a href="Rencontres">Rencontres</a></li>
			<li><a href="Factures">Factures</a></li>
			<li><a href="Galerie">Galerie</a></li>
			<li><a href="Apropos">A€ propos</a></li>
		</ul>


	</div>
</nav>