package com.helen.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_role")
	private Long idRole;
	
	private String role;
	
	private String description;
	
	//Lista de usuarios segun el rol
	@OneToMany(mappedBy = "role")
	private List<User> users;
}