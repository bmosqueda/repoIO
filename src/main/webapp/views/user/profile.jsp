<%! String currentPage = "repositorio/ver/"; %>
<%@ include file="../require-session.jsp"%>
<%! String title = "Mi perfil"; %>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="/repo.io/public/css/profile.css" />

  <div class="columns" id="app"> 
    <!-- User info -->
      <div class="column has-background-light is-4" id="col-repo">
        <figure class="avatar has-text-centered">
          <img src="https://placehold.it/128x128">
        </figure>

        <h1 class="title is-3">{{user.name}}</h1>

        <h1 class="title is-5">N&uacute;mero de cuenta</h1>
        <h1 class="subtitle">{{user.account_number}}</h1>
        <hr>

        <h1 class="title is-5">Correo electr&oacute;nico</h1>
        <h1 class="subtitle">{{user.email}}</h1>
        <hr>

        <h1 class="title is-5">Escuela</h1>
        <h1 class="subtitle">{{user.school_name}}</h1>
        <hr>

      </div>
    <!-- User info END -->

    <!-- Mine repos -->
      <h1 
        class="has-text-centered title is-4"
        style="margin:auto;padding-top: 50px;"
        v-if="user.repositories.length == 0"
      >Actualmente no tienes repositorios, puedes entrar <a href="/repo.io/repositorio/crear/">aqu&iacute;</a> para crear uno nuevo</h1>
      <div class="column is-8" id="col-left" v-else>
        <table class="table">
          <thead>
            <th>Nombre</th>
            <th>URL</th>
            <th>Descripci&oacute;n</th>
            <th>Detalles</th>
          </thead>
          <tbody>
            <tr v-for="repo in user.repositories">
              <td>{{repo.name}}</td>
              <td><a :href="repo.url">{{repo.url}}</a></td>
              <td>{{repo.description}}</td>
              <td>
                <button class="button is-info" @click="viewRepoDetails(repo.repository_id)">
                  <i class="fas fa-info-circle"></i>
                  &nbsp;Detalles
                </button>
              </td>
            </tr>
          </tbody>
        </table>  
      </div>
    <!-- Mine repos END -->

    <!-- Repo details -->
      <div :class="{modal: true, 'is-active': isActiveModal}">
        <div class="modal-background" @click="isActiveModal = false"></div>
        <div class="modal-content">
          <section class="modal-card-body">
            <h1 class="title is-3">{{repository.name}}</h1>

            <h1 class="title is-5">URL</h1>
            <a target="_blank" :href="repository.url">{{repository.url}}</a>
            <hr>
            <h1 class="title is-5">Etiquetas:</h1>
            <div class="tags" style="margin-bottom: 0rem;">
              <span :class="tag.class" v-for="tag in repository.tags">{{tag.tag}}</span>
            </div>
            <hr>
            <h1 class="title is-5">Categor&iacute;as:</h1>
            <div class="columns is-multiline">
              <div class="column is-half" v-for="cat in repository.categories">
                &#9642;&nbsp;{{cat.name}}
              </div>
            </div>
            <hr>
            <h1 class="title is-5">Descripci&oacute;n</h1>
            <h1>{{repository.description}}</h1>
            <hr>
            <h1 class="title is-5">Creado por:</h1>
            <h1>{{repository.creator_name}}</h1>
            <hr>
            <div class="field">
              <div class="control">
                <a :href="'/repo.io/repositorio/ver/?id='+repository.repository_id" class="button is-link">Ver completo</a>
              </div>
            </div>
          </section>
        </div>
        <button class="modal-close is-large" @click="isActiveModal = false" aria-label="close"></button>
      </div>
    <!-- Repo details END -->
  </div>

<%@ include file="../footer.jsp"%>
<script src="/repo.io/public/js/user/profile.js"></script>
</body>
</html>