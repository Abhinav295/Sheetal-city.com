package com.SheetalCity.SheetalCity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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

import com.SheetalCity.SheetalCity.dto.UserDataRequest;
import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.services.UserDataService;

@RestController
@RequestMapping("sheetal.city")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	UserDataService user;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserDataRequest userData) {
		try {
		String username = user.userDataInsert(userData);
		System.out.println("-------------------Inside the insert block----------------------");
		if(!username.isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.OK).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Occured");
		}
	}catch(DataIntegrityViolationException dIVE) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate entry Found");
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some Error Occured");
		}
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserData>> getAllUsers(){
		List<UserData>userD = user.userDataShow();
		return ResponseEntity.status(HttpStatus.OK).body(userD);
	}
	
	@PutMapping("/updateUser/{id}")
	public List<UserData> updateUser(@PathVariable("id") String id,@RequestBody UserData userData) {
		System.out.println("-------------------Inside the update the block----------------------");
		return user.userDataUpdate(Integer.parseInt(id), userData);
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

}
