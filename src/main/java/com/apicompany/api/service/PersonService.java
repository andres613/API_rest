package com.apicompany.api.service;

import java.text.ParseException;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.entity.City;
import com.apicompany.api.entity.Person;
import com.apicompany.api.repository.PersonRepository;


/**
 * La anotación o estereotipo «@Service» indica a Spring que cree una instancia
 * de esta clase (bean) que se podrá usar en otras instancias
 * 
 * @author debian
 */
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

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

	public List<PersonDTO> findByName(String name) {
		return listEntityToDto(personRepository.findByName(name));
	}

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

	public void delete(int id) {
		personRepository.deleteById(id);
	}

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
	
	
	
	/*
	public List<PersonDTO> findPeople() {
		List<Tuple> lt = personRepository.peopleFind();
		lt.stream().map(tuple->{
			PersonDTO persondto = NativeResultProcessUtils.processResult(tuple, PersonDTO.class);
		})
		
		
		return persondto;
	}
	*/
}
