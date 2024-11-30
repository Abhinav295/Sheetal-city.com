package com.SheetalCity.SheetalCity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.SheetalCity.SheetalCity.entity.CityDetails;


@Repository
public interface CityDataRepository extends JpaRepository<CityDetails,Integer>{

	List<CityDetails> findByCityName(String cityName);

}
