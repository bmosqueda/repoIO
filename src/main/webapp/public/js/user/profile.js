const app = new Vue({
  el: "#app",
  data: {
    user: {
      user_id: 0,
      account_number: 0,
      name: "",
      email: "",
      password: "",
      role: 1,
      school_id: 2,
      school_name: "",
      image : "",
      repositories : []
    },
    isActiveModal: false,
    repository: {
      resources_count: 0,
      creator_id: 0,
      name:"ultramaximal Tanchelmian spindle",
      url: "http://enolate.com/interaxial/paradigmatize?a=ambo",
      description: "Lorem ipsum aliquip qui deserunt non irure fugiat occaecat aute nisi culpa exercitation veniam non veniam incididunt nisi commodo ex anim enim excepteur voluptate ad labore sint officia excepteur id dolor qui esse in mollit aute dolore ut eu amet ut minim quis veniam consequat in enim dolore pariatur consectetur aliquip deserunt duis anim consequat adipisicing nulla ut pariatur dolore laborum id do tempor consectetur dolor velit velit anim ullamco laborum magna voluptate amet eiusmod aliquip commodo dolore pariatur tempor consectetur est irure quis occaecat anim tempor excepteur in aliquip proident dolore officia reprehenderit in anim eu culpa consequat sint nisi cillum commodo quis officia adipisicing adipisicing deserunt adipisicing dolore irure reprehenderit duis reprehenderit reprehenderit ut ex magna sint nostrud amet ut aliquip labore aute excepteur officia fugiat excepteur anim dolor duis aliqua aute dolor veniam occaecat aliquip laborum ut est elit non in ad qui ea nulla aliquip eu qui nulla pariatur ad quis eiusmod incididunt cillum mollit nulla eiusmod pariatur anim tempor ea eu dolore ut aute mollit ut amet qui elit eu est nisi in proident.",
      creator_name:"Quintin Vanhoesen Rollin",
      tags: [],
      categories: []
    }
  },
  created() {
    window.axios.get('/repo.io/api/users/mine')
      .then(({data}) => {
        let repos = data.repositories;

        for (var i = repos.length - 1; i >= 0; i--) {
          if(repos[i].description.length > 100)
            repos[i].description = repos[i].description.substring(0, 100) + "...";
        }

        this.user = data;
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
    viewRepoDetails(repo_id) {
      window.axios.get('/repo.io/api/repositories/'+repo_id+'?withoutResources')
        .then(({data}) => {
          let tags = data.tags;

          for (var i = tags.length - 1; i >= 0; i--) {
            tags[i] = {
              tag: tags[i],
              class: this.getTagClass()
            }
          }

          this.repository = data;
          console.log(data);
          this.isActiveModal = true;

        })
        .catch(({response : {data : error}}) => {
          console.error("Hubo un problema al buscar el repositorio");
          console.log(error);
          console.log(error.error);
        });
    }
  }
});