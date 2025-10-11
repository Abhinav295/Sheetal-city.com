package com.SheetalCity.SheetalCity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataRequest {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String mobNo;
	private boolean isActive;
}
