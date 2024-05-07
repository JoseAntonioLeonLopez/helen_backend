package com.helen.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.helen.Entity.Publication;
import com.helen.Repository.PublicationRepository;
import com.helen.Repository.UserFavRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicationService {

	@Autowired
	private final PublicationRepository publicationRepository;
	
	@Autowired
	private final UserFavRepository userFavRepository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Publication> getAllPublications() {
		return this.publicationRepository.findAll();
	}
	
	public Optional<Publication> getPublication(Long id) {
        return publicationRepository.findById(id);
    }

    public Publication addPublication(Publication publication) {
    	publication.setCreatedDate(LocalDate.now());
        return publicationRepository.save(publication);
    }

    public Publication updatePublication(Publication publication) {
        Long publicationId = publication.getIdPublication();
        Optional<Publication> existingPublicationOptional = publicationRepository.findById(publicationId);
        
        if (existingPublicationOptional.isEmpty()) {
            throw new IllegalArgumentException("No se puede actualizar, la Publication no existe.");
        }

        Publication existingPublication = existingPublicationOptional.get();
        
        // Actualizando los campos de la publicación existente con los valores de la nueva publicación
        existingPublication.setTitle(publication.getTitle());
        existingPublication.setDescription(publication.getDescription());
        existingPublication.setCity(publication.getCity());

        return publicationRepository.save(existingPublication);
    }


    @Transactional
    public boolean removePublication(Long id) {
    	return getPublication(id).map(publication -> {
            // Eliminar los registros de user_fav asociados a la publicación
            userFavRepository.deleteByFkPublication(id);
            
            // Eliminar la publicación
            publicationRepository.deleteById(id);
            
            return true;
        }).orElse(false);
    }
    
    // Método para obtener las 10 publicaciones más populares
    public List<Publication> getTop10Publications() {
        String sql = "SELECT p.*, COUNT(uf.id_user_fav) AS favorite_count " +
                     "FROM publication p " +
                     "LEFT JOIN user_fav uf ON p.id_publication = uf.id_publication " +
                     "GROUP BY p.id_publication " +
                     "ORDER BY favorite_count DESC " +
                     "LIMIT 10";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Publication publication = new Publication();
            publication.setIdPublication(rs.getLong("id_publication"));
            publication.setImage(rs.getString("image"));
            publication.setTitle(rs.getString("title"));
            publication.setDescription(rs.getString("description"));
            publication.setCreatedDate(rs.getDate("created_date").toLocalDate());
            publication.setCity(rs.getString("city"));
            publication.setFavorite(rs.getInt("favorite"));
            publication.setPublicId(rs.getString("public_id"));
            publication.setFkUser(rs.getLong("id_user"));
            return publication;
        });
    }
}
