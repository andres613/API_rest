// Interfaz de servicios
package com.apicompany.api.controller;


import java.sql.SQLException;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.response.Response;
import com.apicompany.api.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Validated
@RestController //Para que Spring reconozca una clase como servicio web y la trate como tal Su acceso es mediante la url http://localhost:9898/api_rest
@RequestMapping("/api/person/")//Éste es el endpoint que contiene los métodos que se desean exponer a los clientes.
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {
	// Se le indica a Spring que debe inyectar una instancia de la clase de lógica de negocio
	@Autowired
	PersonService personService;
	
	Response result;
	
	/** @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws JsonProcessingException 
	 * @throws Exception
	 * */	

/** Es posible omitir ésta última anotación y hacerla individualmente en cada mètodo público que se desea exponer, por ejemplo,
 * con << @RequestMapping(value="/obtener", method=RequestMethod.GET) >> y así incluyendo la anotación con el verbo correspondiente
 * La anotación << @RequestMapping >> recibe parámetros que ayudan a cambiar la definición del método hacia el exterior:
 * los content type que recibe o entrega, los headers que requiere, etc. En este caso los básicos son value, para indicar
 * la url en la cual "escuchará" este método y method que indica el método HTTP que iniciará la ejecución de esta operación.
 * Por defecto es GET, así que en este caso se podría no dejar sólo el parámetro value y el servicio funcionaría igual.
 * Al ejecutar la aplicación y dirige el navegador hacia http://localhost:9898/api_rest (si no se han cambiado
 * las configuraciones por defecto) y se verá la respuesta de la petición
 * En el archivo << application.properties >> se han definido otras variables de acceso, como lo son el nombre de la api (api_rest)
 * asi como el path de acceso; ésto con el objeto de especificar y focalizar el acceso la api específica
 */
	
	
	/* Se crea un método REST mediante una petición HTTP POST en la url http://localhost:{puerto}/{nombre_aplicacion}/{path}.
	 * Fijarse en la anotación @RequestBody antes de la variable «person». Esta anotación indica que la variable debe ser creada con los valores
	 * que lleguen en el cuerpo de la petición HTTP, para lo cual se usa una estructura JSON */
	// Se le indica el verbo que corresponde al método
	
/////////
	
	@PostMapping("/post")
	public ResponseEntity<Response> save(@RequestBody PersonDTO persondto) throws ParseException, SQLException, JsonProcessingException, ClassNotFoundException {
		persondto.setId(0);
		return personService.save(persondto);
	}
	
///////////

	@GetMapping
	public List<PersonDTO> getAll() {
		return personService.findAll();
	}

	@GetMapping("/city")
	public List<PersonDTO> finfByCity(@Valid @RequestParam String cityName) {
		return personService.findListByCity(cityName);
	}
	
	@GetMapping("/document")
	public List<PersonDTO> findByDocument(@Valid @RequestParam String document) {
		System.out.println("inicia");
		return personService.findListByDocument(document);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Response> findByCityAndDocument(@Valid @RequestParam String cityName, @Valid @RequestParam String document) throws JsonMappingException, JsonProcessingException {
		return personService.searchByCityAndDocument(cityName, document);
	}
	
	@GetMapping("/id")
	public PersonDTO findById(@Valid @RequestParam int id) {
		return personService.findById(id);
	}
	
	@GetMapping("/name")
	public List<PersonDTO> findByName(@Valid @RequestParam String name) {
		return personService.findListByName(name);
	}
	
/////////
	
	@PutMapping("/put")
	public void update(@Valid @RequestParam int id, @Valid @RequestBody PersonDTO persondto) throws ParseException {
		personService.update(id, persondto);
	}

/////////
	
	@DeleteMapping("/id")
	public ResponseEntity<Response> delete(@Valid @RequestParam int id) throws JsonMappingException, JsonProcessingException {
		return personService.delete(id);
	}
	
}
