<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
  <head>
    <title></title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/bootstrap.css" />
    <script type="text/javascript" src="js/jQuery.js"></script>
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

  <header id="includedContent"></header>

  <body>
    <section class="page-section portfolio" id="portfolio">
      <div class="container">
        <div class="row">
          <div class="col-sm-3  shadow-lg p-3 mb-5 rounded" style="background-color:#1abc9C; margin-right: 0.5cm;">
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
                GestionFacture gestionnaireRencontre = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionFacture();
                ArrayList<TupleFacture> factures = gestionnaireRencontre.listerFactures();
                TupleFacture lastFacture= gestionnaireRencontre.listerFactures().get(1);
                
                
                GestionRencontre gestionnaire = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionReservation();
                ArrayList<TupleRencontre> rencontres = gestionnaire.getRencontre().listeRencontres();
                
                %>

				  <%
                  for(int i=0;i<factures.size();i++){
                  %>  
                  <button type="button" class="btn btn-primary">
                    <h4 class=" text-uppercase mb-4 text-white">
						<% String res= "Facutre " +  factures.get(i).getIdFacture() ; %>
                      
                      <%=res %> </h4>
                  </button>
                  <%
                  }
                  %>
                </div>
              </div>
              <div class="wrap" >
                <button class="button mb-4 text-black" style="position: absolute;bottom: 0;right: 0;">Ajouter une Facture</button>
              </div>
            </section>
            
          </div>
          <div
            class="col-sm  shadow-lg p-3 mb-5 rounded"
            style="background-color: #1abc9C; margin-right: 4cm;"
          >
            <div class="row " id="LieuDate">
              <div class="col-sm-6">
                <h2 class="  text-white">
<%= "  ID: " +lastFacture.getIdFacture() %>
                </h2>
              </div>
              <div class="col-6">
                <h2 class=" text-center  text-white">
                  Date :
                </h2>
                <h3 class=" text-center  text-white">
<% String d= "Vendredi le " +  lastFacture.getDate().getDate() + " "+ lastFacture.getMonthForInt(); %>
<%= d %>
				</h3>
              </div>
            </div>

            <ul class="nav nav-tabs">
              <li class="active">
                <a href="#detail" data-toggle="tab" style="background-color: white;"
                  ><h4 class=" text-uppercase mb-4 text-cyan">Détails</h4></a
                >
              </li>
              <li>
                <a href="#suivi" data-toggle="tab" style="background-color: white;"
                  ><h4 class=" text-uppercase mb-4 text-black ">
                    Suivi
                  </h4></a
                >
              </li>
            </ul>
            <div class="tab-content" id="divCont">
            	<div class="tab-pane fade in active" id="detail">
             		<div class="row ml-2" id="LabelsEquipes" style="background-color: #f0ffff00;">
			              <div class="col-sm">
			                <h2 class=" text-white" >
			                  Description:
			                </h2>
			
			               	<p class=" text-left  text-white"> 
								 <%=lastFacture.getDescription()%>
								 
							</p>
				              	<h3 class=" text-left  text-white"> Montant : <%=lastFacture.getPrix() %>
				              		 </h3>
							
			              		</div>
              	</div>           
                        
              	</div>


              
			<div class="tab-pane fade" id="suivi">

<div
              class="row ml-2"
              id="LabelsEquipes"
              style="background-color: #f0ffff00;"
            >
              <div class="col-sm-5" align="center">
                <h2 class=" text-center  text-black">
                  Joueurs ayant Payé
                </h2>
              </div>
              <div class="col-sm-5 col-sm-offset-2" align="center">
                <h2 class=" text-center  text-black">
                  Joueurs n'ayant pas payé
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
                  for(int i=0;i<rencontres.get(1).getEquipeA().size()-1;i++){
                  %>                  
                  <% String res= rencontres.get(1).getEquipeA().get(i).getNom() + " " +rencontres.get(1).getEquipeA().get(i).getPrenom(); %>
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
                  for(int i=0;i<rencontres.get(1).getEquipeA().size()-2;i++){
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
               
              
            </div>
            <div class="row" id="RowAnlEng" style="position: absolute;bottom: 0;right: 0;">
                <div class="wrap">
                    <button class="button mb-4 text-black">Annuler</button>
                  </div>
                    <div class="wrap">
                        <button class="button mb-4 text-black">Enregistrer</button>
                      </div>
              </div>
            <div
              class="row  d-flex justify-content-around"
              style="background-color: #f0ffff00"
              id="Equipes"
            >
              <div style="background-color: transparent;" col-sm-5" id="divcenter1">
                
              </div>
            </div>
            
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
  </body>
</html>
