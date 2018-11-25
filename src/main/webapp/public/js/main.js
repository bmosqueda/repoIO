const app = new Vue({
  el: "#app",
  data: {
    selectedArea: 0,
    selectedCategory: 0,
    tags: '',
    classTag: 'is-link tag',
    repositories: [],
    areas: [],
    categories: []
  },
  created() {
    let url = '/repo.io/api/repositories/' + (gTitle !== null ? 'title?title='+gTitle : 'newest');
    //Pedir los repositorios
    window.axios.get(url)
      .then(({data}) => {
        this.repositories = data;
        this.formatTags();
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
    formatTags(){
      for (var i = this.repositories.length - 1; i >= 0; i--) {
        for (var j = this.repositories[i].tags.length - 1; j >= 0; j--) {
          let tempTag = {
            tag: this.repositories[i].tags[j],
            class: this.getTagClass()
          }
          this.repositories[i].tags[j] = tempTag;
        }
      }
    },
    getTagClass() {
      let random = Math.round(Math.random() * 5) + 1;
      let tagClass = 'tag is-';
      switch(random) {
        case 1:
          return tagClass + 'primary';
        case 2:
          return tagClass + 'link';
        case 3:
          return tagClass + 'info';
        case 4:
          return tagClass + 'success';
        case 5:
          return tagClass + 'warning';
        default:
          return tagClass + 'danger';
      }
    },
    changeArea() {
      this.selectedCategory = 0;
      window.axios.get('/repo.io/api/repositories/area/'+this.selectedArea)
        .then(({data}) => {
          this.repositories = data;
          this.formatTags();
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al obtener los repositorios");
          console.log(error.error);
        });
    },
    changeCategory() {
      this.selectedArea = 0;

      window.axios.get('/repo.io/api/repositories/category/'+this.selectedCategory)
        .then(({data}) => {
          this.repositories = data;
          this.formatTags();
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al obtener los repositorios");
          console.log(error.error);
        });
    },
    searchByTag() {
      window.axios.post('/repo.io/api/repositories/keyword', {keywords: this.tags})
        .then(({data}) => {
          this.repositories = data;
          this.formatTags();
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al obtener los repositorios");
          console.log(error.error);
        });
    }
  }
});
