package com.helen.Entity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helen.Service.RoleService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

	@Autowired
	RoleService roleService;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	
	private String username;
	
	private String name;
	
	@Column(name = "first_surname")
	private String firstSurname;
	
	@Column(name = "second_surname")
	private String secondSurname;
	
	private int gender;
	
	private String email;
	
	private String password;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "image_user")
	private String imageUser;
	
	@Column(name = "public_id")
	private String publicId;
	
	private String city;
	
	@Column(name = "id_role")
	private Long fkRole;

	@ManyToOne
	@JoinColumn(name = "id_role", insertable = false, updatable = false)
	private Role role;
	
	//Lista seguidores
	@OneToMany(mappedBy = "follower")
	private List<UserFollow> follower;
	
	//Lista usuarios seguidos
	@OneToMany(mappedBy = "followed")
	private List<UserFollow> followed;

	//Lista de publicaciones
	@OneToMany(mappedBy = "usersPublication")
	private List<Publication> usersPublications;
	
	//Lista publicaciones me gusta
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<UserFav> favoritePublications;
	
	//Lista de comentarios
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    // Obtener el rol del usuario
	    Optional<Role> roleOptional = roleService.getRole(fkRole);
	    
	    // Verificar si el rol existe
	    if (roleOptional.isPresent()) {
	        Role role = roleOptional.get();
	        
	        // Crear una lista con el rol del usuario como autoridad
	        return List.of(new SimpleGrantedAuthority(role.getRole()));
	    } else {
	        // Si el rol no existe, devolver una colección vacía
	        return Collections.emptyList();
	    }
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
