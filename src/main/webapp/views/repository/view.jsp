<%! String currentPage = "repositorio/ver/"; %>
<%@ include file="../require-session.jsp"%>
<%! String title = "Ver repositorio"; %>
<%@ include file="../header.jsp"%>
<link rel="stylesheet" href="/repo.io/public/css/view-repo.css" />

  <div class="columns" id="app"> 
    <!-- Repo info -->
      <div class="column has-background-light is-4" id="col-repo">
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

        <div class="columns is-multiline">
    
        </div>
        <hr>
      </div>
    <!-- Repo info END -->
    <div class="column is-8" id="col-left">
        <h1 
          class="has-text-centered title is-4"
          style="margin:auto;padding-top: 50px;"
          v-if="repository.resources.length == 0"
        >Este repositorio no cuenta con recursos</h1>
        <h1 
          class="has-text-centered title is-4"
          style="margin:auto;padding-top: 20px;padding-bottom: 15px;"
          v-else
        >Recursos</h1>
      <div class="columns is-multiline" style="margin-right: 4px;">
          <!-- Cards -->
            <div class="column is-half" v-for="res in repository.resources">
              <div class="card is-shady">
                <div class="card-image">
                  <div class="div-ico">
                    <i v-if="res.type == 1" class="fas fa-video has-text-info type-ico"></i>
                    <i v-else-if="res.type == 2" class="fas fa-volume-up has-text-info type-ico"></i>
                    <i v-else-if="res.type == 3" class="fas fa-book has-text-grey type-ico"></i>
                    <i v-else-if="res.type == 4" class="far fa-file has-text-grey type-ico"></i>
                    <i v-else class="fas fa-feather has-text-grey type-ico"></i>
                    <!-- <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image"> -->
                  </div>
                </div>
                <div class="card-content">
                  <p class="title is-4">{{res.title}}</p>
                  <p class="title is-6">Tama&ntilde;o</p>
                  <p class="subtitle is-6">{{res.size}} MB</p>
                  <p class="title is-6" style="padding-top: 7px;margin-bottom: 2px;">URL</p>
                  <a target="_blank" :href="res.url">{{res.url}}</a>
                </div>
                <footer class="card-footer">
                  <a @click="showModal(res)" class="card-footer-item">Ver m&aacute;s</a>
                </footer>
              </div>
            </div>
          <!-- Cards END -->
      </div>
    </div>
    <!-- View resource modal -->
      <div :class="{modal: true, 'is-active': isActiveModal}">
        <div class="modal-background" @click="isActiveModal = false"></div>
        <div class="modal-content">
          <section class="modal-card-body">
            <div class="card-image">
              <div class="div-ico">
                <i v-if="modalResource.type == 1" class="fas fa-video has-text-info type-ico"></i>
                <i v-else-if="modalResource.type == 2" class="fas fa-volume-up has-text-info type-ico"></i>
                <i v-else-if="modalResource.type == 3" class="fas fa-book has-text-grey type-ico"></i>
                <i v-else-if="modalResource.type == 4" class="far fa-file has-text-grey type-ico"></i>
                <i v-else class="fas fa-feather has-text-grey type-ico"></i>
                <!-- <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image"> -->
              </div>
            </div>
            <p class="title is-4">{{modalResource.title}}</p>

            <p class="title is-6">Tama&ntilde;o</p>
            <p class="subtitle is-6">{{modalResource.size}} MB</p>
            
            <p class="title is-6" style="padding-top: 7px;margin-bottom: 2px;">URL</p>
            <a target="_blank" :href="modalResource.url">{{modalResource.url}}</a>

            <h1 class="title is-6" style="padding-top: 7px;">&Aacute;reas:</h1>
            <div class="columns is-multiline">
              <div class="column is-third" v-for="area in modalResource.areas">
                &#9642;&nbsp;{{area.name}}
              </div>
            </div>

            <p class="title is-6">Descripci&oacute;n</p>
            <p class="subtitle is-6">{{modalResource.description}}</p>
  
            <p class="title is-6" style="padding-top: 7px;margin-bottom: 2px;">Autor(es)</p>
            <p
              v-if="modalResource.authors.length == 0"
            >-- Sin autores --</p>
            <p v-for="author in modalResource.authors">&#9642;{{author.name}}</p>
            <br>
          </section>
        </div>
        <button class="modal-close is-large" @click="isActiveModal = false" aria-label="close"></button>
      </div>
    <!-- View resource modal END -->
  </div>

<%@ include file="../footer.jsp"%>
<script>
  <%  
    Object temp = request.getParameter("id");
    String id = "null";
    if(temp != null)
      id = temp.toString();
  %>
  let gRepoId = <%= id %>;
</script>
<script src="/repo.io/public/js/repository/view.js"></script>
</body>
</html>