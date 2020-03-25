package com.apicompany.api.service;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicompany.api.dto.CityDTO;
import com.apicompany.api.entity.City;
import com.apicompany.api.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	/** @throws ParseException	*/

	public void save(CityDTO citydto) throws ParseException {
		City city = dtoToEntity(citydto);
		cityRepository.save(city);
	}

	public List<CityDTO> findAll() {
		return listEntityToDto(cityRepository.findAll());
	}
	
	public CityDTO findById(int id) {
		return entityToDto(cityRepository.findById(id).get());
	}
	
	public List<CityDTO> findByName(String name) {
		return listEntityToDto(cityRepository.findByName(name));
	}

	public void update(int id, CityDTO citydto) {
		if (findById(id).getId() != 0) {
			City city = cityRepository.findById(id).get();
			city.setId(id);
			city.setName(citydto.getName());
			cityRepository.save(city);
		}
	}

	public void delete(int id) {
		cityRepository.deleteById(id);
	}

	//////////////////////////////////////////////////////////
	// dtoToEntity, entityToDto and listEntityToDto methods //
	//////////////////////////////////////////////////////////

	private City dtoToEntity(CityDTO citydto) throws ParseException {
		if (citydto != null) {
			City entity = new City(0, null);
			entity.setId(citydto.getId());
			entity.setName(citydto.getName());
			
			return entity;
		} else {
			return null;
		}
	}

	private CityDTO entityToDto(City city) {
		if (city != null) {
			CityDTO dto = new CityDTO(0, null);
			dto.setId(city.getId());
			dto.setName(city.getName());
			return dto;
		} else {
			return null;
		}
	}

	private List<CityDTO> listEntityToDto(List<City> cities) {
		List<CityDTO> citydtos = new ArrayList<CityDTO>();
		for (City city : cities) {
			citydtos.add(entityToDto(city));
		}
		return citydtos;
	}
	
}