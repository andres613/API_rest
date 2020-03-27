package com.apicompany.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apicompany.api.entity.City;

public interface CityRepository extends JpaRepository <City, Integer> {
	
	public List<City> findByName(String name);
	
}