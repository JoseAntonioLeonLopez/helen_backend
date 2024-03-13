package com.helen.Entity;

import java.util.Date;

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
@Table(name = "User_follow")
public class UserFollow {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_user_follow")
	private Long idUserFollow;
	
	@Column(name = "follow_date")
	private Date followDate;
	
	//Seguidores
	@Column(name = "user_follow")
	private Long fkUserFollow;

	//Seguidos
	@Column(name = "user_followed")
	private Long fkUserFollowed;
	
	//Seguidores
	@ManyToOne
	@JoinColumn()
	private User follower;
	
	//Seguidos
	@ManyToOne
	@JoinColumn()
	private User followed;
	
	
	
}
