const app = new Vue({
  el: "#app",
  data: {
    repository: {
      repository_id: 0,
      name: 0,
      url: '',
      description: '',
      creator_name: '',
      tags: [],
      categories: [],
      resources: []
    },
    modalResource: {
      title: "",
      description: "",
      size: "",
      type: "",
      url: "",
      authors: [],
      areas: []
    },
    isActiveModal: false
  },
  created() {
    window.axios.get('/repo.io/api/repositories/'+gRepoId)
      .then(({data}) => {
        let tags = data.tags;

        for (var i = tags.length - 1; i >= 0; i--) {
          tags[i] = {
            tag: tags[i],
            class: this.getTagClass()
          }
        }

        this.repository = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al buscar el repositorio");
        console.log(error);
        console.log(error.error);
      });

  },
  methods: {
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
    showModal(resource) {
      this.modalResource = resource;
      this.isActiveModal = true;
    }
  }
});