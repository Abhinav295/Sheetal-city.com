package com.SheetalCity.SheetalCity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SheetalCity.SheetalCity.entity.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,Integer>{
}
