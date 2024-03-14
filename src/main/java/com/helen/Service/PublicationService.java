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
        return publicationRepository.save(publication);
    }

    public boolean removePublication(Long id) {
    	return getPublication(id).map(publication -> {
			publicationRepository.deleteById(id);
			return true;
		}).orElse(false);
    }
	
}
