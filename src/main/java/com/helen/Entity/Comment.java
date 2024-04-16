package com.helen.Entity;

import java.time.LocalDate;

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
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_comment")
	private Long idComment;
	
	private String comment;
	
	@Column(name = "created_date")
	private LocalDate createdDate;
	
	@Column(name = "id_user")
	private Long fkUser;
	
	@Column(name = "id_publication")
	private Long fkPublication;
	
	@ManyToOne
	@JoinColumn(name = "id_user", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_publication", insertable = false, updatable = false)
	private Publication publication;
}
