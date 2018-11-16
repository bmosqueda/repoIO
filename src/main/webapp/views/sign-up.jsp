<% 
//Ya está loggueado
if(session.getAttribute("email") != null)
	response.sendRedirect("/repo.io/buscar/");
%>
<%! String title = "Regístrate"; %>
<%@ include file="header-no-session.jsp"%>
    <div class="columns is-centered" id="app">
      <div class="column is-6-tablet is-12-mobile">
        
        <!-- Account number -->
        <div class="field">
          <label class="label modal-label">Número de cuenta<span class="has-text-danger">*</span></label>
          <div class="control has-icons-left">
            <input class="input" type="text" placeholder="Número de cuenta" v-model.numer="account">
            <span class="icon is-small is-left">
              <i class="fas fa-account"></i>
            </span>
          </div>
        </div>
        <p class="help feed is-danger"id="feed-account">{{error.account}}</p>

        <!-- Name -->
        <div class="field">
          <label class="label modal-label">Nombre <span class="has-text-danger">*</span></label>
          <div class="control has-icons-left">
            <input class="input" type="text" maxlength="100" placeholder="Nombre" v-model="name">
            <span class="icon is-small is-left">
              <i class="fas fa-user"></i>
            </span>
          </div>
        </div>
        <p class="help feed is-danger"id="feed-name">{{error.name}}</p>

        <!-- Email -->
        <div class="field">
          <label class="label modal-label">Correo <span class="has-text-danger">*</span></label>
          <div class="control has-icons-left has-icons-right">
            <input class="input" type="email" v-model="email" maxlength="100" placeholder="Correo de contacto">
            <span class="icon is-small is-left">
              <i class="fas fa-envelope"></i>
            </span>
          </div>
        </div>
        <p class="help feed is-danger" id="feed-email">{{error.email}}</p>

        <!-- School-id -->
          <div class="field">
            <label class="label modal-label">Escuela <span class="has-text-danger">*</span></label>
            <div class="control has-icons-left">
              <div class="select">
                <select v-model="school">
                  <option v-for="school in schools" :value="school.school_id">
                    {{school.name}}
                  </option>
                </select>
              </div>
              <div class="icon is-small is-left">
                <i class="fas fa-globe"></i>
              </div>
            </div>
          </div>
          <p class="help feed is-danger" id="feed-school">{{error.school}}</p>

        <!-- Password -->
          <div class="field">
            <label class="label modal-label">Contraseña <span class="has-text-danger">*</span></label>
            <div class="control has-icons-left has-icons-right">
              <input class="input" type="password" v-model="password" maxlength="50" placeholder="Contraseña">
              <span class="icon is-small is-left">
                <i class="fas fa-password"></i>
              </span>
            </div>
          </div>
          <p class="help feed is-danger" id="feed-password">{{error.password}}</p>

        <!-- Password confirmation -->
          <div class="field">
            <label class="label modal-label">Confirmación de contraseña <span class="has-text-danger">*</span></label>
            <div class="control has-icons-left has-icons-right">
              <input class="input" type="password" v-model="confirmation" maxlength="50" placeholder="Confirmación de contraseña">
              <span class="icon is-small is-left">
                <i class="fas fa-password"></i>
              </span>
            </div>
          </div>
          <p class="help feed is-danger" id="feed-confirmation">{{error.confirmation}}</p>
          <p class="help feed is-danger" id="feed-confirmation">{{response}}</p>

        <button class="button is-info is-pulled-right" @click="signUp">Registrarme</button>
      </div>
    </div>
<%@ include file="footer.jsp"%>
<script src="/repo.io/public/js/sign-up.js"></script>