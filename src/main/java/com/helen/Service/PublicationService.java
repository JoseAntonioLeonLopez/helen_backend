package com.helen.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    	if (publicationRepository.findById(publication.getIdPublication()).isEmpty()) {
	        throw new IllegalArgumentException("No se puede actualizar, el Publication no existe.");
	    }
    	
        return publicationRepository.save(publication);
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
    
    //Metodo para sacar las 10 publicaciones con mas me gustas
	public List<Publication> getAllTopPublications() {
		return this.publicationRepository.findTop10ByOrderByFavoriteDesc();
	}
	
}
