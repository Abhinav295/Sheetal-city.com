package com.SheetalCity.SheetalCity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
	
	public AuthResponse(String token, String username) {
		this.token = token;
		this.username = username;
	}
	String username;
	String token;

}
