<%! String currentPage = "repositorio/catalog/"; %>
<%@ include file="../require-session.jsp"%>
<%
  //Sólo para administradores
  if(Integer.parseInt(session.getAttribute("role_id").toString()) != 1)
    response.sendRedirect("/repo.io/buscar/");
%>
<%! String title = "Cat&aacute;logos"; %>
<%@ include file="../header.jsp"%>
  
  <div id="app">
    <section class="hero is-link">
       <div class="hero-body">
          <div class="container has-text-centered">
             <h1 class="title">
                Cat&aacute;logos
             </h1>
          </div>
       </div>
       <div class="hero-foot">
          <nav class="tabs is-boxed is-fullwidth is-large">
             <div class="container">
                <ul>
                  <li class="tab is-active" onclick="openTab(event,'tabArea')">
                    <a>&Aacute;reas</a>
                  </li>
                  <li class="tab" onclick="openTab(event,'tabSchool')">
                    <a>Escuelas</a>
                  </li>
                  <li class="tab" onclick="openTab(event,'tabCategory')">
                    <a>Categor&iacute;as</a>
                  </li>
                </ul>
             </div>
          </nav>
       </div>
    </section>
    <div class="container section">
      <!-- TAB AREAS -->
        <div id="tabArea" class="content-tab" >
          <div class="columns" style="margin-top: 2rem;">
            <div class="column is-6 is-offset-3">
              <div class="field">
                <label class="label">Nombre del &aacute;rea</label>
                <div class="control">
                  <input 
                    class="input" 
                    v-on:keyup.enter="addArea" 
                    type="text" 
                    v-model.numeric="newArea" 
                    maxlength="100" 
                    placeholder="Nombre del &aacute;rea">
                </div>
                <p class="help is-danger">{{errors.area}}</p>
              </div>
              <div class="field">
                <div class="control">
                  <input 
                    class="button is-success is-pulled-right" 
                    @click="addArea" 
                    v-if="onEditArea == -1"
                    type="button" 
                    value="Agregar">

                  <input 
                    class="button is-link is-pulled-right" 
                    @click="addArea" 
                    v-else
                    type="button" 
                    value="Editar">
                </div>
              </div>
            </div>
          </div>
          <div class="columns">
            <table class="table is-fullwidth is-hoverable">
              <thead>
                <tr>
                  <th>Nombre</th>
                  <th>Opciones</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(area, index) in areas">
                  <td>{{area.name}}</td>
                  <td>
                    <div class="field has-addons">
                      <p class="control">
                        <a class="button is-link is-small" @click="toggleEditArea(index)">
                          <span class="icon is-small">
                            <i class="fa fa-edit"></i>
                          </span>
                          <span>Editar</span>
                        </a>
                      </p>
                      <p class="control">
                        <a class="button is-small is-danger" @click="deleteArea(index)">
                          <span class="icon is-small">
                            <i class="fa fa-trash"></i>
                          </span>
                          <span>Eliminar</span>
                        </a>
                      </p>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      <!-- TAB AREAS  END-->
    </div>
  </div>

<%@ include file="../footer.jsp"%>
<script src="/repo.io/public/js/catalog/crud.js"></script>
</body>
</html>