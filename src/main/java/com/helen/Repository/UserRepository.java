package com.helen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//Buscar por email
	User findByEmail(String email);
}
