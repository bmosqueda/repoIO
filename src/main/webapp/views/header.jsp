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
  <script>
    let gTitle = undefined;
  </script>
  <body class="is-bold is-light main">
    <!-- NavBar -->
      <nav class="navbar is-link ">
        <div class="navbar-brand">
          <a class="navbar-item" href="/repo.io/  ">
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
            <% if(Integer.parseInt(session.getAttribute("role_id").toString()) == 1) {%>
              <a class="navbar-item has-text-weight-semibold" href="/repo.io/catalogos/">
                Cat&aacute;logos
              </a>
              <a class="navbar-item has-text-weight-semibold" href="/repo.io/repositorio/crear/">
                Crear repositorio
              </a>
            <% }%>
            <!-- <div class="navbar-item has-dropdown is-hoverable">
              <a class="navbar-link" href="https://bulma.io/documentation/overview/start/">
                Repositorios
              </a>
                <div class="navbar-dropdown is-boxed">
                  <a class="navbar-item" href="/repo.io/repositorio/crear/">
                    Crear
                  </a>
                  <a class="navbar-item" href="/repositorio/mios/">
                    Mis repositorios
                  </a>
                </div>
            </div> -->
            <a class="navbar-item has-text-weight-semibold" href="/repo.io/perfil/">
              Mi perfil
            </a>
            <a class="navbar-item has-text-weight-semibold" href="/repo.io/salir/">
              Salir
            </a>
          </div>
        </div>
      </nav>
    <!-- NavBar Fin -->