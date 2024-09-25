package com.SheetalCity.SheetalCity.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		String username = user.userDataInsert(userData);
		System.out.println("-------------------Inside the insert block----------------------");
		return username;
	}
	
	@GetMapping("/getUser/{username}")
	public List<UserData> getUser(@PathVariable("username") String username) {
		return user.getUserData(username);
	}
	
	@DeleteMapping("/deleteUser/{username}")
	public String deleteUser(@PathVariable("username") String username) {
		user.userDataDelete(username);
		System.out.println("-------------------Inside the Delete Block----------------------");
		return "static/index.html";
	}
	
	@GetMapping("/getAllUsers")
	public List<UserData> healthCheck(){
		List<UserData>userD = user.userDataShow();
		System.out.println("-------------------Inside the Health check----------------------");
		return userD;
	}
	
	@PutMapping("/update/{id}")
	public List<UserData> updateUser(@PathVariable("id") String id,@RequestBody UserData userData) {
		System.out.println("-------------------Inside the update the block----------------------");
		return user.userDataUpdate(Integer.parseInt(id), userData);
	}
	
}
