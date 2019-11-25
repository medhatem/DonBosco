<!DOCTYPE html>
<html>
<head>
	<title></title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<link rel="stylesheet" href="css/sheet.css">
	<link rel="stylesheet" href="css/ConnecteTresorierFactures.css">
	<!--<script src="js/jquery-3.3.1.min.js"></script>-->
	<link rel="stylesheet" href="css/header.css">
	<jsp:include page="/WEB-INF/header.jsp" />

</head>
<body>
	<div class="container-fluid" style="margin: 50px">
		<div class="row">
			<div class="divborder Big3Div col-sm-2" id="divLeft">
				<div class="row">
					<div class="col-sm-12">
						<ul >
							<li>groupe1</li>
						</ul>
					</div>	
				</div>
				<div class="row" id="RowAjt" align="center">
					<div class="col-sm-4 col-sm-offset-4">
						<button id="ajt" type="button" class="btn btn-default">Ajouter</button>
					</div>	
				</div>	
			</div>

			<div class="container-fluid divborder Big3Div col-sm-5 col-sm-offset-1" id="divcenter">
				<div class="row divborderbottom" id="LieuDate">
					<div class="col-sm-6"><label>Lieu:</label></div> <div class="col-6"><label>Date:</label> <%= new java.util.Date() %> </div>
				</div>
				<div class="row divborderbottom" id="RowTabs">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#detail" data-toggle="tab">Détail</a></li>
						<li><a href="#suivi" data-toggle="tab">Suivi</a></li>
					</ul>
					<div class="tab-content" id="divCont">
						<div class="tab-pane fade in active" id="detail">
							<div class="row">	
								<div class="col-sm-12" id="Desc">
									<label> Description: </label>
									<p> </p>
								</div>
							</div>
							<div class="row" id="RowMont">
								<div class="col-sm-12">
									<label>Montant:</label><input type="text" name="montant" id="montant"><span>$</span>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="suivi">
							<p>hello</p>
						</div>
					</div>
				</div>
				<div class="row" id="RowAnlEng">
					<div class="col-sm-3 col-sm-offset-6">
						<button type="button" class="btn btn-default">Annuler</button>
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-default">Enregistrer</button>
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