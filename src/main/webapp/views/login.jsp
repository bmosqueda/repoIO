<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- <link rel="icon" href="/public/images/Logos/transparent-ico.png"> -->

<!-- Bulma -->
<link rel="stylesheet" href="/repo.io/public/css/bulma.min.css">

<title>Inicio de sesión</title>
</head>
<body class="is-bold is-light">

	<link rel="stylesheet" href="/repo.io/public/css/login.css">
	<section class="hero is-success is-fullheight">
		<div class="hero-body">
			<div class="container has-text-centered">
				<div class="column is-4 is-offset-4">
					<h3 class="title has-text-grey">Inicio de sesión</h3>
					<p class="subtitle has-text-grey">Bienvenido</p>
					<div class="box">
						<figure class="avatar">
							<img src="/repo.io/public/images/login.png">
						</figure>
						<form>
							<div class="field">
								<div class="control">
									<input class="input is-large" type="email"
										placeholder="Correo electrónico" autofocus="">
								</div>
							</div>

							<div class="field">
								<div class="control">
									<input class="input is-large" type="password"
										placeholder="Contraseña">
								</div>
							</div>
							<div class="field is-pulled-right">
								<label class="checkbox"> <input type="checkbox">
									Recordarme
								</label>
							</div>
							<button class="button is-block is-info is-large is-fullwidth">Iniciar
								sesión</button>
						</form>
					</div>
					<p class="has-text-grey">
						No tienes cuenta <a href="../">¿Regístrate?</a> &nbsp;·&nbsp;
						<!-- <a href="../">Forgot Password</a> &nbsp;·&nbsp; -->
						<!-- <a href="../">Need Help?</a> -->
					</p>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>