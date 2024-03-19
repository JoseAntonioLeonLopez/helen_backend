package com.helen.Entity;


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
@Table(name = "user_fav")
public class UserFav {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_user_fav")
	private Long idUserFav;
	
	@Column(name = "id_user")
	private Long fkUser;
	
	@Column(name = "id_publication")
	private Long fkPublication;
	
	@ManyToOne
	@JoinColumn()
	private User user;
	
	@ManyToOne
	@JoinColumn()
	private Publication publication;
}
