package com.apicompany.api.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.entity.City;
import com.apicompany.api.entity.Person;
import com.apicompany.api.repository.PersonRepository;
import com.apicompany.api.response.Response;
import com.apicompany.api.response.Time;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/* La anotación o estereotipo «@Service» indica a Spring que cree una instancia de esta clase (bean) que se podrá usar en otras instancias */
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	String data = "";
	
	/** @throws ParseException	
	 * @throws SQLException 
	 * @throws JsonProcessingException */
	
/////////
	/* Éste método guarda y actualiza, la diferencia estriba en que para el caso de guardar, el parámetro "id" que es la llave primaria, al generarse
	 * automáticamente, llega nulo. Entre tanto, al actualizar, el campo "id" posee
	 * un valor y por ende se actualiza la entidad.*/
	
	public ResponseEntity<Response> save(PersonDTO persondto) throws ParseException, SQLException, JsonProcessingException, ClassNotFoundException {
		ResponseEntity<Response> re = validator(persondto);
		if(re.getBody().getData() == null) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jn = null;			
			Person person = dtoToEntity(persondto);
			try {
				personRepository.save(person);
				jn = mapper.readTree("{\"value\":\"Registro creado con éxito\"}");
		    	return echoSave(Time.getTime(), jn, 201, "Created", persondto);
			} catch (Exception e) {
				jn = mapper.readTree("{\"value\":"+e.getLocalizedMessage()+"\"}");
				return echoSave(Time.getTime(), jn, 500, "Error", persondto);
			}
		} else {
			return re;
		}
	}
	
/////////

	public List<PersonDTO> findAll() {
		//return listEntityToDto(personRepository.findAll());
		return personRepository.findAllDTO();
	}
	
	public PersonDTO findById(int id) {
		return entityToDto(personRepository.findById(id).get());
	}
	
	public PersonDTO findByDocument(String document) {
		return entityToDto(personRepository.findByDocument(document));
	}
		
	public List<PersonDTO> findListByName(String name) {
		return listEntityToDto(personRepository.findByName(name));
	}
	
	public List<PersonDTO> findListByDocument(String document) {
		return personRepository.searchByDocument(document);
	}
	
	public List<PersonDTO> findListByCity(String cityName) {
		return personRepository.searchByCity(cityName);
	}
	
	public ResponseEntity<Response> searchByCityAndDocument(String cityName, String document) throws JsonMappingException, JsonProcessingException {
		PersonDTO persondto = new PersonDTO(0,document,"","",0,0,cityName);
		return responseEnt("search", persondto);
	}

	private List<PersonDTO> search(PersonDTO persondto) {
		String cityName = persondto.getCity();
		String document = persondto.getDocument();
		if (cityName == "") {
			return findListByDocument(document);
		} else {
			if (document == "") {
				return findListByCity(cityName);
			} else {
				return personRepository.findByDocumentAndCity(cityName, document);
			}
		}
	}
	
	
	
	
	
	
	
/////////
	
	public void update(int id, PersonDTO persondto) throws ParseException {
		if (findById(id).getId() != 0) {// Busca que el campo con ese id no se encuentre vacío
			persondto.setId(id);
			Person person = dtoToEntity(persondto);
			personRepository.save(person);
		}
	}

/////////
	
	public ResponseEntity<Response> delete(int id) throws JsonMappingException, JsonProcessingException {
		PersonDTO persondto = findById(id);
		try {
			personRepository.deleteById(id);
			return responseEnt("delete", persondto);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

//////////////////
	
//////////////////////////////////////////////////////////
// dtoToEntity, entityToDto and listEntityToDto methods //
//////////////////////////////////////////////////////////

	private Person dtoToEntity(PersonDTO persondto) throws ParseException {
		if (persondto != null) {
			Person entity = new Person(0, null, null, null, 0);
			entity.setId(persondto.getId());
			entity.setDocument(persondto.getDocument());
			entity.setName(persondto.getName());
			
			entity.setEmail(persondto.getEmail());
			entity.setAge(persondto.getAge());
			entity.setCity(new City());
			entity.getCity().setId(persondto.getCity_id());
			return entity;
		} else {
			return null;
		}
	}

	private PersonDTO entityToDto(Person person) {
		if (person != null) {
			PersonDTO dto = new PersonDTO(0, null, null, null, 0);
			dto.setId(person.getId());
			dto.setDocument(person.getDocument());
			dto.setName(person.getName());
			dto.setEmail(person.getEmail());
			dto.setAge(person.getAge());
			if(person.getCity()!=null) {
				dto.setCity_id(person.getCity().getId());
			}
			return dto;
		} else {
			return null;
		}
	}

	private List<PersonDTO> listEntityToDto(List<Person> people) {
		List<PersonDTO> persondtos = new ArrayList<PersonDTO>();
		for (Person person : people) {
			persondtos.add(entityToDto(person));
		}
		return persondtos;
	}
	
	
//////////////
// Response //
//////////////

	private ResponseEntity<Response> responseEnt (String opt, PersonDTO persondto) throws JsonMappingException, JsonProcessingException {
		JsonNode jn = null;
		ObjectMapper mapper = new ObjectMapper();
		switch (opt) {
		case "search":
			if (persondto.getCity() == "" && persondto.getDocument() == "") {
				List<PersonDTO> peopledto = findAll();
				if (peopledto.isEmpty()) {
					jn = mapper.readTree("{\"value\":\"Recurso no encontrado!!\"}");
					return echoSaveList (Time.getTime(), jn, 404, "Not Found", peopledto);
				} else {
					jn = mapper.readTree("{\"value\":\"Operación realizada satisfactoriamente!!\"}");
					return echoSaveList (Time.getTime(), jn, 200, "OK", peopledto);					
				}
			} else {
				List<PersonDTO> peopledto = search(persondto);
				if (peopledto.isEmpty()) {
					jn = mapper.readTree("{\"value\":\"Recurso no encontrado!!\"}");
					return echoSaveList (Time.getTime(), jn, 404, "Not Found", peopledto);
				} else {
					jn = mapper.readTree("{\"value\":\"Operación realizada satisfactoriamente!!\"}");
					return echoSaveList (Time.getTime(), jn, 200, "OK", peopledto);
				}
			}
			
		case "delete":
			jn = mapper.readTree("{\"value\":\"Registro con DNI "+persondto.getDocument()+" eliminado satisfactoriamente!!\"}");
			return echoSave(Time.getTime(), jn, 200, "OK", persondto);
		default:
			return null;
		}
	}
	
	
	
	private ResponseEntity<Response> echoSave (Timestamp timestamp, JsonNode data, int respondeCode, String status, PersonDTO persondto) {
		Response response;
		response = new Response(timestamp, data, respondeCode, status, persondto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private ResponseEntity<Response> echoSaveList (Timestamp timestamp, JsonNode data, int respondeCode, String status, List<PersonDTO> peopledto) {
		Response response;
		response = new Response(timestamp, data, respondeCode, status, peopledto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
/////////////////
// Validations //
/////////////////

	private ResponseEntity<Response> validator(PersonDTO persondto) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jn = null;
		if (findByDocument(persondto.getDocument()) != null) {
			jn = mapper.readTree(
					"{\"value\":\"El número de identificación ingresado, ya se encuentra en base de datos!!\"}");
			return echoSave(Time.getTime(), jn, 500, "Error", persondto);
		} else {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<PersonDTO>> constraintViolations = validator.validate(persondto);
			if (constraintViolations.size() > 0) {
				Map<String, String> map = new HashMap<String, String>();
				for (ConstraintViolation<PersonDTO> temp : constraintViolations) {
					map.put(temp.getPropertyPath() + "", temp.getMessage() + "");
				}
				jn = mapper.readTree(new ObjectMapper().writeValueAsString(map));
				return echoSave(Time.getTime(), jn, 409, "Conflict", persondto);
			} else {
				return echoSave(Time.getTime(), null, 200, "OK", persondto);
			}
		}
	}

	
}
