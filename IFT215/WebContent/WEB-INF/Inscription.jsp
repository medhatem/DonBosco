<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/sheet.css">
<link rel="stylesheet" href="css/Inscription.css">
<!--<script src="js/jquery-3.3.1.min.js"></script>-->

<jsp:include page="/WEB-INF/header.jsp" />
<link rel="stylesheet" href="css/header.css">
</head>

<body>
	<div class="container-fluid" style="margin: 50px">
		<div class="row">
			<div class="divborder col-sm-8" id="divLeft">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-2">
						<h2>Formulaire d'inscription</h2>
					</div>
				</div>
				<FORM ACTION="Inscription" METHOD="POST">
					<div class="row">
						<div class="col-sm-7 col-sm-offset-1">
							<div class="form-group">
								<h6>Courriel</h6>
								<input type="email" name="Nom" class="form-control InputW"
									id="idNom">
							</div>
							<div class="form-group">
								<h6>Prénom</h6>
								<input type="text" name="Prenom" class="form-control InputW"
									id="idPrenom">
							</div>
							<div class="form-group">
								<h6>Date de naissance (ex: 1990-10-23)</h6>
								<input type="Date" name="DateN" class="form-control InputW"
									id="idDateN">
							</div>
							<div class="form-group">
								<h6>Mot de passe</h6>
								<input type="password" name="motP" class="form-control" id="idAdrs">
							</div>
							<div class="form-group">
								<h6>Adresse</h6>
								<input type="text" name="Adrs" class="form-control" id="idAdrs">
							</div>
							<div class="form-group">
								<h6>Niveau:</h6>
								<div class="radio">
									<label> <small><input type="radio"
											name="optionsRadios" id="optionsRadios1" value="Joueur">
											Joueur</small>
									</label>
								</div>
								<div class="radio">
									<label> <small><input type="radio"
											name="optionsRadios" id="optionsRadios2" value="Spectateur">
											spectateur</small>
									</label>
								</div>
							</div>
						</div>

						<div class="col-sm-4" id="colImg" align="center">
							<img src="img/personne.png" class="divborder"
								style="height: 200px; width: 200px;">
							<button id="btnRemove">
								<span class="glyphicon glyphicon-remove"
									style="font-size: 20px;"></span>
							</button>
							<br> <br>
							<button class="btn btn-default" id="AjtTof" style="width: 150px;">Ajouter
								une photo</button>
						</div>
					</div>

					<div class="row" id="">
						<div class="col-sm-2 col-sm-offset-8">
							<INPUT TYPE="SUBMIT" VALUE="Annuler" name="bouton">
						</div>
						<div class="col-sm-2">
							<INPUT TYPE="SUBMIT" VALUE="Enregistrer" name="bouton">
						</div>
					</div>
				</form>
			</div>

			<div id="divright" class="Big3Div col-sm-3 col-sm-offset-1">
				<div class="divborder" id="divrighttop" align="center">Dernier résultat</div>
				<br>
				<div class="divborder" id="divrightbottem" align="center">Flux d'actualités</div>
			</div>
		</div>
	</div>
</body>
</html>