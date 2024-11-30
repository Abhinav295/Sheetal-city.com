package com.SheetalCity.SheetalCity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "city_details")
public class CityDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="city_id")
	private int id;
	
	private String cityName;
	private double rsPerUnit;
	private double maintenancePerMonth;
}
