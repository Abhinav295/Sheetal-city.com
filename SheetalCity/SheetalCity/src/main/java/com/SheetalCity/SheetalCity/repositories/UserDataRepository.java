package com.SheetalCity.SheetalCity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.SheetalCity.SheetalCity.entity.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,Integer>{
	
	List<UserData> findByUsername(String firstName);
	UserData findById(int id);
}
