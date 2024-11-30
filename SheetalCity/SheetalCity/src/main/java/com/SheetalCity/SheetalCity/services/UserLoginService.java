package com.SheetalCity.SheetalCity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.entity.UserLogin;
import com.SheetalCity.SheetalCity.repositories.UserLoginRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserLoginService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	

	public void loginInsert(UserData userData) {
		UserLogin login = new UserLogin();
		login.setUsername(userData.getUsername());
		login.setPassword(userData.getPassword());
		login.setType("User");
		userLoginRepository.save(login);
	}
	
	public String authenticate(UserLogin user) {
		List<UserLogin> userLoginCred = userLoginRepository.findByUsername(user.getUsername());
		if(!userLoginCred.isEmpty()) {
			if(userLoginCred.get(0).getUsername().equals( user.getUsername()) && userLoginCred.get(0).getPassword().equals(user.getPassword())) {
				return user.getUsername();
			}else {
				return "";
			}
		}else {
			return "";
		}
		
	}
	public List<UserLogin> getAllLoginCred(){
		List<UserLogin> userList = userLoginRepository.findAll();
		System.out.println(userList.get(0).getUsername());
		return userList;
	}

	public String AddUserDetails(UserLogin user) {
		userLoginRepository.save(user);
		List<UserLogin> userLoginCred = userLoginRepository.findByUsername(user.getUsername());
		if(!userLoginCred.isEmpty()) {
			if(userLoginCred.get(0).getUsername().equals( user.getUsername()) && userLoginCred.get(0).getPassword().equals(user.getPassword())) {
				return user.getUsername();
			}else {
				return "";
			}
		}else {
			return "";
		}
	}

	public boolean deleteLogin(String username) {
		userLoginRepository.deleteByUsername(username);
		return !userLoginRepository.existsByUsername(username);
	}

}
