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
    }
  },
  mounted() {
    console.log("CREATED");
    window.axios.get('/repo.io/api/schools')
      .then(({data}) => {
        console.log(data);
        this.schools = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al recuperrar las escuelas");
        console.log(error);
        console.log(error.error);
        this.error = error.error;
      });
  },
  methods: {
    signUp(ev) {
      ev.preventDefault();

      let user = {
        account: this.account,
        name: this.name,
        email: this.email,
        school: this.school,
        password: this.password,
        confirmation: this.confirmation
      }

      window.axios.post('/repo.io/api/users', user)
        .then(({data}) => {
          //location.href = '/repo.io/buscar/';
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