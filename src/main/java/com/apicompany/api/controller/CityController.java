package com.apicompany.api.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apicompany.api.dto.CityDTO;
import com.apicompany.api.service.CityService;

@Validated
@RestController
@RequestMapping("/api/city/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	/** @throws Exception * @throws ParseException */
	@PostMapping("/post")
	public void save(@Valid @RequestBody CityDTO citydto) throws ParseException {
		citydto.setId(0);
		cityService.save(citydto);	
	}
	
	@GetMapping
	public List<CityDTO> getAll() {
		return cityService.findAll();
	}

	@GetMapping("/id")
	public CityDTO getById(@Valid @RequestParam int id) {
		return cityService.findById(id);
	}
	
	@GetMapping("/name")
	public List<CityDTO> getByName(@Valid @RequestParam String name) {
		return cityService.findByName(name);
	}

	@PutMapping("/put")
	public void update(@Valid @RequestParam int id, @Valid @RequestBody CityDTO citydto) throws ParseException {
		cityService.update(id, citydto);
	}

	@DeleteMapping("/id")
	public void delete(@Valid @RequestParam int id) {
		cityService.delete(id);
	}

}