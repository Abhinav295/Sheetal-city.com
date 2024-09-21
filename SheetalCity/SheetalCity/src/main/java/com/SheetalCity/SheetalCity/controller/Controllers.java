package com.SheetalCity.SheetalCity.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.services.UserDataService;


@RestController
@RequestMapping("/users")
public class Controllers {

	@Autowired
	UserDataService user;
	
	@GetMapping("/r")
	public String getSub() {
		user.userDataInsert();
		return "rj";
	}
	
	@GetMapping("/ki/{username}")
	public String web(@PathVariable("username") String username) {
		user.userDataDelete(username);
		return "index";
	}
	
	@GetMapping("/healthCheck/{username}")
	public String healthCheck(@PathVariable("username") String username){
		List<UserData>userD = user.userDataShow(username);
		String x = "";
		for(UserData d : userD) {
			x = x + d.getId();
			System.out.println();
		}
		return "Health Check"+x;
	}
	
}
