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
	    if (roleRepository.findByRole(role.getRole()) != null) {
	        throw new IllegalStateException("Ya existe un Role con este nombre: " + role.getRole());
	    }
	    
	    return roleRepository.save(role);
	}

	public Role updateRole(Role role) {
	    if (roleRepository.findById(role.getIdRole()).isEmpty()) {
	        throw new IllegalArgumentException("No se puede actualizar, el Role no existe.");
	    }
	    
	    return roleRepository.save(role);
	}


    public boolean removeRole(Long id) {
    	return getRole(id).map(role -> {
			roleRepository.deleteById(id);
			return true;
		}).orElse(false);
    }
	
}
