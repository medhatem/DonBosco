<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<link rel="stylesheet" href="css/sheet.css">
	<link rel="stylesheet" href="css/ConnecteJoueur.css">
	<!--<script src="js/jquery-3.3.1.min.js"></script>-->
    <link rel="stylesheet" href="css/header.css">
		<jsp:include page="/WEB-INF/header.jsp" />
</head>

<body>
	<div class="container-fluid" style="margin: 50px">
		<div class="row">
			<div class="divborder Big3Div col-sm-2" id="divLeft">
						<ul>
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
				<div class="row" id="RowRadio">
					<div class="col-sm-2">
						<label><small>Participation: </small></label>
					</div>
					<div class="col-sm-5">
						<div class="radio">
						  <label>
						    <small><input type="radio" name="optionsRadios" id="optionsRadios1" value="option1">
						    Je serai présent</small>
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <small><input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
						    Je ne serai pas présent</small>
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <small><input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">
						    je ne sais pas encore</small>
						  </label>
						</div>
					</div>
					<div class="col-sm-3 col-sm-offset-2">
						<button type="button" class="btn btn-default" id="Eng">Enregistrer</button>
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
</body>
</html>