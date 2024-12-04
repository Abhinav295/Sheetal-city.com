package com.SheetalCity.SheetalCity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.dto.HouseMappingDTO;
import com.SheetalCity.SheetalCity.entity.HouseMapping;
import com.SheetalCity.SheetalCity.repositories.HouseMappingRepository;

import jakarta.transaction.Transactional;

@Service
public class HouseMappingService {
	
	@Autowired
	HouseMappingRepository houseMappingRepository;
	
	public int registerHouse(HouseMappingDTO hM) {
		HouseMapping newHM = new HouseMapping();
		newHM.setBlock(hM.getBlock());
		newHM.setHouseNo(hM.getHouseNo());
		newHM.setCityDetails(hM.getCityDetails().get(0));
		newHM.setUserDetails(hM.getUserDetails().get(0));
		newHM.setDueElectric(hM.getDueElectric());
		newHM.setDueMaintenance(hM.getDueMaintenance());
		houseMappingRepository.save(newHM);
		int houseNo =houseMappingRepository.findByHouseMapping(newHM.getHouseNo(),newHM.getBlock(),newHM.getCityDetails());;
		return houseNo;
	}

	public boolean deleteHouseMapping(int id) {
		houseMappingRepository.deleteHouseMappingsByUserId(id);
		return true;
	}
	
	public List<HouseMapping> getAllHouseRegisteration (){
		return houseMappingRepository.findAll();
	}
	
}
