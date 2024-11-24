package com.SheetalCity.SheetalCity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.entity.UserLogin;
import com.SheetalCity.SheetalCity.repositories.UserLoginRepository;

@Service
public class UserLoginService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	

	public void loginInsert(UserData userData) {
		UserLogin login = new UserLogin();
		login.setUsername(userData.getUsername());
		login.setPassword(userData.getPassword());
		userLoginRepository.save(login);
	}
	
	public String authenticate(UserLogin user) {
		userLoginRepository.findByUsername(user.getUsername());
		return user.getUsername();
	}
	public List<UserLogin> getAllLoginCred(){
		List<UserLogin> userList = userLoginRepository.findAll();
		System.out.println(userList.get(0).getUsername());
		return userList;
	}

}
