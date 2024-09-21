package com.SheetalCity.SheetalCity.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users_info")
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private int id;
	
	@NotNull
	@Column(name="user_name")
	private String username;
	
	@NotNull
	private String password;
	@NotNull
	private String firstName;
	
	private String lastName;
	@NotNull
	private String email;
	
	private String mobNo;
	
	@NotNull
	@DefaultValue("true")
	private boolean isActive =true;
	
	private Date created_dt;
	
	private Date updated_dt;
	
	@PrePersist
	    public void prePersist() {
	       if (updated_dt == null) {
	    	   updated_dt = new Date();
	       }
	       if(created_dt == null) {
	    	   created_dt = new Date();
	       }
	}
}
