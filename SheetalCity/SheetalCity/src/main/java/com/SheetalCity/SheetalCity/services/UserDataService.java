package com.SheetalCity.SheetalCity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.repositories.UserDataRepository;



@Service
public class UserDataService {
	@Autowired
	private UserDataRepository userDataRepository;
	
	public void userDataInsert() {
	UserData userData = new UserData();
	userData.setUsername("Abhinav");
	userData.setPassword("b");
	userData.setFirstName("Abhinav");
	userData.setLastName("Jain");
	userData.setEmail("AbhinavJain295@gmail.com");
	userDataRepository.save(userData);
	System.out.println(userData.getFirstName());
	}
}
