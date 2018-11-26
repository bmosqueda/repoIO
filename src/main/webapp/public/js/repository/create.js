const vue = new Vue({
  el: "#app",
  data: {
    name: '',
    description: '',
    url: '',
    tags: '',
    repoCategories: [],
    isModalVisible : false,
    resources: [],
    areas: [],
    authors: [],
    categories: [],
    resource: {
      title: '',
      description: '',
      size: 1,
      type: 1,
      url: '',
      areas: [],
      authors: []
    },
    onEdit: false,
    isSaving: false,
    errors: {
      resource: [],
      repo: []
    },
    validationRules: {
      name : "required|maxLength,250",
      description : "required|maxLength,250",
      size: "isNumber|required",
      type: "isNumber|required",
      url: 'required'
    },
    repoRules: {
      name : "required|maxLength,300",
      description : "required|maxLength,3000",
      url: "required|maxLength,2083",
      tags: "maxLength,300"
    }
  },
  created() {
    //Pedir las áreas
    window.axios.get('/repo.io/api/areas/all')
      .then(({data}) => {
        this.areas = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al buscar las áreas");
        console.log(error);
        console.log(error.error);
      });

    //Pedir las categorías
    window.axios.get('/repo.io/api/categories/all')
      .then(({data}) => {
        this.categories = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al buscar las áreas");
        console.log(error);
        console.log(error.error);
      });

    //Pedir los autores
    window.axios.get('/repo.io/api/authors/all')
      .then(({data}) => {
        this.authors = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al buscar las áreas");
        console.log(error);
        console.log(error.error);
      });
  },
  methods: {
    addResource() {
      try {
        console.log(this.resource)
        validator.validate(this.resource, this.validationRules);
      } catch(ex) {
        console.log(ex);
        alert(ex.message);
        // this.errors.resource = ex.message.split('\n');
        // location.href = location.href + '#divResourceError';
        return;
      }

      let tempRes = {
          title: this.resource.title,
          description: this.resource.description,
          size: this.resource.size,
          type: this.resource.type,
          url: this.resource.url,
          areas: this.resource.areas,
          visible: true
        };

      if(this.onEdit === false)
        this.resources.push(tempRes);
      else
        this.resources.splice(this.onEdit, 1, tempRes);

      this.resetResource();
      this.isModalVisible = !this.isModalVisible;
    },
    editResource(index) {
      this.resource = this.resources[index];
      this.onEdit = index;
      this.isModalVisible = true;
    },
    deleteResource(index) {
      this.onEdit = false;
      this.resources.splice(index, 1);
    },
    foldResource(res) {
      console.log(res.name);
      console.log(res.url);
      console.log(res.visible);
      res.visible = !res.visible;
      console.log(res.visible);
    },
    showAdd() {
      this.resetResource();
      this.isModalVisible = !this.isModalVisible; 
      this.onEdit = false;
    },
    resetResource() {
      this.resource.title = '';
      this.resource.description = '';
      this.resource.size = 1;
      this.resource.type = 1;
      this.resource.url = '';

      this.errors.resource = [];
    },
    pushAuthor() {

      /*if(!isNaN(this.resource.author))
        this.resource.authors.push({isNew: false, author_id: this.resource.author, name: ''});
      else 
        this.resource.authors.push({isNew: true, author_id: 0, name: this.resource.author});
      */
    },
    addRepository() {
      if(this.resources.length === 0)
        if(!(confirm('¿Quieres agregar un repositorio sin recursos?')))
          return;

      let repo = {
        name: this.name,
        description: this.description,
        url: this.url,
        tags: this.tags,
        categories: this.repoCategories,
        resources: this.resources
      }

      console.log(repo);

      try {
        validator.validate(repo, this.repoRules);
      } catch(ex) {
        console.log(ex);
        this.errors.repo = ex.message.split('\n');
        return;
      }
      this.isSaving = true;
      console.log("Guardando");
      window.axios.post('/repo.io/api/repositories/', repo)
        .then(({data}) => {
          this.isSaving = false;
          alert('Se creo correctamente');
          location.href = "/repo.io/repositorio/ver/?id="+data.repository_id;
          console.log(data);
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al guardar el repositorio");
          console.log(error);
          console.log(error.error);
        });
    },
    testResources() {
      let random = Math.round(Math.random() * 1000);

      let tempResource = {
        title: "Name "+random,
        description: "Laboris veniam ex sit id labore ex proident in consequat dolore aliquip culpa ad pariatur id labore sit nostrud aliqua amet labore dolor dolor nostrud adipisicing dolore proident est consequat magna labore amet culpa laborum.",
        size: random,
        type: Math.round(Math.random() * 3) + 1,
        url: "http://positivistic.com/soapbox/appalachian?a=coonily&b=isoseismical#hetty",
        visible: true,
        areas: this.areas
      };

      this.resources.push(tempResource);
    }
  }
});