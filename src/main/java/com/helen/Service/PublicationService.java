package com.helen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helen.Entity.Publication;
import com.helen.Repository.PublicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublicationService {

	@Autowired
	private final PublicationRepository publicationRepository;
	
	public List<Publication> getAllPublications() {
		return this.publicationRepository.findAll();
	}
	
	public Optional<Publication> getPublication(Long id) {
        return publicationRepository.findById(id);
    }

    public Publication addPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    public Publication updatePublication(Publication publication) {
    	if (publicationRepository.findById(publication.getIdPublication()).isEmpty()) {
	        throw new IllegalArgumentException("No se puede actualizar, el Publication no existe.");
	    }
    	
        return publicationRepository.save(publication);
    }

    public boolean removePublication(Long id) {
    	return getPublication(id).map(publication -> {
			publicationRepository.deleteById(id);
			return true;
		}).orElse(false);
    }
    
    //Metodo para sacar las 10 publicaciones con mas me gustas
	public List<Publication> getAllTopPublications() {
		return this.publicationRepository.findTop10ByOrderByFavoriteDesc();
	}
	
}
