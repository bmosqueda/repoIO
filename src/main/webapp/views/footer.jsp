
<!-- Footer -->
<footer class="footer has-background-link">
	<div class="columns is-multiline">
		<div class="column is-3-desktop is-6-tablet is-12-mobile"
			style="margin-right: 50px;">
			<a class="" href="#">
				<figure class="image"
					style="padding-top: 50px; padding-bottom: 50px;">
					<!-- <img src="/public/images/Logos/transparent-white.png" alt="Patrimonio social" width="200" height="1000"> -->
					<img src="/public/images/Logos/BetterLogo.png"
						alt="Patrimonio social" width="200" height="1000">
				</figure>
			</a>
			<p class="has-text-light has-text-justified">Los meros meros habaneros</p>
		</div>
		<div class="column is-2-desktop is-5-tablet is-12-mobile">
			<p class="footer-title  title is-5 has-text-light">Página</p>
			<ul>
				<li class="pad-bot-li"><a href="/" class="has-text-light">Inicio</a>
				</li>
				<li class="pad-bot-li"><a href="/nosotros/"
					class="has-text-light">Nosotros</a></li>
				<li class="pad-bot-li"><a href="/servicios/"
					class="has-text-light">Servicios</a></li>
				<li class="pad-bot-li"><a href="/privacidad/"
					class="has-text-light">Aviso de privacidad</a></li>
				<li class="pad-bot-li"><a href="/requisitos/"
					class="has-text-light">Requisitos</a></li>
				<li class="pad-bot-li"><a href="/preguntas-frecuentes/"
					class="has-text-light">Preguntas frecuentes</a></li>
				<li class="pad-bot-li"><a href="/contacto/"
					class="has-text-light">Contacto</a></li>
				<li class="pad-bot-li"><a href="/acceso/"
					class="has-text-light">Acceso</a></li>
			</ul>
		</div>
		<div class="column is-4-desktop is-7-tablet is-12-mobile">
			<p class="footer-title title is-5 has-text-light">Contáctanos</p>
			<ul>
				<!-- <li>
                <p class="has-text-light" href="#">
                  <i class="fa fa-envelope"></i>
                  <span>&nbsp;atencionaclientes@global.com.mx</span>
                </p>
              </li> -->
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-envelope"></i> <span>&nbsp;bmosqueda@ucol.mx</span>
					</p>
				</li>
				<br>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;55 68 65 37 76</span>
						<!-- eli -->
					</p>
				</li>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;55 44 97 56 46</span>
						<!-- ericka -->
					</p>
				</li>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;55 68 63 96 99</span>
						<!-- fer -->
					</p>
				</li>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;55 83 03 92 58</span>
						<!-- Luis ramirez -->
					</p>
				</li>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;55 27 95 94 02</span>
						<!-- alfredo -->
					</p>
				</li>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;55 30 31 31 04</span>
						<!-- rogelio -->
					</p>
				</li>
			</ul>
		</div>
		<div class="column is-2-desktop is-5-tablet is-12-mobile text-mobile">
			<p class="footer-title  title is-5 has-text-light">Síguenos</p>
			<a target="_blank" class="has-text-light"> <i
				class="fab fa-facebook" style="font-size: 3em;"></i>
			</a> <a target="_blank" class="has-text-light"> <i
				class="fab fa-twitter" style="font-size: 3em;"></i>
			</a> <a target="_blank"
				href="https://www.youtube.com/watch?v=RMZCogCCfM0"
				class="has-text-light"> <i class="fab fa-youtube"
				style="font-size: 3em;"></i>
			</a>
			<!-- <a target="_blank" class="has-text-light">
              <i class="fab fa-linkedin fa-3x"></i>
            </a> -->
			<!-- <p class="footer-title  has-text-light">Sociedad Patrimonial 2018 ©</p> -->
		</div>
	</div>
</footer>
<div class="has-text-centered has-background-link">
	<p class="footer-title has-text-light"
		style="margin: auto; padding: 0px;">5° D</p>
	<p class="footer-title has-text-light"
		style="margin: auto; padding-bottom: : 15px;">Equipo 3</p>
</div>
<script>
  document.addEventListener('DOMContentLoaded', () => {

    // Get all "navbar-burger" elements
    const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

    // Check if there are any navbar burgers
    if ($navbarBurgers.length > 0) {

      // Add a click event on each of them
      $navbarBurgers.forEach( el => {
        el.addEventListener('click', () => {

          // Get the target from the "data-target" attribute
          const target = el.dataset.target;
          const $target = document.getElementById(target);

          // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
          el.classList.toggle('is-active');
          $target.classList.toggle('is-active');

        });
      });
    }
  });
</script>
<!-- Vue.Js-->
<script src="/repo.io/public/js/vue.min.js"></script>
<script src="/repo.io/public/js/axios.min.js"></script>