package com.SheetalCity.SheetalCity.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.dto.AdvancePaymentDTO;
import com.SheetalCity.SheetalCity.dto.AuthUserRequest;
import com.SheetalCity.SheetalCity.dto.UserDataRequest;
import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.repositories.UserDataRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserDataService {

	@Autowired
	private UserDataRepository userDataRepository;
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private HouseMappingService houseMappingService;
	
	public String userDataInsert(UserDataRequest userDataRequest) throws Exception {
	UserData userData = new UserData();
	userData.setUsername(userDataRequest.getUsername());
	userData.setPassword(userDataRequest.getPassword());
	userData.setFirstName(userDataRequest.getFirstName());
	userData.setLastName(userDataRequest.getLastName());
	userData.setEmail(userDataRequest.getEmail());
	userData.setMobNo(userDataRequest.getMobNo());
	userData.setActive(false);
	UserData userDataResponse = userDataRepository.save(userData);
	userLoginService.AddUserDetails(new AuthUserRequest(userDataResponse.getUsername(),userDataResponse.getPassword(),"USER"));
	return userData.getUsername();
	}
	
	public boolean userDataDelete(int id) {
		UserData user =userDataRepository.findById(id);
		boolean isLoginDeleted = false;
		boolean isHouseMappingDeleted = false;
		if(user!=null) {
			isLoginDeleted = userLoginService.deleteLogin(user.getUsername());
		}
		if(user!=null) {
			isHouseMappingDeleted = houseMappingService.deleteHouseMapping(id);
		}
		userDataRepository.deleteById(id);
		return !userDataRepository.existsById(id);
	}
	public List<UserData> userDataShow() {
		List<UserData> userList = userDataRepository.findAll();
		return userList;
	}
	public List<UserData> userDataUpdate(int id, UserData userData) {
		userData.setId(id);
		userData.setUpdated_dt(new Date());
		userDataRepository.save(userData);
		return getUserData(userData.getUsername());
	}
	public List<UserData> getUserData(String username) {
		return userDataRepository.findByUsername(username);
	}

	public UserData updateAdvancePayment(AdvancePaymentDTO advanePayment) {
		UserData user = userDataRepository.findById(advanePayment.getUserId());
		user.setAdvanceElectric(user.getAdvanceElectric()+advanePayment.getAdvanceElectricPayment());
		user.setAdvanceMaintenance(user.getAdvanceMaintenance()+advanePayment.getAdvanceMaintenancePayment());
		user.setUpdated_dt(new Date());
		userDataRepository.save(user);
		return user;
	}
}
