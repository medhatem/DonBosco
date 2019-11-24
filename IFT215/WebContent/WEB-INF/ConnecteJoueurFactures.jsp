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
	<link rel="stylesheet" href="css/ConnecteJoueurFactures.css">
	<!--<script src="js/jquery-3.3.1.min.js"></script>-->

		<jsp:include page="/WEB-INF/header.jsp" />
</head>
<body>
	<div class="container-fluid" style="margin: 50px">
		<div class="row">
			<div class="divborder Big3Div col-sm-2" id="divLeft">
				<ul >
					<li>groupe1</li>
				</ul>
			</div>

			<div class="container-fluid divborder Big3Div col-sm-5 col-sm-offset-1" id="divcenter">
				<div class="row divborderbottom" id="LieuDate">
					<div class="col-sm-6"><label>Facture n°</label></div> <div class="col-6"><label>Date:</label> <%= new java.util.Date() %> </div>
				</div>
				
				<div class="row divborderbottom" id="RowDesc">
					<div class="divborder" id="divDesc">
						<div class="row">	
							<div class="col-sm-12" id="Desc">
								<label> Description: </label>
								<p> </p>
							</div>
						</div>
						<div class="row" id="RowMont">
							<div class="col-sm-12  ">
								<label>Montant:</label><input type="text" name="montant" id="montant"><span>$</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row" id="PayPalRow">
					<div class="col-sm-3 col-sm-offset-9">
						<button type="button" class="btn btn-default" id="PayPalBtn"><small><span><img src="img/paypal.jpg" style="width: 30px; height: 15px; padding-right: 5px;"></span>Check out</small></button>
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