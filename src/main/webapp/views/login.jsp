<% 
//Ya est� loggueado
if(session.getAttribute("email") != null)
	response.sendRedirect("/repo.io/buscar/");
%>
<%! String title = "Iniciar sesi�n"; %>
<%@ include file="header-no-session.jsp"%>
	<link rel="stylesheet" href="/repo.io/public/css/login.css">
	<section class="hero is-success is-fullheight">
		<div class="hero-body" id="app">
			<div class="container has-text-centered">
				<div class="column is-4 is-offset-4">
					<h3 class="title has-text-grey">Inicio de sesi�n</h3>
					<p class="subtitle has-text-grey">Bienvenido</p>
					<div class="box">
						<figure class="avatar">
							<img src="/repo.io/public/images/login.png">
						</figure>

							<div class="field">
								<div class="control">
									<input class="input is-large" type="email"
										placeholder="Correo electr�nico" v-model="email" autofocus="">
								</div>
							</div>

							<div class="field">
								<div class="control">
									<input class="input is-large" type="password" v-model="password"
										placeholder="Contrase�a">
								</div>
							</div>
							<div class="field is-pulled-right">
								<label class="checkbox"> <input type="checkbox">
									Recordarme
								</label>
							</div>
							<p class="help feed is-danger">{{error}}</p>
							<button class="button is-block is-info is-large is-fullwidth" @click="login">Iniciar
								sesi�n</button>
						
					</div>
					<p class="has-text-grey">
						No tienes cuenta <a href="../">�Reg�strate?</a> &nbsp;�&nbsp;
						<!-- <a href="../">Forgot Password</a> &nbsp;�&nbsp; -->
						<!-- <a href="../">Need Help?</a> -->
					</p>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
<script src="/repo.io/public/js/login.js"></script>