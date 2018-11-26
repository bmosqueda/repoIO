const vue = new Vue({
  el: '#app',
  data: {
    account: 0,
    name: '',
    email: '',
    school: '',
    schools: [],
    password: '',
    confirmation: '',
    error: {
      account: '',
      name: '',
      email: '',
      school: '',
      password: '',
      confirmation: ''
    }, 
    response: ''
  },
  mounted() {
    console.log("CREATED");
    window.axios.get('/repo.io/api/schools/all')
      .then(({data}) => {
        console.log(data);
        this.schools = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al recuperrar las escuelas");
        console.log(error);
        console.log(error.error);
        this.response = error.error;
      });
  },
  methods: {
    test() {
      this.account = '20145969';
      this.name = 'Brandon Mosqueda';
      this.email = 'bmosqueda@ucol.mx';
      this.school = 1;
      this.password = 'hola';
      this.confirmation = 'hola';
    },
    signUp(ev) {
      ev.preventDefault();

      if(this.account === '')
        this.error.account = 'Este campo es requerido';
      else if(isNaN(this.account))
        this.error.account = 'El número de cuenta debe ser un número';
      else
        this.error.account = '';

      if(this.name === '')
        this.error.name = 'Este campo es requerido';
      else
        this.error.name = '';

      if(this.password === '')
        this.error.password = 'Este campo es requerido';
      else
        this.error.password = '';

      if(this.confirmation === '')
        this.error.confirmation = 'Este campo es requerido';
      else
        this.error.confirmation = '';

      if(this.confirmation !== this.password)
        this.error.confirmation = 'Las contraseñas no coinciden';
      else
        this.error.confirmation = '';

      if(this.school === '')
        this.error.school = 'Este campo es requerido';
      else
        this.error.school = '';

      for(let prop in this.error) {
        if(this.error[prop] !== '')
          return;
      }

      let user = {
        account_number: this.account,
        name: this.name,
        email: this.email,
        school_id: this.school,
        password: this.password,
        confirmation: this.confirmation
      }

      window.axios.post('/repo.io/api/users', user)
        .then(({data}) => {
          alert('Se creó tu usuario exitosamente');
          location.href = '/repo.io/buscar/';
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al logguearte");
          console.log(error);
          console.log(error.error);
          this.response = error.error;
        });
    }
  }
});