package com.helen.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_follow")
public class UserFollow {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_user_follow")
	private Long idUserFollow;
	
	@Column(name = "follow_date")
	private LocalDate followDate;
	
	//Seguidores
	@Column(name = "user_follow")
	private Long fkUserFollow;

	//Seguidos
	@Column(name = "user_followed")
	private Long fkUserFollowed;
	
	//Seguidores
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_follow", referencedColumnName = "id_user", insertable = false, updatable = false)
	private User follower;
	
	//Seguidos
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_followed", referencedColumnName = "id_user", insertable = false, updatable = false)
	private User followed;
	
	
	
}
