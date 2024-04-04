package com.helen.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//Buscar por username
	Optional<User> findByUsername(String username);
}
