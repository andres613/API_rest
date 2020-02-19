package com.apicompany.api.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.entity.Person;
import com.apicompany.api.repository.PersonRepository;
/**
 * La anotación o estereotipo «@Service» indica a Spring que cree una instancia de esta clase (bean)
 * que se podrá usar en otras instancias
 * @author debian
 */
@Service
public class PersonService {
	
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private PersonRepository personRepository;
	/**
	 * Éste método guarda y actualiza, la diferencia estriba en que para el caso de guardar, el parámetro "id" que es la
	 * llave prinaria, al generarse automáticamente, llega nulo. Entre tanto, al actualizar, el campo "id" posee un valor y
	 * por ende se actualiza la entidad. 
	 * @throws ParseException 
	 */
	
	public void save(PersonDTO persondto) throws ParseException {
		Person person = dtoToEntity(persondto);
		personRepository.save(person);
	}
	
	public List<PersonDTO> findAll(){
		return listEntityToDto(personRepository.findAll());
	}
	
	public PersonDTO findById(String id) {
		return entityToDto(personRepository.findById(id).get());
	}
	
	public PersonDTO findByPersonid(String document) {
		return entityToDto(personRepository.findByDocument(document));
	}
	
	public List<PersonDTO> findByName(String name) {
		return listEntityToDto(personRepository.findByName(name));
	}
	
	public void delete(String id) {
		personRepository.deleteById(id);
	}
	
	
	private Person dtoToEntity(PersonDTO persondto) throws ParseException {
		return modelMapper.map(persondto, Person.class);
	}
	
	private PersonDTO entityToDto (Person person) {
		if (person != null) {
			PersonDTO dto = new PersonDTO("", null, null, null, 0);
			dto.setId(person.getId());
			dto.setDocument(person.getDocument());
			dto.setName(person.getName());
			dto.setEmail(person.getEmail());
			dto.setAge(person.getAge());
			return dto;
		} else {
			return null;
		}
	}
	
	private List<PersonDTO> listEntityToDto (List<Person> people) {
		List<PersonDTO> persondtos = new ArrayList<PersonDTO>();
		for (Person person : people) {
			persondtos.add(entityToDto(person));
		}
		return persondtos;
	}
	
}
