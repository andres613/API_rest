package com.apicompany.api.repositorydao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apicompany.api.entitydto.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	// Este método es agregado debido a que entre los compotamientos definidos por defecto no se encuentra
	public Person findByName(String name);
}
