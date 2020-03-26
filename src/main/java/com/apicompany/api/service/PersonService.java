package com.apicompany.api.service;

<<<<<<< HEAD
import java.sql.SQLException;
import java.sql.Timestamp;
=======
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
import java.text.ParseException;


import java.util.ArrayList;
<<<<<<< HEAD
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
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
import org.springframework.stereotype.Service;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.entity.City;
import com.apicompany.api.entity.Person;
import com.apicompany.api.repository.PersonRepository;
<<<<<<< HEAD
import com.apicompany.api.response.Response;
import com.apicompany.api.response.Time;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/* La anotación o estereotipo «@Service» indica a Spring que cree una instancia de esta clase (bean) que se podrá usar en otras instancias */
=======


/**
 * La anotación o estereotipo «@Service» indica a Spring que cree una instancia
 * de esta clase (bean) que se podrá usar en otras instancias
 * 
 * @author debian
 */
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
<<<<<<< HEAD
	
	String data = "";

	/* Éste método guarda y actualiza, la diferencia estriba en que para el caso de guardar, el parámetro "id" que es la llave primaria, al generarse
	 * automáticamente, llega nulo. Entre tanto, al actualizar, el campo "id" posee
	 * un valor y por ende se actualiza la entidad.*/
	
	/** @throws ParseException	
	 * @throws SQLException 
	 * @throws JsonProcessingException */
	
/////////
	
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
	
=======

	/**
	 * Éste método guarda y actualiza, la diferencia estriba en que para el caso de
	 * guardar, el parámetro "id" que es la llave prinaria, al generarse
	 * automáticamente, llega nulo. Entre tanto, al actualizar, el campo "id" posee
	 * un valor y por ende se actualiza la entidad.
	 * 
	 * @throws ParseException
	 */

	public void save(PersonDTO persondto) throws ParseException {
		Person person = dtoToEntity(persondto);
		personRepository.save(person);
	}

>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
	public List<PersonDTO> findAll() {
		//return listEntityToDto(personRepository.findAll());
		return personRepository.findAllDTO();
	}
	
<<<<<<< HEAD
	public PersonDTO findById (int id) {
=======
	public PersonDTO findById(int id) {
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
		return entityToDto(personRepository.findById(id).get());
	}
	
	public PersonDTO findByDocument(String document) {
		return entityToDto(personRepository.findByDocument(document));
	}

<<<<<<< HEAD
	public List<PersonDTO> searchByDocument(String document) {
		return personRepository.searchByDocument(document);
	}
	
	public List<PersonDTO> findByName(String name) {
		return listEntityToDto(personRepository.findByName(name));
	}
	
	public List<PersonDTO> searchByCity(String cityName) {
		return personRepository.searchByCity(cityName);
	}
	
	public List<PersonDTO> searchByCityAndDocument(String cityName, String document) {
		if(cityName == "") {
			return searchByDocument(document);
		} else {
			if(document == "") {
				return searchByCity(cityName);
			} else {
				return personRepository.findByDocumentAndCity(cityName, document);
			}
		}
	}

/////////
	
=======
	public List<PersonDTO> findByName(String name) {
		return listEntityToDto(personRepository.findByName(name));
	}

>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
	public void update(int id, PersonDTO persondto) {
		if (findById(id).getId() != 0) {// Busca que el campo con ese id no se encuentre vacío
			Person person = personRepository.findById(id).get();
			person.setId(id);
			person.setDocument(persondto.getDocument());
			person.setName(persondto.getName());
			person.setEmail(persondto.getEmail());
			person.setAge(persondto.getAge());
			personRepository.save(person);
		}
	}

<<<<<<< HEAD
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
=======
	public void delete(int id) {
		personRepository.deleteById(id);
	}

	//////////////////////////////////////////////////////////
	// dtoToEntity, entityToDto and listEntityToDto methods //
	//////////////////////////////////////////////////////////
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2

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
	
	
<<<<<<< HEAD
//////////////
// Response //
//////////////

	private ResponseEntity<Response> responseEnt (String opt, PersonDTO persondto) throws JsonMappingException, JsonProcessingException {
		switch (opt) {
		case "delete":
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jn = null;
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

	
=======
	
	/*
	public List<PersonDTO> findPeople() {
		List<Tuple> lt = personRepository.peopleFind();
		lt.stream().map(tuple->{
			PersonDTO persondto = NativeResultProcessUtils.processResult(tuple, PersonDTO.class);
		})
		
		
		return persondto;
	}
	*/
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
}
