package com.SheetalCity.SheetalCity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthUserRequest {
	private String username;
	private String password;
	private String userType;

}
