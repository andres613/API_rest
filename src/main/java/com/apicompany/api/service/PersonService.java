package com.apicompany.api.service;

import java.text.ParseException;
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
		//System.out.println("{id:"+person.getPersonId()+", name:"+person.getName()+", email:"+person.getEmail()+", age:"+person.getAge()+"}");
		personRepository.save(person);
	}
	
	public PersonDTO findByPersonid(String personid) {
		return entityToDto(personRepository.findByPersonid(personid));
	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	/*
	public Person findByName(String name) {
		return personRepository.findByName(name);
	}
	
	public void delete(Long id) {
		personRepository.deleteById(id);
	}
	*/
	private Person dtoToEntity(PersonDTO persondto) throws ParseException {
		return modelMapper.map(persondto, Person.class);
	}
	
	private PersonDTO entityToDto (Person person) {
		return modelMapper.map(person, PersonDTO.class);
	}
	
	
}
