package com.helen.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helen.Entity.Role;
import com.helen.Service.RoleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
	
	@Autowired
	private final RoleService roleService;
	
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
    	return new ResponseEntity<List<Role>>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
		return roleService.getRole(id)
				.map(role -> new ResponseEntity<>(role, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    @PostMapping
	public ResponseEntity<Role> addRole(@RequestBody Role role) {
		return new ResponseEntity<Role>(roleService.addRole(role), HttpStatus.CREATED);
	}
    
    @PutMapping("/{id}")
	public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable("id") Long id) {
		if(id == role.getIdRole()) {
			return roleService.getRole(id)
			    	.map(roleDB -> new ResponseEntity<>(roleService.addRole(role), HttpStatus.OK))
			    	.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Role> removeRole(@PathVariable("id") Long id) {
		if (roleService.removeRole(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
