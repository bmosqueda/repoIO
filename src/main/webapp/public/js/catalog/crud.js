const appArea = new Vue({
  el: "#tabArea",
  data: {
    areas: [],
    newArea: '',
    onEditArea: -1,
    errors: {
      area: ''
    }
  },
  created(){
    //Pedir las áreas
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
    addArea() {
      let name = this.newArea.trim();

      if(name === '') {
        this.errors.area = 'Por favor ingresa un nombre';
        return;
      }

      if(this.onEditArea == -1){
        window.axios.post('/repo.io/api/areas', {name: this.newArea.trim()})
          .then(({data}) => {
            this.areas.push(data);
          })
          .catch(({response : {data : error}}) => {
            console.error("Hubo un problema al crear la área");
            console.log(error.error);
          });
      }
      else
        this.editArea();
      this.newArea = '';
    },
    editArea() {
      let name = this.newArea.trim();

      if(name === '') {
        this.errors.area = 'Por favor ingresa un nombre';
        return;
      }

      window.axios.put('/repo.io/api/areas/'+this.areas[this.onEditArea].area_id, {name: name})
        .then(({data}) => {
          this.areas[this.onEditArea].name = name;
          this.newArea = '';
          this.onEditArea = -1;
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al crear la área");
          console.log(error.error);
        });

    },
    deleteArea(index) {
      window.axios.delete('/repo.io/api/areas/'+this.areas[index].area_id)
        .then(({data}) => {
          this.areas.splice(index, 1);
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al eliminar el área");
          console.log(error.error);
          alert(error.error);
        });
    },
    toggleEditArea(index) {
      this.onEditArea = index;
      this.newArea = this.areas[index].name;
    },
    existsArea(name) {
      name = name.toLowerCase().trim();
      return this.areas.findIndex(area => area.name.toLowerCase() === name) != -1;
    }
  }
});
 
const appCategory = new Vue({
  el: "#tabCategory",
  data: {
    categories: [],
    newCategory: '',
    onEditCategory: -1,
    errors: {
      category: ''
    }
  },
  created(){
    //Pedir las áreas
    window.axios.get('/repo.io/api/categories/all')
      .then(({data}) => {
        this.categories = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al obtener las categorías");
        console.log(error.error);
      });
  },
  methods: {
    addCategory() {
      let name = this.newCategory.trim();

      if(name === '') {
        this.errors.category = 'Por favor ingresa un nombre';
        return;
      }

      if(this.onEditCategory == -1){
        window.axios.post('/repo.io/api/categories', {name: this.newCategory.trim()})
          .then(({data}) => {
            this.categories.push(data);
          })
          .catch(({response : {data : error}}) => {
            console.error("Hubo un problema al crear la área");
            console.log(error.error);
          });
      }
      else
        this.editCategory();
      this.newCategory = '';
    },
    editCategory() {
      let name = this.newCategory.trim();

      if(name === '') {
        this.errors.category = 'Por favor ingresa un nombre';
        return;
      }

      window.axios.put('/repo.io/api/categories/'+this.categories[this.onEditCategory].category_id, {name: name})
        .then(({data}) => {
          this.categories[this.onEditCategory].name = name;
          this.newCategory = '';
          this.onEditCategory = -1;
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al crear la área");
          console.log(error.error);
        });

    },
    deleteCategory(index) {
      window.axios.delete('/repo.io/api/categories/'+this.categories[index].category_id)
        .then(({data}) => {
          this.categories.splice(index, 1);
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al eliminar el área");
          console.log(error.error);
          alert(error.error);
        });
    },
    toggleEditCategory(index) {
      this.onEditCategory = index;
      this.newCategory = this.categories[index].name;
    },
    existsCategory(name) {
      name = name.toLowerCase().trim();
      return this.categories.findIndex(category => category.name.toLowerCase() === name) != -1;
    }
  }
});

const appSchool = new Vue({
  el: "#tabSchool",
  data: {
    schools: [],
    newSchool: '',
    onEditSchool: -1,
    errors: {
      school: ''
    }
  },
  created(){
    //Pedir las áreas
    window.axios.get('/repo.io/api/schools/all')
      .then(({data}) => {
        this.schools = data;
      })
      .catch(({response : {data : error}}) => {
        console.error("Hubo un problema al obtener las categorías");
        console.log(error.error);
      });
  },
  methods: {
    addSchool() {
      let name = this.newSchool.trim();

      if(name === '') {
        this.errors.School = 'Por favor ingresa un nombre';
        return;
      }

      if(this.onEditSchool == -1){
        window.axios.post('/repo.io/api/schools', {name: this.newSchool.trim()})
          .then(({data}) => {
            this.schools.push(data);
          })
          .catch(({response : {data : error}}) => {
            console.error("Hubo un problema al crear la área");
            console.log(error.error);
          });
      }
      else
        this.editSchool();
      this.newSchool = '';
    },
    editSchool() {
      let name = this.newSchool.trim();

      if(name === '') {
        this.errors.School = 'Por favor ingresa un nombre';
        return;
      }

      window.axios.put('/repo.io/api/schools/'+this.schools[this.onEditSchool].school_id, {name: name})
        .then(({data}) => {
          this.schools[this.onEditSchool].name = name;
          this.newSchool = '';
          this.onEditSchool = -1;
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al crear la área");
          console.log(error.error);
        });

    },
    deleteSchool(index) {
      window.axios.delete('/repo.io/api/schools/'+this.schools[index].school_id)
        .then(({data}) => {
          this.schools.splice(index, 1);
        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al eliminar el área");
          console.log(error.error);
          alert(error.error);
        });
    },
    toggleEditSchool(index) {
      this.onEditSchool = index;
      this.newSchool = this.schools[index].name;
    },
    existsSchool(name) {
      name = name.toLowerCase().trim();
      return this.schools.findIndex(School => School.name.toLowerCase() === name) != -1;
    }
  }
});
// For tabs
function openTab(evt, tabName) {var i, x, tablinks; x = document.getElementsByClassName("content-tab"); for (i = 0; i < x.length; i++) {x[i].style.display = "none"; } tablinks = document.getElementsByClassName("tab"); for (i = 0; i < x.length; i++) {tablinks[i].className = tablinks[i].className.replace(" is-active", ""); } document.getElementById(tabName).style.display = "block"; evt.currentTarget.className += " is-active";}