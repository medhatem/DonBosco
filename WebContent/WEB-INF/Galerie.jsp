<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta charset="ISO-8859-1" />
<title>Galerie</title>
<meta charset="UTF-8" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/slideshow.css" />
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/sheet.css" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Montserrat&display=swap"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />

<!-- Theme CSS -->
<link href="css/freelancer.min.css" rel="stylesheet" />
<!--<script src="js/jquery-3.3.1.min.js"></script>-->
<link rel="stylesheet" href="css/header.css" />
<style>
body {
	background-color: #e3ecef;
}
</style>
<jsp:include page="/WEB-INF/header.jsp" />
<link rel="stylesheet" href="css/header.css" />
</head>

<header id="includedContent"></header>

<body>
	<section class="page-section portfolio" id="portfolio">
		<div class="container">
			<div class="row">


				<div class="col-sm  shadow-lg p-3 mb-5 rounded"
					style="margin-right: 4cm;">
					<h5
						class="page-section-heading text-center text-uppercase text-black">Bienvenue
						dans la galerie</h5>
					<div id="slideshow" class="carousel slide carousel-fade"
						data-ride="carousel">

						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="img/portfolio/equieAfille.jpg" class="d-block w-100"
									alt="...">
								<div class="carousel-caption d-none d-md-block">
									<h5>Joueus en plein echauffement</h5>
									<p>Description</p>
								</div>
							</div>
							<div class="carousel-item">
								<img src="img/portfolio/equipeA.jpg" class="d-block w-100"
									alt="...">
								<div class="carousel-caption d-none d-md-block">
									<h5>Nom Label</h5>
									<p>Description</p>
								</div>
							</div>
							<div class="carousel-item">
								<img src="img/portfolio/equipeB.jpg" class="d-block w-100"
									alt="...">
								<div class="carousel-caption d-none d-md-block">
									<h5>Nom Label</h5>
									<p>Description</p>
								</div>
							</div>
							<div class="carousel-item">
								<img src="img/portfolio/equipeC.jpg" class="d-block w-100"
									alt="...">
								<div class="carousel-caption d-none d-md-block">
									<h5>Nom Label</h5>
									<p>Description</p>
								</div>
							</div>

						</div>

						<a class="carousel-control-prev" href="#slideshow" role="button"
							data-slide="prev"> <span class="carousel-control-prev-icon"
							aria-hidden="true"></span> <span class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#slideshow" role="button"
							data-slide="next"> <span class="carousel-control-next-icon"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>



					</div>


				</div>

				<div class="col-sm-3">
					<section
						class="page-section bg-primary text-white mb-0 divborder  shadow-lg p-3 mb-5 rounded"
						id="about">
						<div class="container"
							style="background-color: #1abc9C; margin-right: 4cm;">
							<!-- About Section Heading -->
							<h2
								class="page-section-heading text-center text-uppercase text-white">
								Dernier Resultat</h2>

							<!-- Icon Divider -->
							<div class="divider-custom divider-light ">
								<div class="divider-custom-line"></div>
								<div class="divider-custom-icon">
									<i class="fas fa-star"></i>
								</div>
								<div class="divider-custom-line"></div>
							</div>

							<!-- About Section Content -->
							<div class="row">
								<div class="col-lg-4 ml-auto">
									<p class="lead">Equipe1 : score 1</p>
								</div>
								<div class="col-lg-4 mr-auto">
									<p class="lead">Equipe2 : score 2</p>
								</div>
							</div>
						</div>
					</section>
					<br />
					<section
						class="page-section bg-primary text-white mb-0 divborder  shadow-lg p-3 mb-5 rounded"
						id="about">
						<div class="container"
							style="background-color: #1abc9C; margin-right: 4cm;">
							<!-- About Section Heading -->
							<h2
								class="page-section-heading text-center text-uppercase text-white">
								Flux d'actualité</h2>

							<!-- Icon Divider -->
							<div class="divider-custom divider-light">
								<div class="divider-custom-line"></div>
								<div class="divider-custom-icon">
									<i class="fas fa-star"></i>
								</div>
								<div class="divider-custom-line"></div>
							</div>

							<!-- About Section Content -->
							<div class="row">
								<div class="col-lg-4 ml-auto">
									<p class="lead">Flux 1</p>
									<p class="lead">Flux 1</p>
								</div>
								<div class="col-lg-4 mr-auto">
									<p class="lead">Flux 2</p>
									<p class="lead">Flux 1</p>
								</div>
							</div>
						</div>
					</section>
				</div>

			</div>
		</div>

	</section>

	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>

	<!-- Custom scripts for this template -->
	<script src="js/freelancer.min.js"></script>

	<script src="js/bootstrap.bundle.js"></script>
	<script src="js/utils.js"></script>
	<script src="js/slideshow.js"></script>
</body>


</html>
