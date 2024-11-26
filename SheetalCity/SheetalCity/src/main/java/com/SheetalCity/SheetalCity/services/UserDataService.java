package com.SheetalCity.SheetalCity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.repositories.UserDataRepository;


@Service
public class UserDataService {

	@Autowired
	private UserDataRepository userDataRepository;
	
	@Autowired
	private UserLoginService userLoginService;
	
	public String userDataInsert(UserData user) {
	userDataRepository.save(user);
	userLoginService.loginInsert(user);
	return user.getUsername();
	}
	
	public boolean userDataDelete(int id) {
		userDataRepository.deleteById(id);
		return !userDataRepository.existsById(id);
	}
	public List<UserData> userDataShow() {
		List<UserData> userList = userDataRepository.findAll();
		return userList;
	}
	public List<UserData> userDataUpdate(int id, UserData userData) {
		userData.setId(id);
		userDataRepository.save(userData);
		return getUserData(userData.getUsername());
	}
	public List<UserData> getUserData(String username) {
		return userDataRepository.findByUsername(username);
	}
}
