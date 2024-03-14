package com.helen.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helen.Entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
	
    // Método para encontrar las publicaciones con más favoritos
    List<Publication> findTop10ByOrderByFavoriteDesc();
    
}
