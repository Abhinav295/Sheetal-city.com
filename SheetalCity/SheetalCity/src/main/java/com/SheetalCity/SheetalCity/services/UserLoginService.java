package com.SheetalCity.SheetalCity.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.dto.AuthUserRequest;
import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.entity.UserLogin;
import com.SheetalCity.SheetalCity.repositories.UserLoginRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserLoginService {

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	

	public void loginInsert(UserData userData) {
		UserLogin login = new UserLogin();
		login.setUsername(userData.getUsername());
		login.setPassword(userData.getPassword());
		login.setType("User");
		userLoginRepository.save(login);
	}
	
	public Optional<UserLogin> authenticate(String username) {
		 return userLoginRepository.findByUsername(username);
	}
	
	public boolean checkPassword(UserLogin u, String raw) {
		return encoder.matches(raw, u.getPassword());
	}
	
	public List<UserLogin> getAllLoginCred(){
		List<UserLogin> userList = userLoginRepository.findAll();
		System.out.println(userList.get(0).getUsername());
		return userList;
	}

	public String AddUserDetails(AuthUserRequest user) throws SQLIntegrityConstraintViolationException, SQLException {
		
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(user.getUsername());
		userLogin.setPassword(encoder.encode(user.getPassword()));
		userLogin.setType(user.getUserType());
		UserLogin response =  userLoginRepository.save(userLogin);
		System.out.println(user.getUserType());
		return response.getUsername();
	}

	public boolean deleteLogin(String username) {
		userLoginRepository.deleteByUsername(username);
		return !userLoginRepository.existsByUsername(username);
	}

}
