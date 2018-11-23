<%@ page contentType="text/html;charset=utf-8" %>
<html>
  <head> 
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- <link rel="icon" href="/public/images/Logos/transparent-ico.png"> -->
    <link rel="stylesheet" href="/repo.io/public/fontawesome/css/all.min.css">

    <!-- Bulma -->
    <link rel="stylesheet" href="/repo.io/public/css/bulma.min.css">

    <title><%= title %></title>
  </head>
  <body class="is-bold is-light">
    <!-- NavBar -->
      <nav class="navbar is-link ">
        <div class="navbar-brand">
          <a class="navbar-item" href="/">
            <img class="logo-no-mobile" src="/public/images/Logos/logo-10.jpeg" alt="Repo.IO"  width="300" height="2000">
            <!-- <figure class="logo-mobile image is-32x32 is-pulled-right text-mobile">
              <img src="/public/images/Logos/transparent-ico.png" alt="Sociedad Patrimonial 2018 ©">
          </figure> -->
          </a>
          <div class="navbar-burger burger" data-target="navbar">
            <span></span>
            <span></span>
            <span></span>
          </div>
        </div>

        <div id="navbar" class="navbar-menu">
          <div class="navbar-start">
          </div>
          <div class="navbar-end">
            <div class="field is-grouped" style="margin-top:12px;">
              <p class="control is-expanded">
                <input class="input" type="text" placeholder="Buscar repositorios" id="txtSearch">
              </p>
              <p class="control">
                <button class="button is-info" id="btnSearch">
                  Buscar
                </button>
              </p>
            </div>
            <a class="navbar-item  has-text-weight-semibold" href="/">
              Mis repositorios
            </a>
            <a class="navbar-item has-text-weight-semibold" href="/nosotros/">
              Mi perfil
            </a>
            <a class="navbar-item has-text-weight-semibold" href="/repo.io/salir/">
              Salir
            </a>
          </div>
        </div>
      </nav>
    <!-- NavBar Fin -->