const vue = new Vue({
  el: '#app',
  data: {
    email: '',
    password: '',
    error: ''
  },
  methods: {
    login(ev) {
      ev.preventDefault();
      if(this.email === '' || this.password === '') {
        alert('El correo y la contraseña son necesarios');
        return;
      }

      let user = {
        email: this.email,
        password: this.password
      }

      window.axios.post('/repo.io/api/users/login', user)
        .then(({data}) => {
          location.href = '/repo.io/buscar/';
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al logguearte");
          console.log(error);
          console.log(error.error);
          this.error = error.error;
        });
    }
  }
});