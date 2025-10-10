package com.SheetalCity.SheetalCity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserRequest {
	
	private String username;
	private String password;
	private String userType;

}
