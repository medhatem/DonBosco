
<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <script type="text/javascript" src="js/jQuery.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css" />

    <script type="text/javascript" src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/sheet.css" />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <link
      href="vendor/fontawesome-free/css/all.min.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Montserrat&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
      rel="stylesheet"
      type="text/css"
    />

    <!-- Theme CSS -->
    <link href="css/freelancer.min.css" rel="stylesheet" />
    <!--<script src="js/jquery-3.3.1.min.js"></script>-->


		<jsp:include page="/WEB-INF/header.jsp" />
		    <link rel="stylesheet" href="css/header.css">
		    
	<style>
      body {
        background-color: #e3ecef;
      }
    </style>
</head>



	<BODY>
	
				<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
	<jsp:include page="/WEB-INF/messageErreur.jsp" />
	
<section class="page-section portfolio" id="portfolio">
      <div class="container">
        <div class="row">
          <div class="col-sm-3  shadow-lg p-3 mb-5 rounded">
            <section class="page-section bg-primary text-white mb-0" id="about">
              <div class="container">
                <!-- About Section Heading -->
                <h2
                  class="page-section-heading text-center text-uppercase text-white"
                >
                  Dates de rencontres
                </h2>

                <!-- Icon Divider -->
                <div class="divider-custom divider-light">
                  <div class="divider-custom-line"></div>
                  <div class="divider-custom-icon">
                    <i class="fas fa-star"></i>
                  </div>
                  <div class="divider-custom-line"></div>
                </div>

                <!-- About Section Content -->
                <div class="btn-group-vertical">
                
                <%
                GestionRencontre gestionnaireRencontre = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionReservation();
                ArrayList<TupleRencontre> rencontres = gestionnaireRencontre.getRencontre().listeRencontres();
                
                TupleRencontre rencontreActuel=gestionnaireRencontre.getRencontre().listeRencontres().get(1);

                  for(int i=0;i<rencontres.size();i++){
                  %>
                  <button type="button " class="btn btn-primary" onclick=>
                    <h4 class=" text-uppercase mb-4 text-white">
                      <% String res= "Vendredi " + rencontres.get(i).getDate().getDay() + "  " + rencontres.get(i).getMonthForInt() ; %>
                      
                      <%=res %>
                    </h4>
                  </button>
                  <%
                  }
                  %>
                  
                </div>
              </div>

           	  <div class="row" id="RowAnlEng" style="position: absolute;bottom: 0;right: 0;">
			              <div class="wrap">
			                <button class="button mb-4 text-black">Ajouter</button>
			              </div>
				</div>	
            </section>
          </div>
          <div
            class="col-sm  shadow-lg p-3 mb-5 rounded"
            style="background-color: #1abc9C;"
          >
            <div class="row " id="LieuDate">
              <div class="col-sm-6">
                <h2 class="  text-white">
                  Lieu : <%=  rencontreActuel.getTerrain().getNom() %>
                </h2>
              </div>
              <div class="col-6">
                <h2 class=" text-center  text-white">
                  Date : <%=   "Vendredi " + rencontreActuel.getDate().getDay() + "  " + rencontreActuel.getMonthForInt() %>
                </h2>
 
              </div>
            </div>

			<ul class="nav nav-tabs">
              <li class="active">
                <a href="#equipes" data-toggle="tab" style="background-color: white;"
                  ><h4 class=" text-uppercase mb-4 text-cyan">Equipes</h4></a
                >
              </li>
              <li>
                <a href="#bilan" data-toggle="tab" style="background-color: white;"
                  ><h4 class=" text-uppercase mb-4 text-black ">
                    Bilan
                  </h4></a
                >
              </li>
            </ul>
            <div class="tab-content" id="divCont">
             	<div class="tab-pane fade in active" id="equipes">
			            <div
			              class="row ml-2"
			              id="LabelsEquipes"
			              style="background-color: #f0ffff00;"
			            >
			              <div class="col-sm-5" align="center">
			                <h2 class=" text-center  text-black">
			                  Equipe A
			                </h2>
			              </div>
			              <div class="col-sm-5 col-sm-offset-2" align="center">
			                <h2 class=" text-center  text-black">
			                  Equipe B
			                </h2>
			              </div>
			            </div>             	
			            <div
			              class="row  d-flex justify-content-around"
			              style="background-color: #f0ffff00"
			              id="Equipes"
			            >
			              <div style="background-color: transparent;" col-sm-5" id="divcenter1">
			                <div
			                  class="col-lg-4 mb-5 mb-lg-0 "
			                  style="background-color: f0ffff00";
			                >
			                  <ul style="list-style-type:square;">
			                  <%
			                  for(int i=0;i<rencontres.get(1).getEquipeA().size();i++){
			                  %>                  
			                  <% String res= rencontres.get(1).getEquipeB().get(i).getNom() + " " +rencontres.get(1).getEquipeB().get(i).getPrenom(); %>
			                    <li>
			                      <p class=" text-uppercase mb-4 text-black"><%=res %></p>
			                    </li>
			                  <%
			                  }
			                  %>
			                  </ul>
			                </div>
			              </div>
			              <div class=" col-sm-5 col-sm-offset-2" id="divcenter2">
			                <div class="col-lg-4 mb-5 mb-lg-0">
			                  <ul style="list-style-type:square;">
			                  <%
			                  for(int i=0;i<rencontres.get(1).getEquipeA().size();i++){
			                  %>                  
			                  <% String res= rencontres.get(1).getEquipeB().get(i).getNom() + " " +rencontres.get(1).getEquipeB().get(i).getPrenom(); %>
			                    <li>
			                      <p class=" text-uppercase mb-4 text-black"><%=res %></p>
			                    </li>
			                  <%
			                  }
			                  %>
			                  </ul>
			                </div>
			              </div>
			            </div>             	
             	</div>           
             	<div class="tab-pane fade" id="bilan">
             	
             	
             	</div>                  
            </div>            
            


            <section class=" text-center text-black">
           	  <div class="row" id="RowAnlEng" style="position: absolute;bottom: 0;right: 0;">
                <div class="wrap">
                    <button class="button mb-4 text-black">Annuler</button>
                  </div>
                    <div class="wrap">
                        <button class="button mb-4 text-black">Enregistrer</button>
                      </div>
              </div>

            </section>
          </div>

          <div class="col-sm-3">
            <section
              class="page-section bg-primary text-white mb-0 divborder  shadow-lg p-3 mb-5 rounded"
              id="about"
            >
              <div class="container">
                <!-- About Section Heading -->
                <h2
                  class="page-section-heading text-center text-uppercase text-white"
                >
                  Dernier Resultat
                </h2>

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
                  <%
                    
                    TupleRencontre  rencontreLast= (TupleRencontre)  session.getAttribute("DerniereRencontre");
                    
                    %>
                    <p class="lead">EquipeA : <%=rencontreLast.getScoreA() %></p>
                  </div>
                  <div class="col-lg-4 mr-auto">
                    <p class="lead">EquipeB :  <%=rencontreLast.getScoreB() %></p>
                  </div>
                </div>
              </div>
            </section>
            <br />
            <section
              class="page-section bg-primary text-white mb-0 divborder  shadow-lg p-3 mb-5 rounded"
              id="about"
            >
              <div class="container">
                <!-- About Section Heading -->
                <h2
                  class="page-section-heading text-center text-uppercase text-white"
                >
                  Flux d'actualité
                </h2>

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

	</BODY>
</html>

