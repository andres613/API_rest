package com.apicompany.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicompany.api.entitydto.Person;
import com.apicompany.api.repositorydao.PersonRepository;
/**
 * La anotación o estereotipo «@Service» indica a Spring que cree una instancia de esta clase (bean)
 * que se podrá usar en otras instancias
 * @author debian
 */
@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	/**
	 * Éste método guarda y actualiza, la diferencia estriba en que para el caso de guardar, el parámetro "id" que es la
	 * llave prinaria, al generarse automáticamente, llega nulo. Entre tanto, al actualizar, el campo "id" posee un valor y
	 * por ende se actualiza la entidad. 
	 */
	public Person save(Person person) {
		return personRepository.save(person);
	}
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public Person findById(Long id) {
		return personRepository.findById(id).get();
	}
	
	public Person findByName(String name) {
		return personRepository.findByName(name);
	}
	
	public void delete(Long id) {
		personRepository.deleteById(id);
	}
	
}
