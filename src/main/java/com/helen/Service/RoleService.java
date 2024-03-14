package com.helen.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.Role;
import com.helen.Repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getAllRoles() {
		return this.roleRepository.findAll();
	}
	
}
