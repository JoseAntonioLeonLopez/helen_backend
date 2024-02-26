package com.helen.Entity;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_user")
	private Long idUser;
	
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
	
	@Lob 
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "image_user")
	private byte[] imageUser;
	
	private String city;
	
	//Rol del usuario
	@Column(name = "id_rol")
	@ManyToOne
	@JoinColumn(name = "id_rol")
	private Rol rol;
	
	//Lista usuarios seguidos
	@ManyToMany
	private List<User> userFollow;
	
	//Lista seguidores
	@ManyToMany
	private List<User> userFollowed;
	
}
