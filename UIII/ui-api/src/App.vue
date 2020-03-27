<template lang = "pug">
  
  <div id = "app">
    
    <img src = "./assets/person-cityr.jpg">
    <h1>Personas por ciudad!!</h1>
    
    </br>

    <div for = 'inOperation'>

      <div>
        <input type = "button" value = "Crear"     v-on:click = 'create()' :disabled = 'isDisabled'/>
        <input type = "button" value = "Consultar" v-on:click = 'search()' :disabled = 'isDisabled'/>
      </div>

      </br>
      
      <div>

          <h3 v-if = 'this.operation == "search"' >Ingrese un número de documento para la búsqueda:</h3>
          <span v-show = 'this.operation == "create" || this.operation == "update" || this.operation == "search"'>
            <label> Identificación <input type = "text" id = "dni" placeholder = "Identificación" v-model = "dni"/> </label>
          </span>

          <span v-show = 'this.operation == "create" || this.operation == "update"'>
            <label> Nombre <input type = "text" id = "name"  placeholder = "Nombre"/> </label>
            <label> Email  <input type = "text" id = "email" placeholder = "E-mail"/> </label>
            <label> Edad   <input type = "text" id = "age"   placeholder = "Edad"/> </label>
          </span>

          <span v-show = 'this.operation == "create" || this.operation == "search" || this.operation == "update"'>
            <label> Ciudad
              <select id = "cities" v-model = "citieSelected" >
                <option value = "" selected = "selected" > {{ (this.operation == "search") ? "Todos" : "Select a city ..." }} </option>
                <option v-for = "c in respCity"> {{ c.name }} </option>
              </select>
            </label>
          </span>

          <h3 v-show = 'this.operation == "search"'> {{ (!dni.length) ? msgDni : answer }} </h3>

      </div>

    </div>

    <p id = "answer" v-show = 'this.operation == "create" || this.operation == "remove"'> {{ answer }} </p>
    <p>
      <input type = "button" v-bind:value = "seUp" v-show = 'this.operation == "create" || this.operation == "update"' @click = 'send()'/>
      <input type = "button" value = "Cancelar"    v-show = 'this.operation == "create" || this.operation == "search" || this.operation == "update"' v-on:click = 'cancel()'/>
    </p>


    <div>
      <table id = "personTable" border="2">
      <caption> Person by city </caption>
      <tr>
        <th>Id</th>
        <th>Document</th>
        <th>Name</th>
        <th>Email</th>
        <th>Age</th>
        <th>City</th>
        <th>City id</th>
        <th>Actualizar/Eliminar</th>
      </tr>
      <tr v-for = "p in respPerson" >
        <td> {{p.id}} </td>
        <td> {{p.document}} </td>
        <td> {{p.name}} </td>
        <td> {{p.email}} </td>
        <td> {{p.age}} </td>
        <td> {{p.city}} </td>
        <td> {{p.city_id}} </td>
        <td >
            <span >
                <input type = "button" value = "Actualizar" v-on:click = "update" :disabled = 'isDisabled'/>
                <input type = "button" value = "Eliminar"   v-on:click = "remove" :disabled = 'isDisabled'/>
            </span>
        </td>
      </tr>
      </table>
    </div>

  </div>



</template>

<script>

export default {

  name: 'app',
  
  data () {
    return {
      inOperation: false,
      registry: "",
      data: "",
      seUp: "Enviar",
      operation: "search",
      dni: "",
      citieSelected: '',
      indexCity: "",
      msgDni: "No se han ingresado criterios de búsqueda!!",
      respPerson: "",
      respCity: "",
      answer: "",
      urlPerson: "http://localhost:9898/api_rest/api/person/",
      urlCity:"http://localhost:9898/api_rest/api/city/"
    }
  },

  mounted() {
    this.clean ()
  },

  computed: {
  	isDisabled: function(){
    	return this.inOperation;
    }
  },

  watch: {
    
    dni (newVal, oldVal) {
      if(this.operation == "search") {
        fetch(this.urlPerson+`get?cityName=${document.getElementById("cities").value}&document=${this.dni}`)
          .then(res => { return res.text() })
          .catch(error => console.error('Error:', error))
          .then(response => {
            this.respPerson = JSON.parse(response).peopleDTO;
            if(this.respPerson.length == 0) {
              this.answer = JSON.parse(response).data
            } else {
              this.answer = ""
            }
          });
          //this.clean()
      }
    },

    citieSelected (newVal, oldVal) {
      if(this.operation == "search") {
        fetch(this.urlPerson+`get?cityName=${document.getElementById("cities").value}&document=${this.dni}`)
          .then(res => { return res.text() })
          .catch(error => console.error('Error:', error))
          .then(response => {
            this.respPerson = JSON.parse(response).peopleDTO;
            if(this.respPerson.length == 0) {
              this.answer = JSON.parse(response).data
            } else {
              this.answer = ""
            }
          });
          //this.clean()
      }
    }
    
  },

  methods: {

    cancel() {
      this.dni = "";
      this.operation = "cancel"
      this.inOperation = false
      this.answer = "";
      this.clean ()
    },
    
    clean() {
      this.loading (function(){
          document.getElementById("name").value = ""
          document.getElementById("email").value = ""
          document.getElementById("age").value = ""
          document.getElementById("cities").selectedIndex = 0
        })
    },
    
    loading ( clean ) {
      
      fetch(this.urlPerson+"document?document="+this.dni)
        .then (response => { return response.json() })
        .then (dataPerson => {
          this.respPerson = dataPerson
        })
      fetch(this.urlCity)
        .then(response => { return response.json() })
        .then(dataCity => {
          this.respCity = dataCity
          //console.log(JSON.stringify(this.respCity))
        })
      clean();
    },

    create() {
      this.inOperation = true
      this.seUp = "Enviar"
      this.operation = "create"
      this.dni = ""
      this.clean()
    },

    send() {
      this.indexCity = document.getElementById("cities").selectedIndex
      if(this.indexCity != 0) {
          this.data = `{"document":"${document.getElementById("dni").value}",
                        "name":"${document.getElementById("name").value}",
                        "email":"${document.getElementById("email").value}",
                        "age":"${document.getElementById("age").value}",
                        "city_id":"${this.respCity[document.getElementById("cities").selectedIndex-1].id}"}`
          return fetch(this.urlPerson+"post/", {
            method: 'POST', // or 'PUT'
            body: this.data, // data can be `string` or {object}!
            headers:{
              'Content-Type': 'application/json'
            }
          })
          .then(res => { return res.text() })
          .catch(error => console.error('Error:', error))
          .then(response => {
            this.answer = JSON.parse(response);
            this.answer = this.answer.data
            if(JSON.parse(response).responseCode < 300) {
              this.create()
            }
          });
      } else {
          this.answer = "Seleccione una ciudad"
      }
    },

    search() {
      this.inOperation = true
      this.operation = "search"
      //this.dni = "";this.clean ()
    },

    update(event) {
      this.inOperation = true
      this.seUp = "Actualizar"
      this.operation = "update"
      let registry = event.target.parentNode.parentNode.parentNode.getElementsByTagName('td')
      let i = this.respCity.findIndex (ciudad => ciudad.name === registry[5].innerHTML.trim())
      this.getRegistry (registry, i, function(registry, i) {
          document.getElementById("dni").value = registry[1].textContent
          document.getElementById("name").value = registry[2].textContent
          document.getElementById("email").value = registry[3].textContent
          document.getElementById("age").value = registry[4].textContent
          document.getElementById("cities").selectedIndex = i
        })
    },

    getRegistry (registry, i, saveUpdate) {
      this.answer = "";
      this.dni = "";
      saveUpdate(registry, i);
    },
    
    remove() {
      if (confirm(`¿Seguro que desea eliminar a ${event.target.parentNode.parentNode.parentNode.getElementsByTagName('td')[1].textContent}?`)) {
        fetch (`${this.urlPerson}id?id=${event.target.parentNode.parentNode.parentNode.getElementsByTagName('td')[0].textContent}`, {
          method: 'DELETE'
          })
          .then(res => { return res.text() })
          .catch(error => console.error('Error:', error))
          .then(response => {
            this.answer = JSON.parse(response);
            this.answer = this.answer.data.value
            this.operation = "remove"
            this.dni = "";
            this.clean ()
          });
      } 
    }

  }
  
}

</script>
    
<style lang="scss">

html {
  padding: 50px;
  color: #3d3d3d;
}
  
.red {
  color: tomato;
}

#answer {
  color: red;
}

#personTable tr:hover {
  background-color: cyan;
}
    
</style>