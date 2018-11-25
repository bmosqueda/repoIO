
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
						alt="Repo.io" width="200" height="1000">
				</figure>
			</a>
			<p class="has-text-light has-text-justified">Los meros meros habaneros</p>
		</div>
		<div class="column is-2-desktop is-5-tablet is-12-mobile">
			<p class="footer-title  title is-5 has-text-light">Página</p>
			<ul>
				<li class="pad-bot-li"><a href="/" class="has-text-light">Mi perfil</a>
				</li>
				<li class="pad-bot-li"><a href="/nosotros/"
					class="has-text-light">Mis repositorios</a></li>
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
						<i class="fa fa-envelope"></i> <span>&nbsp;repo_io@ucol.mx</span>
					</p>
				</li>
				<br>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;312 329 21 32</span>
						<!-- eli -->
					</p>
				</li>
				<li>
					<p class="has-text-light" href="#">
						<i class="fa fa-phone"></i> <span>&nbsp;312 323 18 51</span>
						<!-- ericka -->
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

  //Para el input de búsquedas en el header
  let searchInput = document.getElementById('txtSearch');
  document.getElementById('btnSearch').addEventListener('click', searchRepos);

  searchInput.addEventListener('keyup', function(ev) {
    if(ev.keyCode == 13)
      searchRepos();
  });

  function searchRepos() {
    if(searchInput.value.trim().length > 0)
    {
      if(gTitle === undefined)
        location.href = "/repo.io/buscar/?title="+searchInput.value.trim();
      else
      {
        window.axios.get('/repo.io/api/repositories/title?title='+searchInput.value.trim())
          .then(({data}) => {
            app.$data.repositories = data;
          })
          .catch(({response : {data : error}}) => {
            console.error("Hubo un problema al obtener los repositorios");
            console.log(error.error);
          });
      }
    }
  }
</script>
<!-- Vue.Js-->
<script src="/repo.io/public/js/vue.min.js"></script>
<script src="/repo.io/public/js/axios.min.js"></script>