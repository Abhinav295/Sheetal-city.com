package com.SheetalCity.SheetalCity.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SheetalCity.SheetalCity.dto.AuthResponse;
import com.SheetalCity.SheetalCity.dto.AuthUserRequest;
import com.SheetalCity.SheetalCity.security.JwtUtil;
import com.SheetalCity.SheetalCity.services.UserLoginService;

@RestController
@RequestMapping("sheetal.city/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired
	UserLoginService loginService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody AuthUserRequest user) {
		try {
		String username = loginService.AddUserDetails(user);
		System.out.println(username);
		if(!user.getUsername().isEmpty()) {
			System.out.println(username);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(username);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}
		}catch(SQLIntegrityConstraintViolationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}catch(SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate Entry Present");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthUserRequest user) {
		var opt = loginService.authenticate(user.getUsername());
		if(opt.isPresent() && loginService.checkPassword(opt.get(), user.getPassword())){
			String token = jwtUtil.generateToken(opt.get().getUsername());
			return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(token,user.getUsername()));
		}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inavlid User Name and Password");
	}

}
