package com.helen.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

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
	
}
