package com.apicompany.api.repository;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
import org.springframework.data.jpa.repository.JpaRepository;

import com.apicompany.api.entity.City;

public interface CityRepository extends JpaRepository <City, Integer> {
<<<<<<< HEAD
	
	public List<City> findByName(String name);
	
=======
 
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
}