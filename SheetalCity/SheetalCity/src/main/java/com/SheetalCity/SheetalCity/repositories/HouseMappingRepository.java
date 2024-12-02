package com.SheetalCity.SheetalCity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SheetalCity.SheetalCity.entity.CityDetails;
import com.SheetalCity.SheetalCity.entity.HouseMapping;

@Repository
public interface HouseMappingRepository extends JpaRepository<HouseMapping,Integer>{

	@Query("Select houseNo from HouseMapping where houseNo=?1 and block=?2 and cityDetails=?3")
	int findByHouseMapping(int houseNo,String block,CityDetails city);

}
