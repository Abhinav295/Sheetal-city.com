package com.SheetalCity.SheetalCity.dto;

import java.util.List;

import com.SheetalCity.SheetalCity.entity.CityDetails;
import com.SheetalCity.SheetalCity.entity.UserData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HouseMappingDTO {
	
	private long id;
	private int houseNo;
	private String block;
	private List<CityDetails> cityDetails;
	private List<UserData> userDetails;

}
