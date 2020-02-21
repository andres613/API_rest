package com.apicompany.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apicompany.api.entity.Person;
import com.apicompany.api.repository.CityRepository;

	@RestController
	@RequestMapping("/api/nested")
	public class NestedController {
	    @Autowired
	    CityRepository cityrepo;
	 
	    @RequestMapping("/list")
	    public List<Person> personlist(){
	        List personlist = cityrepo.findAll();
	        return personlist;
	    }
	    
	    /**
	     * La anotación "@RestController", además de indicar que la clase es un controlador,
	     * marca a todos los métodos con la anotación "@ResponseBody".
	     * Se podría poner "@Controller" y el el método lista añadir una anotación "@ResponseBody".
	     * En "@Requestmapping" se indica que el directorio al que lleva el controlador será “nested”.
	     * El método personlist se ejecuta al acceder a la ubicación nested/list y devuelve una lista de objetos
	     * City mapeados en JSON. Esta lista se obtiene a partir de una instancia de un objeto tipo 
	     * CityRepository "@Autowired" que creará Spring.
	     */
	}