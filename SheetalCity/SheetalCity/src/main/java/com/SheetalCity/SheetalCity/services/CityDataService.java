package com.SheetalCity.SheetalCity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.entity.CityDetails;
import com.SheetalCity.SheetalCity.repositories.CityDataRepository;

@Service
public class CityDataService {
	
	@Autowired
	private CityDataRepository cityDataRepository;
	
	public String addNewCity(CityDetails newCity) {
		List<CityDetails> city;
		try {
			cityDataRepository.save(newCity);
			city =  cityDataRepository.findByCityName(newCity.getCityName());
		}catch(Exception e) {
			e.printStackTrace();
			return "";
		}
		return city.get(0).getCityName();
	}

	public List<CityDetails> getCitiesDetails(){
		return cityDataRepository.findAll();
	}
	
	public CityDetails UpdateCityDetails(CityDetails city) {
		cityDataRepository.save(city);
		return cityDataRepository.findById(city.getId());
	}
}
