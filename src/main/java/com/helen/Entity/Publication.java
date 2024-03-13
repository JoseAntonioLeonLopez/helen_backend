package com.helen.Entity;

import java.util.Date;
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
@Table(name = "Publication")
public class Publication {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id_publication")
	private Long idPublication;
	
	@Lob 
	@Basic(fetch = FetchType.EAGER)
	private byte[] image;
	
	private String title;
	
	private String description;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	private String city;
	
	private int favorite;
	
	@Column(name = "id_user")
	private Long fkUser;
	
	@ManyToOne
	@JoinColumn()
	private User usersPublication;
	
	//Lista de me gustas
	@OneToMany(mappedBy = "publication")
	private List<UserFav> favorites;
	
	//Lista de comentarios
	@OneToMany(mappedBy = "publication")
	private List<Comment> comments;
	
}
