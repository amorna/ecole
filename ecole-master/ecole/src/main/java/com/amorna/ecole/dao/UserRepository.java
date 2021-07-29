package com.amorna.ecole.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amorna.ecole.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	//@Query("SELECT u FROM User u WHERE u.userName = ?1")
	//public User findByUserName(String username);

}
