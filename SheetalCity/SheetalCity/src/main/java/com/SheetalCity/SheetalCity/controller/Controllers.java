package com.SheetalCity.SheetalCity.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.services.UserDataService;

@RestController
@RequestMapping("sheetal.city")
public class Controllers {

	@Autowired
	UserDataService user;
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody UserData userData) {
		user.userDataInsert(userData);
		System.out.println("-------------------Inside the insert block----------------------");
		return "index.html";
	}
	
	@GetMapping("/getUser/{username}")
	public List<UserData> getUser(@PathVariable("username") String username) {
		return user.getUserData(username);
	}
	
	
	
	@GetMapping("/deleteUser/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		user.userDataDelete(username);
		System.out.println("-------------------Inside the Delete Block----------------------");
		return "static/index.html";
	}
	
	@GetMapping("/healthCheck/{username}")
	public List<UserData> healthCheck(@PathVariable("username") String username){
		List<UserData>userD = user.userDataShow(username);
		System.out.println("-------------------Inside the Health check----------------------");
		return userD;
	}
	
	@GetMapping("/update/{id}")
	public UserData update(@PathVariable("id") String id) {
		System.out.println("-------------------Inside the update the block----------------------");
		return user.userDataUpdate(Integer.parseInt(id));
	}
	
}
