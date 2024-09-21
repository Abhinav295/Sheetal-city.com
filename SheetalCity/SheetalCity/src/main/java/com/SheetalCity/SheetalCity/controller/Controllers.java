package com.SheetalCity.SheetalCity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
