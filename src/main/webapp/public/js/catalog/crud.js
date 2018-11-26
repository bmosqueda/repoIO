const app = new Vue({
  el: "#app",
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

// For tabs
function openTab(evt, tabName) {var i, x, tablinks; x = document.getElementsByClassName("content-tab"); for (i = 0; i < x.length; i++) {x[i].style.display = "none"; } tablinks = document.getElementsByClassName("tab"); for (i = 0; i < x.length; i++) {tablinks[i].className = tablinks[i].className.replace(" is-active", ""); } document.getElementById(tabName).style.display = "block"; evt.currentTarget.className += " is-active";}