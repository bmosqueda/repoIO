<%! String currentPage = "buscar/"; %>
<%@ include file="require-session.jsp"%>
<%! String title = "Repo.io"; %>
<%@ include file="header.jsp"%>
  <style>
    #col-left {
      margin-top: 1rem;
    }

    .card-content {
      height: 300px;
      overflow: hidden;
    }

    .is-shady {
      animation: flyintoright .4s backwards;
      background: #fff;
      box-shadow: rgba(0, 0, 0, .1) 0 1px 0;
      border-radius: 4px;
      /*display: inline-block;*/
      margin: 10px;
      position: relative;
      transition: all .2s ease-in-out;
    }
    .card {
      box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.18);
      margin-bottom: 2rem;
    }
    .is-shady:hover {
      box-shadow: 0 10px 16px rgba(0, 0, 0, .13), 0 6px 6px rgba(0, 0, 0, .19);
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
            class="input is-primary" 
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
          <h1 
            class="has-text-centered title is-4"
            style="margin:auto;padding-top: 50px;"
            v-if="repositories.length == 0"
          >No se encontraron repositorios, prueba buscando de otra forma</h1>
          <h1 
            class="has-text-centered title is-4"
            style="margin:auto;padding-top: 20px;padding-bottom: 15px;"
            v-else
          >Repositorios</h1>
        <div class="columns is-multiline">
          <div class="column is-half" v-for="repo in repositories">
            <div class="card is-shady">
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

                  <strong><p>Descripci&oacute;n</p></strong>
                  <p>{{repo.description}}</p>
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
  gTitle = <%= param %>;
</script>
<script src="/repo.io/public/js/main.js"></script>
</body>
</html>