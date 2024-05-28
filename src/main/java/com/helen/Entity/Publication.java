package com.helen.Entity;

import java.time.LocalDate;
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
@Table(name = "publication")
public class Publication {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_publication")
	private Long idPublication;
	
	private String image;
	
	private String title;
	
	private String description;
	
	@Column(name = "created_date")
	private LocalDate createdDate;
	
	private String city;
	
	private Integer favorite;
	
	@Column(name = "public_id") // campo adicional para almacenar el public_id de Cloudinary
    private String publicId;
	
	@Column(name = "id_user")
	private Long fkUser;
	
	@ManyToOne
	@JoinColumn(name = "id_user", insertable = false, updatable = false)
	@JsonIgnore
	private User usersPublication;
	
	//Lista de me gustas
	@OneToMany(mappedBy = "publication")
	private List<UserFav> favorites;
	
	//Lista de comentarios
	@OneToMany(mappedBy = "publication")
	private List<Comment> comments;
	
}
