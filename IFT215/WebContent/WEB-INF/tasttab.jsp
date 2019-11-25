<!DOCTYPE html>
<html>
<head>
  <title></title>
  <meta charset="UTF-8">
  <!--
  
  
  
  <script src="js/jquery-3.3.1.min.js"></script>-->
  <script type="text/javascript" src="js/jQuery.js"></script>
  <link rel="stylesheet" href="css/bootstrap.css">
  <script type="text/javascript" src="js/bootstrap.js"></script>
  <link rel="stylesheet" href="css/header.css">
</head>

<body style="margin: 50px;">

<ul class="nav navbar-nav navbar-right">
  <li>
    <div class="dropdown" id="HeaderForm" align="center">
      <button type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-user" style="font-size: 20px;"></span></button> <br> <small>Se connecter</small> 

      <form class="dropdown-menu" id="HeaderForm">
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
          <button id="BtnConx">connexion</button>
        </div>
        <div class="form-group HFP">      
          <a href="#" id="Ins"><small>Inscription</small> </a><br>
          <a href="#" id="MPO"><small>Mot de passe oublié?</small> </a>
        </div>
      </form>
    </div>
  </li>
</ul>

</body>
</html>