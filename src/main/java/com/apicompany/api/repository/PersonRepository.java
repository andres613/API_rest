package com.apicompany.api.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apicompany.api.entity.Person;
@Repository
public interface PersonRepository extends JpaRepository <Person, Integer>{
	
	// Este método es agregado debido a que entre los compotamientos definidos por defecto no se encuentra
	public List<Person> findByName(String name);
	
	public Person findByDocument(String document);
	
}
