const app = new Vue({
  el: "#app",
  data: {
    classTag: 'is-link tag',
    repositories: [],
    areas: [],
    categories: []
  },
  created() {
    //Pedir los repositorios
    window.axios.get('/repo.io/api/repositories/newest')
      .then(({data}) => {
        this.repositories = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al obtener los repositorios");
        console.log(error.error);
      });

    //Pedir los repositorios
    window.axios.get('/repo.io/api/categories/all')
      .then(({data}) => {
        this.categories = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al obtener las categorías");
        console.log(error.error);
      });

    //Pedir los repositorios
    window.axios.get('/repo.io/api/areas/all')
      .then(({data}) => {
        this.areas = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al obtener las áreas");
        console.log(error.error);
      });
  },
  methods: {
    getTagClass(random) {
      switch(random) {
        case 1:
          return classTag + 'primary';
        case 1:
          return classTag + 'link';
        case 1:
          return classTag + 'info';
        case 1:
          return classTag + 'success';
        case 1:
          return classTag + 'warning';
        default:
          return classTag + 'danger';
      }
    }
  }
});
