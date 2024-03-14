package com.helen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.Role;
import com.helen.Repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	@Autowired
	private final RoleRepository roleRepository;
	
	public List<Role> getAllRoles() {
		return this.roleRepository.findAll();
	}
	
	public Optional<Role> getRole(Long id) {
        return roleRepository.findById(id);
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    public void removeRole(Long id) {
    	roleRepository.deleteById(id);
    }
	
}
