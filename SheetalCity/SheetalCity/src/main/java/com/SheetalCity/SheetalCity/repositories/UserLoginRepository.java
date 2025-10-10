package com.SheetalCity.SheetalCity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SheetalCity.SheetalCity.entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{
	Optional<UserLogin> findByUsername(String username);

	long deleteByUsername(String username);

	boolean existsByUsername(String username);
	
}
