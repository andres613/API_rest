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
@RestController
@RequestMapping("/api/person/")
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
		return personService.searchByCity(cityName);
	}
	
	@GetMapping("/document")
	public List<PersonDTO> findByDocument(@Valid @RequestParam String document) {
		return personService.searchByDocument(document);
	}

	@GetMapping("/get")
	public List<PersonDTO> findByCityAndDocument(@Valid @RequestParam String cityName, @Valid @RequestParam String document) {
		return personService.searchByCityAndDocument(cityName, document);
	}
	
	@GetMapping("/id")
	public PersonDTO getById(@Valid @RequestParam int id) {
		return personService.findById(id);
	}
	
	@GetMapping("/name")
	public List<PersonDTO> getByName(@Valid @RequestParam String name) {
		return personService.findByName(name);
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
