package com.SheetalCity.SheetalCity.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public String userDataInsert(UserData user) {
	userDataRepository.save(user);
	userLoginService.loginInsert(user);
	return user.getUsername();
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
}
