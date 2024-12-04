package com.SheetalCity.SheetalCity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SheetalCity.SheetalCity.entity.CityDetails;
import com.SheetalCity.SheetalCity.entity.HouseMapping;

import jakarta.transaction.Transactional;

@Repository
public interface HouseMappingRepository extends JpaRepository<HouseMapping,Integer>{

	@Query("Select houseNo from HouseMapping where houseNo=?1 and block=?2 and cityDetails=?3")
	int findByHouseMapping(int houseNo,String block,CityDetails city);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM HouseMapping hm where hm.userDetails.id = ?1")
	void deleteHouseMappingsByUserId(int userId);
	
}
