package com.helen.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany
	@JoinColumn()
	@Column(name = "user_follow")
	private User userFollow;
	
	@ManyToMany
	@JoinColumn()
	@Column(name = "user_followed")
	private User userFollowed;
	
	
	
}
