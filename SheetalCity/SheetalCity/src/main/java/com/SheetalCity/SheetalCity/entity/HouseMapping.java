package com.SheetalCity.SheetalCity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "house_info",uniqueConstraints = @UniqueConstraint(columnNames= {"house_no","block","city_id"}))

public class HouseMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="house_id")
	private long id;
	
	@NotNull
	@Column(name="house_no")
	private int houseNo;
	
	@NotNull
	@Column(name="block")
	private String block;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private CityDetails cityDetails;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserData userDetails;
	
}
