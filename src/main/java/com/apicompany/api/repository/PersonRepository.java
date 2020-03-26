package com.apicompany.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apicompany.api.dto.PersonDTO;
import com.apicompany.api.entity.Person;
@Repository
public interface PersonRepository extends JpaRepository <Person, Integer>{
	
<<<<<<< HEAD
// Estos métodos son agregados debido a que éstos no se encuentran definidos por defecto

	public Person findByDocument(String document);
	
	@Query ( "SELECT new com.apicompany.api.dto.PersonDTO (p.id, p.document, p.name, p.email, p.age, c.id, c.name) FROM Person p INNER JOIN City c ON p.city.id = c.id WHERE p.document LIKE %?1%" )
	List<PersonDTO> searchByDocument(String document);
	
	public List<Person> findByName(String personName);
=======
	// Este método es agregado debido a que entre los compotamientos definidos por defecto no se encuentra
	public List<Person> findByName(String name);
	
	public Person findByDocument(String document);
	
	
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
	
	@Query("SELECT new com.apicompany.api.dto.PersonDTO(p.id, p.document, p.name, p.email, p.age, c.id, c.name) FROM Person p INNER JOIN City c ON p.city.id = c.id")
    List<PersonDTO> findAllDTO();
	
<<<<<<< HEAD
	@Query ( "SELECT new com.apicompany.api.dto.PersonDTO (p.id, p.document, p.name, p.email, p.age, c.id, c.name) FROM Person p INNER JOIN City c ON p.city.id = c.id WHERE c.name LIKE %?1%" )
	List<PersonDTO> searchByCity(String cityName);
	
	@Query ( "SELECT new com.apicompany.api.dto.PersonDTO (p.id, p.document, p.name, p.email, p.age, c.id, c.name) FROM Person p INNER JOIN City c ON p.city.id = c.id WHERE c.name LIKE %?1% AND p.document LIKE %?2%" )
    List<PersonDTO> findByDocumentAndCity(String cityName, String document);
=======
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
}
