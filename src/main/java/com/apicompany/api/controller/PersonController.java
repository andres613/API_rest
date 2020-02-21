// Interfaz de servicios
package com.apicompany.api.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.service.PersonService;
/**
 * Para que Spring reconozca una clase como servicio web y la trate como tal, se debe anotar la clase con << @RestController >>.
 * Por otro lado, También se anota la clase con "@RequestMapping("/api/person")" ya que éste es el endpoint que contiene
 * los métodos que se desean exponer a los clientes.
 * Su acceso es mediante la url http://localhost:9898/api_rest
 * Es posible omitir ésta última anotación y hacerla individualmente en cada mètodo público que se desea exponer, por ejemplo,
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

@Validated
@RestController
@RequestMapping("/api/")
public class PersonController {
	/**
	 * Se le indica a Spring que debe inyectar una instancia de la clase de lógica de negocio
	 */
	@Autowired
	PersonService personService;
	/**
	 * Se crea un método REST mediante una petición HTTP POST en la url http://localhost:{puerto}/{nombre_aplicacion}/{path}.
	 * Fijarse en la anotación << @RequestBody >> antes de la variable «person». Esta anotación indica que la variable
	 * debe ser creada con los valores que lleguen en el cuerpo de la petición HTTP,
	 * para lo cual se usa una estructura JSON
	 * @throws Exception 
	 * @throws ParseException 
	 */
	// Se le indica el verbo que corresponde al método
	
	@PostMapping("/post")
	public void save(@Valid @RequestBody PersonDTO persondto) throws ParseException {
		persondto.setId(0);
		personService.save(persondto);	
	}
	
	@GetMapping
	public List<PersonDTO> getAll() {
		return personService.findAll();
	}
	
	@GetMapping("/id")
	public PersonDTO getById(@Valid @RequestParam int id) {
		return personService.findById(id);
	}
	
	@GetMapping("/document")
	public PersonDTO getByDocument(@Valid @RequestParam String document) {
		return personService.findByDocument(document);
	}
	
	@GetMapping("/name")
	public List<PersonDTO> getByName(@Valid @RequestParam String name) {
		return personService.findByName(name);
	}
	
	@PutMapping("/put")
	public void update(@Valid @RequestParam int id, @Valid @RequestBody PersonDTO persondto) throws ParseException {
		personService.update(id, persondto);
	}
	
	@DeleteMapping("/id")
	public void delete(@Valid @RequestParam int id) {
		personService.delete(id);
	}

}
