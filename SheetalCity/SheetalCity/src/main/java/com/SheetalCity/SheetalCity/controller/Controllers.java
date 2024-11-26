package com.SheetalCity.SheetalCity.controller;
import java.net.http.HttpResponse.BodyHandler;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.entity.UserLogin;
import com.SheetalCity.SheetalCity.services.UserDataService;
import com.SheetalCity.SheetalCity.services.UserLoginService;

@RestController
@RequestMapping("sheetal.city")
@CrossOrigin(origins = "http://localhost:3000")
public class Controllers {

	@Autowired
	UserDataService user;
	
	@Autowired
	UserLoginService loginService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserData userData) {
		String username = user.userDataInsert(userData);
		System.out.println("-------------------Inside the insert block----------------------");
		if(!username.isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.OK).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Occured");
		}
	}
	
	@GetMapping("/getUser/{username}")
	public List<UserData> getUser(@PathVariable("username") String username) {
		return user.getUserData(username);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		boolean isDeleted = user.userDataDelete(id);
		System.out.println("-------------------Inside the Delete Block----------------------");
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inavlid User Name");
		}
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserData>> healthCheck(){
		List<UserData>userD = user.userDataShow();
		System.out.println("-------------------Inside the Health check----------------------");
		return ResponseEntity.status(HttpStatus.OK).body(userD);
	}
	
	@PutMapping("/update/{id}")
	public List<UserData> updateUser(@PathVariable("id") String id,@RequestBody UserData userData) {
		System.out.println("-------------------Inside the update the block----------------------");
		return user.userDataUpdate(Integer.parseInt(id), userData);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserLogin user) {
		String username = loginService.authenticate(user);
		if(!username.isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.OK).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inavlid User Name");
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody UserLogin user) {
		String username = loginService.AddUserDetails(user);
		if(!username.isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.OK).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inavlid User Name");
		}
	}
	
	
	@GetMapping("/getAllCred")
	public List<UserLogin> getAllCred(){
		return loginService.getAllLoginCred();
	}
	
}
