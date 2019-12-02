<%@ page import="java.util.*,java.text.*,equipeSoccer.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
  <head>
      <meta charset="ISO-8859-1" />
      <title>Factures</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/bootstrap.css" />
    <script type="text/javascript" src="js/jQuery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/sheet.css" />
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link
      rel="stylesheet"
      href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
    />
    <link rel="stylesheet" href="/resources/demos/style.css" />
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    
    <script>
      $(function() {
        $(document).tooltip();
      });
    </script>
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
            <div class="col-sm-3  shadow-lg p-3 mb-5 rounded" style="background-color:#1abc9C; margin-right: 0.5cm;">
              <section class="page-section bg-primary text-white mb-0" id="about">
                <div class="container">
                  <!-- About Section Heading -->
                  <h2
                    class="page-section-heading text-center text-uppercase text-white"
                  >
                    Groupe 1
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
                    <button type="button " class="btn btn-primary">
                
                <%
                GestionFacture gestionnaireRencontre = ((GestionEquipeSoccer) session.getAttribute("equipeSoccerInterrogation")).getGestionFacture();
                ArrayList<TupleFacture> factures = gestionnaireRencontre.listerFactures();
                TupleFacture lastFacture= gestionnaireRencontre.listerFactures().get(1);
                
                %>
				  <%
                  for(int i=0;i<factures.size();i++){
                  %>  
                  <button type="button " class="btn btn-primary">
                    <h4 class=" text-uppercase mb-4 text-white">
						<% String res= "Facutre " +  factures.get(i).getIdFacture() ; %>
                      
                      <%=res %> </h4>
                  </button>
                  <%
                  }
                  %>
                  </div>
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
                    Date : <%= "Vendredi le " + lastFacture.getDate().getDate()%>
                  </h2>

                </div>
              </div>
  
              <br></br> <br></br>
              <br></br><br></br>
              <div
                class="row ml-2"
                id="LabelsEquipes"
                style="background-color: #f0ffff00;"
              >
                <div class="col-sm" >
				<h2 class=" text-white" >
                  Description:
                </h2>

               	<p class=" text-left  text-white"> 
	 <%=
	 lastFacture.getDescription()
	 %>
	  </p>
	              	<h3 class=" text-left  text-white"> Montant : <%=lastFacture.getPrix() %>
	              		 </h3>
				
                 
                </div>
                
              </div>
              <div class="row" id="RowAnlEng" style="position: absolute;bottom: 0;right: 0;">
                  <!-- PayPal Logo -->
                      <div class="wrap">
                          <button class="button mb-4 text-black" style="background-color: #f0ffff00;">
                              <table border="0" cellpadding="10" cellspacing="0" align="center"><tr><td align="center"></td></tr><tr><td align="center"><a href="https://www.paypal.com/c2/webapps/mpp/paypal-popup?locale.x=en_C2" title="PayPal" onclick="javascript:window.open('https://www.paypal.com/c2/webapps/mpp/paypal-popup?locale.x=en_C2','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;"><img src="https://www.paypalobjects.com/digitalassets/c/website/marketing/apac/C2/logos-buttons/optimize/44_Blue_PayPal_Pill_Button.png" alt="PayPal" /></a></td></tr></table><!-- PayPal Logo -->
                          </button>
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
