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
	
	public void userDataInsert() {
	UserData userData = new UserData();
	userData.setUsername("Abhinav");
	userData.setPassword("b");
	userData.setFirstName("Abhinav295");
	userData.setLastName("Jain");
	userData.setEmail("AbhinavJain295@gmail.com");
	userDataRepository.save(userData);
	System.out.println(userData.getFirstName());
	}
	
	public void userDataDelete(String username) {
		//Integer userId = Integer.parseInt(username);
		List<UserData> userIds = userDataRepository.findByUsername(username);
		//userDataRepository.deleteById(userId);
		userDataRepository.deleteAll(userIds);
	}
	public List<UserData> userDataShow(String username) {
		List<UserData> userD = userDataRepository.findByUsername(username);
		return userD;
	}
}
