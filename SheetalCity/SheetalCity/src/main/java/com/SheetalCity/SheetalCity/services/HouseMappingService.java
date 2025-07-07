package com.SheetalCity.SheetalCity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.dto.HouseMappingDTO;
import com.SheetalCity.SheetalCity.entity.HouseMapping;
import com.SheetalCity.SheetalCity.entity.LedgerDetails;
import com.SheetalCity.SheetalCity.repositories.HouseMappingRepository;
import com.SheetalCity.SheetalCity.repositories.LedgerDetailsRepositories;

import jakarta.transaction.Transactional;

@Service
public class HouseMappingService {
	
	@Autowired
	HouseMappingRepository houseMappingRepository;
	
	@Autowired
	LedgerDetailsRepositories ledgerDetailsRepositories;
	
	public int registerHouse(HouseMappingDTO hM) {
		HouseMapping newHM = new HouseMapping();
		newHM.setBlock(hM.getBlock());
		newHM.setHouseNo(hM.getHouseNo());
		newHM.setCityDetails(hM.getCityDetails().get(0));
		newHM.setUserDetails(hM.getUserDetails().get(0));
		newHM.setDueElectric(hM.getDueElectric());
		newHM.setDueMaintenance(hM.getDueMaintenance());
		int houseNo=0;
		try {
			houseMappingRepository.save(newHM);
			houseNo =houseMappingRepository.findByHouseMapping(newHM.getHouseNo(),newHM.getBlock(),newHM.getCityDetails());
			if(newHM.getDueElectric()!=0 || newHM.getDueMaintenance()!=0) {
				generateBillManuallyForSpecificUser(newHM);
			}
		}catch(Exception e) {
			System.out.println(e);
			System.out.println(houseNo);
			return houseNo;
		}
		return houseNo;
	}

	public boolean deleteHouseMapping(int id) {
		houseMappingRepository.deleteHouseMappingsByUserId(id);
		return true;
	}
	
	public List<HouseMapping> getAllHouseRegisteration (){
		return houseMappingRepository.findAll();
	}

	public List<HouseMapping> getHouseRegisteration(int id) {
		return houseMappingRepository.findByUserId(id);
	}
	
	public void generateBillManuallyForSpecificUser(HouseMapping hm) {
		LedgerDetails ld = new LedgerDetails();
		ld.setElectricityBill(hm.getDueElectric());
		ld.setMaintenanceBill(hm.getDueMaintenance());
		ld.setUser(hm.getUserDetails());
		ld.setHousemapping(hm);
		ld.setTotalBill(hm.getDueElectric()+hm.getDueMaintenance());
		ld.setPaymentStatus("DUE");
		ld.setSlipNo("Not Generated");
		ledgerDetailsRepositories.save(ld);
	}
	
}
