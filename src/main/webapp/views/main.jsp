<%! String currentPage = "buscar/"; %>
<%@ include file="require-session.jsp"%>
<%! String title = "Repo.io"; %>
<%@ include file="header.jsp"%>
  <style>
    #col-left {
      margin-top: 1rem;
    }

    .card-content {
      height: 250px;
      overflow: hidden;
    }
  </style>
  <div class="columns" id="app"> 
    <div class="column has-background-light is-3" style="padding-top: 3rem;padding-left: 3rem;">
      <h1 class="title is-5">Áreas</h1>
      <div class="columns is-multiline">
        <div v-for="area in areas" class="column is-half">
          <div class="control">
            <label class="radio">
              <input 
                type="radio" 
                :value="area.area_id" 
                v-model="selectedArea" 
                @change="changeArea" 
                name="area"
              >
              {{area.name}}
            </label>
          </div>
        </div>
      </div>
      <hr>
      <h1 class="title is-5">Categorías</h1>
      <div class="columns is-multiline">
        <div v-for="category in categories" class="column is-half">
          <div class="control">
            <label class="radio">
              <input 
                type="radio" 
                name="category"
                :value="category.category_id" 
                v-model="selectedCategory" 
                @change="changeCategory"
              >
              {{category.name}}
            </label>
          </div>
        </div>
      </div>
      <hr>
      <h1 class="title is-5">Etiquetas</h1>
      <div class="field is-grouped" style="margin-top:12px;">
        <div class="control is-expanded">
          <input 
            class="input" 
            type="text" 
            placeholder="Etiquetas" 
            v-model="tags" 
            @keyup.enter="searchByTag">
        </div>
        <div class="control">
          <button class="button is-info" @click="searchByTag">
            Buscar
          </button>
        </div>
      </div>
      <p class="help">Separados por coma</p>
    </div>
    <div class="column is-9" id="col-left">
      <!-- Cards -->
        <div class="columns is-multiline">
          <h1 
            class="has-text-centered title is-4"
            style="margin:auto;padding-top: 50px;"
            v-if="repositories.length == 0"
          >No se encontraron repositorios, prueba buscando de otra forma</h1>
          <div class="column is-half" v-for="repo in repositories">
            <div class="card" style="height: 100%;">
              <header class="card-header">
                <p class="card-header-title">
                  {{repo.name}}
                </p>
              </header>
              <div class="card-content">
                <div class="content">
                  <strong><p>Etiquetas</p></strong>
                  <div class="tags" style="margin-bottom: 0rem;">
                    <div>
                      <span :class="tag.class" v-for="tag in repo.tags" >{{tag.tag}}</span>
                    </div>
                  </div>

                  <strong><p>Número de recursos</p></strong>
                  <p>{{repo.resources_count}}</p>
                  
                  <strong><p>URL</p></strong>
                  <a :href="repo.url">{{repo.url}}</a>
                  <br>
                  <!-- <strong><p>Categorías</p></strong>
                  <div class="tags">
                    <span class="tag" v-for="cat in repo.categories">{{cat.name}}</span>
                  </div> -->
                </div>
              </div>
              <footer class="card-footer">
                <a :href="'/repo.io/repositorio/ver/?id=' + repo.repository_id" class="card-footer-item">Ver más</a>
              </footer>
            </div>
          </div>
        </div>
      <!-- Cards END -->
    </div>
  </div>

<%@ include file="footer.jsp"%>
<script>
  <%  
    Object temp = request.getParameter("title");
    String param = "null";
    if(temp != null)
      param = "'"+temp.toString()+"'";
  %>
  let gTitle = <%= param %>;
</script>
<script src="/repo.io/public/js/main.js"></script>
</body>
</html>