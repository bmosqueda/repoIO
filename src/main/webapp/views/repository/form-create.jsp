<style>
  html,body {
    font-family: 'Open Sans', serif;
    background: #F2F6FA;
  }
  footer {
    background-color: #F2F6FA !important;
  }
  .type-ico {
    font-size: 100px !important;
  }
  .topNav {
    border-top: 5px solid #3498DB;
  }
  .topNav .container {
    border-bottom: 1px solid #E6EAEE;
  }
  .container .columns {
    margin: 3rem 0;
  }
  .navbar-menu .navbar-item {
    padding: 0 2rem;
  }
  aside.menu {
    padding-top: 3rem;
  }
  aside.menu .menu-list {
    line-height: 1.5;
  }
  aside.menu .menu-label {
    padding-left: 10px;
    font-weight: 700;
  }
  .button.is-primary.is-alt {
    background: #00c6ff;
    background: -webkit-linear-gradient(to bottom, #0072ff, #00c6ff);
    background: linear-gradient(to bottom, #0072ff, #00c6ff);
    font-weight: 700;
    font-size: 14px;
    height: 3rem;
    line-height: 2.8;
  }
  .media-left img {
    border-radius: 50%;
  }
  .media-content p {
    font-size: 14px;
    line-height: 2.3;
    font-weight: 700;
    color: #8F99A3;
  }
  article.post {
    margin: 1rem;
    padding-bottom: 1rem;
    border-bottom: 1px solid #E6EAEE;
  }
  article.post:last-child {
    padding-bottom: 0;
    border-bottom: none;
  }
  .menu-list li{
    padding: 5px;
  }
</style>
<div id="app">
  <section class="container">
    <div class="columns">
      <div class="column is-5">
        <h1 class="subtitle is-3 has-text-centered">Informaci&oacute;n del repositorio</h1>
        <div class="box content">
          <div class="field">
            <label class="label modal-label">T&iacute;tulo<span class="has-text-danger">*</span></label>
            <div class="control">
              <input class="input" type="text" placeholder="Número de cuenta" v-model="name">
            </div>
          </div>

          <div class="field">
            <label class="label modal-label">URL<span class="has-text-danger">*</span></label>
            <div class="control">
              <input class="input" type="text" placeholder="Número de cuenta" v-model="url">
            </div>
          </div>
          

          <div class="field">
            <label class="label modal-label">Etiquetas</label>
            <div class="control">
              <input class="input" type="tags" placeholder="Add Tag">
            </div>
          </div>
          
          <!-- <div class="field">
            <label class="label modal-label">Categor&iacute;a</label>
            <div class="control">
              <div class="select" style="width: 100%;">
                <select v-model="category_id" style="width: 100%;">
                  <option v-for="cat in categories" value="cat.category_id">{{cat.name}}</option>
                </select>
              </div>
            </div>
          </div> -->
          <label class="label modal-label">Categor&iacute;as</label>
          <div class="columns" style="margin-top: 10px;margin-bottom: 10px;">
            <div class="column is-half">
              <div v-for="(cat, index) in categories">
                <label v-if="index % 2 == 0" class="checkbox">
                  <input type="checkbox" v-model="repoCategories" :value="cat.category_id">
                  {{cat.name}}
                </label>
              </div>
            </div>
            <div class="column is-half">
              <div v-for="(cat, index) in categories">
                <label v-if="index % 2 == 1" class="checkbox">
                  <input type="checkbox" v-model="repoCategories" :value="cat.category_id">
                  {{cat.name}}
                </label>
              </div>
            </div>
          </div>

          <p class="help feed is-danger" v-for="err in errors.repo">{{err}}</p>
          <div class="field">
            <div class="control">
              <a class="button is-primary is-block is-alt is-large" @click="addRepository">Guardar</a>
            </div>
          </div>              
        </div>
      </div>
      <!-- Resources -->
        <div class="column is-7">
          <a class="button is-primary is-block is-alt is-large" @click="showAdd">Agregar recurso</a>
          <aside class="menu">
              <p class="menu-label">
                Recursos
              </p>
              <h1 v-if="resources.length == 0" class="subtitle is-3 has-text-centered">A&uacute;n no has agregado recursos</h1>
              <div v-for="(res, index) in resources">
                <div class="card">
                  <header class="card-header">
                    <p class="card-header-title">
                      {{res.title}}
                    </p>
                    <a class="card-header-icon" aria-label="more options">
                      <span class="icon" @click="res.visible = !res.visible">
                        <i class="fas fa-angle-down" aria-hidden="true"></i>
                      </span>
                    </a>
                  </header>
                  <div :class="{'is-hidden' : !res.visible, 'card-content': true}">
                    <div class="content">
                      <div class="columns">
                        <div class="column is-4 is-centered">
                          <i v-if="res.type == 1" class="fas fa-video has-text-info type-ico"></i>
                          <i v-else-if="res.type == 2" class="fas fa-volume-up has-text-info type-ico"></i>
                          <i v-else-if="res.type == 3" class="fas fa-book has-text-grey type-ico"></i>
                          <i v-else-if="res.type == 4" class="far fa-file has-text-grey type-ico"></i>
                          <i v-else class="fas fa-feather has-text-grey type-ico"></i>
                          <!-- <i class="far fa-file-pdf"></i> -->
                        </div>
                        <div class="column is-8">
                          {{res.description}}
                        </div>
                      </div>
                      <!-- Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec iaculis mauris.
                      <a href="#">@bulmaio</a>. <a href="#">#css</a> <a href="#">#responsive</a>
                      <br>
                      <time datetime="2016-1-1">11:09 PM - 1 Jan 2016</time> -->
                    </div>
                  </div>
                  <footer :class="{'is-hidden' : !res.visible, 'card-footer': true}">
                    <button class="card-footer-item button is-primary" @click="editResource(index)">Editar</button>
                    <button class="card-footer-item button is-danger" @click="deleteResource(index)">Eliminar</button>
                  </footer>
                </div>
                <br>
              </div>
            </aside>
          </div>
        </div>
      <!-- Resources END -->
    </section>
  
  <!-- Modal section -->
    <div v-bind:class="{'is-active' : isModalVisible, modal : true}">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <p class="modal-card-title">Agregar recurso</p>
          <button class="delete" aria-label="close" @click="isModalVisible = false"></button>
        </header>
        <section class="modal-card-body">                
            <div class="field">
              <label class="label modal-label">T&iacute;tulo<span class="has-text-danger">*</span></label>
              <div class="control">
                <input class="input" type="text" placeholder="T&iacute;tulo" v-model="resource.name">
              </div>
            </div>

            <div class="field">
              <label class="label modal-label">Descripci&oacute;n</label>
              <div class="control">
                <textarea maxlength="300" v-model="resource.description" class="textarea" placeholder="Descripci&oacute;n..."></textarea>
              </div>
            </div>
            
            <div class="field">
              <label class="label modal-label">Tama&ntilde;o <span class="has-text-muted">(en MB)</span><span class="has-text-danger">*</span></label>
              <div class="control">
                <input class="input" type="number" v-model="resource.size" placeholder="Tama&ntilde;o">
              </div>
            </div>
            

            <label class="label modal-label">&Aacute;reas</label>
            <div class="columns" style="margin-top: 10px;margin-bottom: 10px;">
              <div class="column is-half">
                <div v-for="(area, index) in areas">
                  <label v-if="index % 2 == 0" class="checkbox">
                    <input type="checkbox" v-model="resource.areas" :value="area.area_id">
                    {{area.name}}
                  </label>
                </div>
              </div>
              <div class="column is-half">
                <div v-for="(area, index) in areas">
                  <label v-if="index % 2 == 1" class="checkbox">
                    <input type="checkbox" v-model="resource.areas" :value="area.area_id">
                    {{area.name}}
                  </label>
                </div>
              </div>
            </div>
            
            <label class="label modal-label">Autores</label>
            <div class="field is-grouped">
              <p class="control is-expanded">
                <input class="input" type="text" placeholder="Autores" list="authorsList">
                  <datalist id="authorsList">
                    <option v-for="author in authors" :value="author.author_id">{{author.name}}</option>
                  </datalist>
              </p>
              <p class="control">
                <button class="button is-success" @click="pushAuthor">Buscar</button>
              </p>
            </div>
            
            <table :class="{table: true, 'is-hidden': authors.length == 0}" style="width: 100%;">
              <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Alias</th>
                    <th>Agregar</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="author in authors">
                    <th>{{author.name}}</th>
                    <th>{{author.alias}}</th>
                    <th>
                      <button :value="author.author_id" class="button is-info">Agregar</button>
                    </th>
                  </tr>
                </tbody>
            </table>
            <span v-for="author in resource.authors">{{author.name}}, </span>
            <br>

            <div class="field">
              <label class="label modal-label">Tipo<span class="has-text-danger">*</span></label>
              <div class="control">
                <div class="select" style="width: 100%;">
                  <select v-model="resource.type" style="width: 100%;">
                    <option value="1" selected>Video</option>
                    <option value="2">Audio</option>
                    <option value="3">Libro</option>
                    <option value="4">Documento</option>
                    <option value="5">Otro</option>
                  </select>
                </div>
              </div>
            </div>

            <div class="field">
              <label class="label modal-label">URL<span class="has-text-danger">*</span></label>
              <div class="control">
                <input class="input" type="text" v-model="resource.url" placeholder="Número de cuenta" v-model="url">
              </div>
            </div>
            <div id="divResourceError">
              <p class="help feed is-danger" v-for="err in errors.resource">{{err}}</p>
            </div>
        </section>
        <footer class="modal-card-foot">
          <button class="button is-primary is-block is-alt" @click="addResource">Agregar</button>
          <button class="button" @click="isModalVisible = false">Cancelar</button>
        </footer>
      </div>
    </div>
  <!-- Modal section END -->
</div>