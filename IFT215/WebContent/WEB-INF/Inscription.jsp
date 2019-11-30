<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
      <meta charset="ISO-8859-1" />
      <title>Inscription</title>
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
          <div
            class="col-sm  shadow-lg p-3 mb-5 rounded"
            style="background-color: #1abc9C; margin-right: 4cm;"
          >
          <br></br>
            <div class="row " id="LieuDate">
              <div class="col-sm-6 col-sm-offset-2">
                <h2 class="  text-white">Formulaire d'inscription</h2>
              </div>
            </div>
            <br></br><br></br>
            <div
              class="row ml-2"
              id="LabelsEquipes"
              style="background-color: #f0ffff00;"
            >
              <div class="col-sm">
                <form action="Inscription" method="POST">
                  <div class="row">
                    <div class="col-sm-7 col-sm-offset-1">
                      <div class="form-group">
                        <h2
                          class="  text-white"
                          title="Vous utiliserez ce courriel pour vous authentifier sur le site"
                        >
                          Courriel
                        </h2>
                        <input
                          type="email"
                          name="Nom"
                          class="form-control InputW"
                          id="idNom"
                        />
                      </div>
                      <div class="form-group">
                        <h2
                          class="  text-white"
                          title="Noms et prnoms usuels, ex: Lionel Messi"
                        >
                          Nom et prnom
                        </h2>
                        <input
                          type="text"
                          name="Prenom"
                          class="form-control InputW"
                          id="idPrenom"
                        />
                      </div>
                      <div class="form-group">
                        <h2
                          class="  text-white"
                          title="ex: 1985-07-28 (AAAA-MM-JJ)"
                        >
                          Date de naissance
                        </h2>
                        <input
                          type="Date"
                          name="DateN"
                          class="form-control InputW"
                          id="idDateN"
                        />
                      </div>
                      <div class="form-group">
                        <h2
                          class="  text-white"
                          title="Minimum 6 caractres, au moins un chiffre et une majuscule"
                        >
                          Mot de passe
                        </h2>
                        <input
                          type="password"
                          name="motP"
                          class="form-control"
                          id="idAdrs"
                        />
                      </div>
                      <div class="form-group">
                        <h2
                          class="  text-white"
                          title="Numro, rue, ville, code postal"
                        >
                          Adresse
                        </h2>
                        <input
                          type="text"
                          name="Adrs"
                          class="form-control"
                          id="idAdrs"
                        />
                      </div>
                      <br></br><br></br>
                      <div class="form-group">
                        <h2
                          class="  text-white"
                          title="Le niveau Joueur permet de s'inscrire aux parties, contrairement au niveau Spectateur."
                        >
                          Niveau:
                        </h2>
                        <div class="radio"style="margin-left: 40px;">
                          <label>
                            
                              <input
                                type="radio"
                                name="optionsRadios"
                                id="optionsRadios1"
                                value="Joueur"
                              />
                              <h3 class="  text-white">Joueur</h3>
                              
                          </label>
                        </div>
                        <div class="radio" style="margin-left: 40px;">
                          <label>
                            <input
                                type="radio"
                                name="optionsRadios"
                                id="optionsRadios2"
                                value="Spectateur"
                              />
                             
                             <h3 class="text-white">spectateur</h3> 
                          </label>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-sm-4" id="colImg" align="center">
                        <br></br><br></br>
                      <img
                        src="img/personne.png"
                        class="divborder"
                        style="height: 215px; width: 200px;"
                      />

                      <br />
                      <br />
                      <button
                        class="button mb-4 text-black"
                        id="AjtTof"
                        style="width: 150px;"
                      >
                        Ajouter une photo
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <div
              class="row"
              id="RowAnlEng"
              style="position: absolute;bottom: 0;right: 0;"
            >
              <div class="wrap">
                <button class="button mb-4 text-black">Annuler</button>
              </div>
              <div class="wrap">
                <button class="button mb-4 text-black">Enregistrer</button>
              </div>
            </div>
            <br></br><br></br><br></br>
            <!-- <div
              class="row  d-flex justify-content-around"
              style="background-color: #f0ffff00"
              id="Equipes"
            >
              <div style="background-color: transparent;" col-sm-5" id="divcenter1">
                
              </div>
            </div> -->
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
