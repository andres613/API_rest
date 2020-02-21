package com.apicompany.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apicompany.api.entity.City;

public interface CityRepository extends JpaRepository <City, Integer> {
 
}