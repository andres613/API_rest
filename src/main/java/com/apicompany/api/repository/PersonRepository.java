package com.apicompany.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.entity.Person;
@Repository
public interface PersonRepository extends JpaRepository <Person, Integer>{
	
	// Este método es agregado debido a que entre los compotamientos definidos por defecto no se encuentra
	public List<Person> findByName(String name);
	
	public Person findByDocument(String document);
	
	
	
	@Query("SELECT new com.apicompany.api.dto.PersonDTO(p.id, p.document, p.name, p.email, p.age, c.id, c.name) FROM Person p INNER JOIN City c ON p.city.id = c.id")
    List<PersonDTO> findAllDTO();
	
}
