package com.helen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	//Buscar por role
	Role findByRole(String role);
}
